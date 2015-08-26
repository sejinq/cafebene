package com.parse;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;

import bolts.Task;

/**
 * Created by jsw on 2015. 8. 17..
 */
public class KakaoAuthenticationProvider extends ParseAuthenticationProvider {
    private static final DateFormat preciseDateFormat;
    public static final int DEFAULT_AUTH_ACTIVITY_CODE = 64206;
    public static final String AUTH_TYPE = "kakao";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRATION_DATE = "expiration_date";
    private CallbackManager callbackManager;
    private WeakReference<Activity> baseActivity;
    private WeakReference<Fragment> baseFragment;
    private Collection<String> permissions;
    private KakaoAuthenticationProvider.LoginAuthorizationType authorizationType;

    public KakaoAuthenticationProvider() {
        this.authorizationType = KakaoAuthenticationProvider.LoginAuthorizationType.READ;
    }

//    public KakaoAuthenticationProvider(Context context, int callbackRequestCodeOffset) {
//        this.authorizationType = KakaoAuthenticationProvider.LoginAuthorizationType.READ;
//        KakaoSdk.sdkInitialize(context, callbackRequestCodeOffset);
//    }

    public String getAuthType() {
        return "kakao";
    }

    public synchronized KakaoAuthenticationProvider setActivity(Activity activity) {
        this.baseActivity = new WeakReference(activity);
        return this;
    }

    public synchronized KakaoAuthenticationProvider setFragment(Fragment fragment) {
        this.baseFragment = new WeakReference(fragment);
        return this;
    }

    public synchronized KakaoAuthenticationProvider setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public synchronized KakaoAuthenticationProvider setLoginAuthorizationType(KakaoAuthenticationProvider.LoginAuthorizationType authorizationType) {
        this.authorizationType = authorizationType;
        return this;
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean ret = false;
        if(this.callbackManager != null) {
            ret = this.callbackManager.onActivityResult(requestCode, resultCode, data);
            this.callbackManager = null;
        }

        return ret;
    }

    public Task<Map<String, String>> authenticateAsync() {
        if(this.callbackManager != null) {
            return Task.forError(new RuntimeException("Unable to authenticate when another authentication is in process"));
        } else {
            final Task.TaskCompletionSource tcs = Task.create();
            Activity activity = this.baseActivity != null?(Activity)this.baseActivity.get():null;
            Fragment fragment = this.baseFragment != null?(Fragment)this.baseFragment.get():null;
            LoginManager manager = LoginManager.getInstance();
            this.callbackManager = CallbackManager.Factory.create();
//            manager.registerCallback(this.callbackManager, new KakaoCallback<LoginResult>() {
//                public void onSuccess(LoginResult loginResult) {
//                    AccessToken accessToken = loginResult.getAccessToken();
//                    Map authData = KakaoAuthenticationProvider.this.getAuthData(accessToken);
//                    tcs.trySetResult(authData);
//                }
//
//                public void onCancel() {
//                    tcs.trySetCancelled();
//                }
//
//                public void onError(FacebookException e) {
//                    tcs.trySetError(e);
//                }
//            });
            if(KakaoAuthenticationProvider.LoginAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                if(fragment != null) {
                    manager.logInWithPublishPermissions(fragment, this.permissions);
                } else {
                    manager.logInWithPublishPermissions(activity, this.permissions);
                }
            } else if(fragment != null) {
                manager.logInWithReadPermissions(fragment, this.permissions);
            } else {
                manager.logInWithReadPermissions(activity, this.permissions);
            }

            this.baseActivity = null;
            this.baseFragment = null;
            this.authorizationType = KakaoAuthenticationProvider.LoginAuthorizationType.READ;
            this.permissions = null;
            return tcs.getTask();
        }
    }

    public synchronized void cancel() {
    }

    public boolean restoreAuthentication(Map<String, String> authData) {
        if(authData == null) {
            LoginManager.getInstance().logOut();
            return true;
        } else {
            try {
                ApplicationInfo androidInfo = baseActivity.get().getPackageManager().getApplicationInfo(baseActivity.get().getPackageName(), PackageManager.GET_META_DATA);
                AccessToken e = new AccessToken((String)authData.get("access_token"), (String)androidInfo.metaData.get("com.kakao.sdk.AppKey"), (String)authData.get("id"), (Collection)null, (Collection)null, (AccessTokenSource)null, preciseDateFormat.parse((String)authData.get("expiration_date")), (Date)null);
                AccessToken.setCurrentAccessToken(e);
                return true;
            } catch(PackageManager.NameNotFoundException e){
                return false;
            } catch (ParseException var3) {
                return false;
            }
        }
    }

    public void deauthenticate() {
        this.restoreAuthentication((Map)null);
    }

    public Map<String, String> getAuthData(AccessToken accessToken) {
        HashMap authData = new HashMap();
        authData.put("id", accessToken.getUserId());
        authData.put("access_token", accessToken.getToken());
        authData.put("expiration_date", preciseDateFormat.format(accessToken.getExpires()));
        return authData;
    }

    static {
        preciseDateFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.US);
        preciseDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
    }

    public static enum LoginAuthorizationType {
        READ,
        PUBLISH;

        private LoginAuthorizationType() {
        }
    }

    public interface KakaoCallback<RESULT> {
        void onSuccess(RESULT var1);

        void onCancel();

        void onError(FacebookException var1);
    }

    static public Task<ParseUser> logInWithAsync(Map<String,String> authData){
        Log.d("Kakao","Kakao Auth");
        return ParseUser.logInWithAsync(AUTH_TYPE, authData);
    }
}
