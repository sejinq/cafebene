package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.APIErrorResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.usermgmt.MeResponseCallback;
import com.kakao.usermgmt.SignupResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.UserProfile;
import com.parse.KakaoAuthenticationProvider;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.HashMap;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(!Session.getCurrentSession().isClosed()) Log.d("Kakao","Session is unstable!");
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        setView();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    private void setView(){
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_logo).setAlpha(0.8f);

        FontApplyer.setFont(this, (TextView)findViewById(R.id.login_existing_text),FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);

        //Button Handler
//        ((ImageButton) findViewById(R.id.login_email_join_btn)).setOnClickListener(view -> emailJoinAction());
        ((ImageButton) findViewById(R.id.login_email_join_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailJoinAction();
            }
        });
        ((ImageButton)findViewById(R.id.login_kakao_sign_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                block();
            }
        });
        ((ImageButton)findViewById(R.id.login_facebook_sign_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookSignAction();
            }
        });
        ((ImageButton)findViewById(R.id.login_email_sign_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailSignAction();
            }
        });
    }

    //Button Action
    private void emailJoinAction(){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//        ParseUser user = new ParseUser();
//        user.setUsername("email@example.com");
//        user.setPassword("my pass");
//
//        user.signUpInBackground(new SignUpCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                }
//            }
//        });
    }
    private void kakaoSignAction(){
        Session.getCurrentSession().open(AuthType.KAKAO_TALK, this);
        Log.d("LogIn", "Start Kakao Sign");
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.d("LogIn", "Get Kakao Member User Profile");
//                UserManagement.requestLogout(new LogoutResponseCallback() {
//                    @Override
//                    public void onSuccess(long l) {
//                        return;
//                    }
//
//                    @Override
//                    public void onFailure(APIErrorResult apiErrorResult) {
//                        return;
//                    }
//                });
                HashMap authData = new HashMap();
                authData.put("id", "" + userProfile.getId());
                authData.put("access_token", Session.getCurrentSession().getAccessToken());
//                authData.put("expiration_date", preciseDateFormat.format(accessToken.getExpires()));
                KakaoAuthenticationProvider.logInWithAsync(authData);

//                ServerInteraction.onLogin("" + userProfile.getId(), null, User.UserType.KAKAO);
            }

            @Override
            public void onNotSignedUp() {
                Log.d("LogIn", "User was not member");
                UserManagement.requestSignup(new SignupResponseCallback() {
                    @Override
                    public void onSuccess(long userObjectId) {
                        Log.d("LogIn", "Success to signed up in kakao");
                        HashMap authData = new HashMap();
                        authData.put("id", "" + userObjectId);
                        authData.put("access_token", Session.getCurrentSession().getAccessToken());
//                authData.put("expiration_date", preciseDateFormat.format(accessToken.getExpires()));
                        KakaoAuthenticationProvider.logInWithAsync(authData);
//                        UserManagement.requestLogout(new LogoutResponseCallback() {
//                            @Override
//                            public void onSuccess(long l) {
//                                return;
//                            }
//
//                            @Override
//                            public void onFailure(APIErrorResult apiErrorResult) {
//                                return;
//                            }
//                        });
//                        externalSignAction(User.UserType.KAKAO, "" + userObjectId);
                    }

                    @Override
                    public void onSessionClosedFailure(APIErrorResult apiErrorResult) {
                        Log.d("LogIn", "Kakao sign up failed");
                        kakaoSignAction();
                    }

                    @Override
                    public void onFailure(APIErrorResult errorResult) {
                        Log.d("LogIn", "Kakao sign up failed");
                        Log.e("Kakao", "failed to sign up. msg = " + errorResult);
                    }
                }, null);
            }

            @Override
            public void onSessionClosedFailure(APIErrorResult apiErrorResult) {
                Log.d("LogIn", "Kakao sign up failed");
                kakaoSignAction();
            }

            @Override
            public void onFailure(APIErrorResult errorResult) {
                Log.d("LogIn", "Kakao sign up failed");
                Log.e("Kakao", "failed to update profile. msg = " + errorResult);
            }
        });
    }
    private void facebookSignAction(){
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, Arrays.asList("email"), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (parseUser.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    Intent signup = new Intent(LoginActivity.this,SignUpActivity.class);
                    signup.putExtra(SignUpActivity.USER_TYPE,User.UserType.FACEBOOK);
                    signup.putExtra(SignUpActivity.USER_NAME,parseUser.getUsername());
                    startActivity(signup);
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                    User loginUser = ServerInteraction.onLoginWithParseUser(parseUser);
                    if(parseUser.get("displayedName") != null) {
                        ((MyApplication) getApplicationContext()).setUser(loginUser);
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }else{
                        Intent signup = new Intent(LoginActivity.this,SignUpActivity.class);
                        signup.putExtra(SignUpActivity.USER_TYPE,User.UserType.FACEBOOK);
                        signup.putExtra(SignUpActivity.USER_NAME,parseUser.getUsername());
                        startActivity(signup);
                    }
                }
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

    private void block(){
        Toast.makeText(this,"Not support now",Toast.LENGTH_SHORT).show();
    }
}