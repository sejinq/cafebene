package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.kakao.auth.APIErrorResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LogoutResponseCallback;
import com.kakao.usermgmt.MeResponseCallback;
import com.kakao.usermgmt.SignupResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.UserProfile;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;

import java.util.List;


public class LoginActivity extends Activity {
    private SimpleFacebook facebookApi;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(!Session.getCurrentSession().isClosed()) Log.d("Kakao","Session is unstable!");
		setView();
    }

    @Override
    protected void onResume(){
        super.onResume();

        // Set Facebook Library
        facebookApi =  SimpleFacebook.getInstance(this);
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        facebookApi.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setView(){
		setContentView(R.layout.activity_login);

		//Button Handler
//		((ImageButton)findViewById(R.id.login_email_join_btn)).setOnClickListener(view -> emailJoinAction());
//        ((ImageButton)findViewById(R.id.login_facebook_sign_btn)).setOnClickListener(view -> kakaoSignAction());
//        ((ImageButton)findViewById(R.id.login_facebook_sign_btn)).setOnClickListener(view -> facebookSignAction());
//        ((ImageButton)findViewById(R.id.login_email_sign_btn)).setOnClickListener(view -> emailSignAction());
    }

    //Button Action
    private void emailJoinAction(){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
    private void kakaoSignAction(){
        Session.getCurrentSession().open(AuthType.KAKAO_TALK, this);
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onSuccess(long l) {
                        return;
                    }
                    @Override
                    public void onFailure(APIErrorResult apiErrorResult) {
                        return;
                    }
                });
                ServerInteraction.onLogin("" + userProfile.getId(), null, User.UserType.KAKAO);
            }

            @Override
            public void onNotSignedUp() {
                UserManagement.requestSignup(new SignupResponseCallback() {
                    @Override
                    public void onSuccess(long userObjectId) {
                        UserManagement.requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onSuccess(long l) {
                                return;
                            }

                            @Override
                            public void onFailure(APIErrorResult apiErrorResult) {
                                return;
                            }
                        });
                        externalSignAction(User.UserType.KAKAO, "" + userObjectId);
                    }

                    @Override
                    public void onSessionClosedFailure(APIErrorResult apiErrorResult) {
                        kakaoSignAction();
                    }

                    @Override
                    public void onFailure(APIErrorResult errorResult) {
                        Log.e("Kakao", "failed to sign up. msg = " + errorResult);
                    }
                }, null);
            }

            @Override
            public void onSessionClosedFailure(APIErrorResult apiErrorResult) {
                kakaoSignAction();
            }

            @Override
            public void onFailure(APIErrorResult errorResult) {
                Log.e("Kakao", "failed to update profile. msg = " + errorResult);
            }
        });
    }
    private void facebookSignAction(){
        facebookApi.login(new OnLoginListener() {
            @Override
            public void onLogin(String s, List<Permission> list, List<Permission> list1) {
                Log.d("Facebook","Login Success!");
            }

            @Override
            public void onCancel() {
                Log.d("Facebook","User Cancel");
            }

            @Override
            public void onException(Throwable throwable) {
                Log.e("Facebok","Facebook Exception:"+throwable);
            }

            @Override
            public void onFail(String s) {
                Log.e("Facebook","Login Fail:"+s);
            }
        });
    }
    private void emailSignAction(){
        startActivity(new Intent(LoginActivity.this, SignInActivity.class));
    }
    private void externalSignAction(int type, String id){
        Intent nextIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        nextIntent.putExtra("",type);
        nextIntent.putExtra("",id);
        startActivity(nextIntent);
    }
    // Facebook Method
    private void facebookLoginAction(){
    }
}