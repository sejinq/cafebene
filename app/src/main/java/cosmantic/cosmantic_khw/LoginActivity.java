package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kakao.auth.APIErrorResult;
import com.kakao.auth.Session;
import com.kakao.auth.SessionCallback;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.MeResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.UserProfile;
import com.kakao.util.exception.KakaoException;


public class LoginActivity extends Activity {
    private Session kakaoSession;
    private final SessionCallback kakaoSessionCallback = new KakaoSessionStatusCallback();

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
		setView();
        onKakaoCreate();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
        onKakaoDestroy();
	}

	private void setView(){
		setContentView(R.layout.activity_login);

		//Button Handler
		((ImageButton)findViewById(R.id.login_email_join_btn)).setOnClickListener(view -> emailJoinAction());
        ((ImageButton)findViewById(R.id.login_facebook_sign_btn)).setOnClickListener(view -> facebookSignAction());
        ((ImageButton)findViewById(R.id.login_email_sign_btn)).setOnClickListener(view -> emailSignAction());
    }

    //Button Action
    private void emailJoinAction(){

    }
    private void facebookSignAction(){

    }
    private void emailSignAction(){

    }

    //KakaoTalk Life Cycle Method
    private void onKakaoCreate(){
        kakaoSession = Session.getCurrentSession();
        kakaoSession.addCallback(kakaoSessionCallback);

        if (kakaoSession.isClosed()){
            ((LoginButton)findViewById(R.id.login_kakao_sign_btn)).setVisibility(View.VISIBLE);
        } else {
            ((LoginButton)findViewById(R.id.login_kakao_sign_btn)).setVisibility(View.INVISIBLE);
            kakaoSession.implicitOpen();
        }
    }
    private void onKakaoDestroy(){
        kakaoSession.removeCallback(kakaoSessionCallback);
    }
    private class KakaoSessionStatusCallback implements SessionCallback{
        @Override
        public void onSessionOpened(){
            Log.d("Kakao", "onSessionOpened");
            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onSuccess(final UserProfile userProfile) {
                    Log.d("Kakao","UserProfile : " + userProfile);
                    userProfile.saveUserToCache();

                    long id = userProfile.getId();
                    String nickname = userProfile.getNickname();
                    String ProfileImagePath = userProfile.getProfileImagePath();

                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSessionClosedFailure(final APIErrorResult errorResult) {
                }

                @Override
                public void onFailure(final APIErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Log.e("Kakao",message);

                    if (errorResult.getErrorCodeInt() == -777) {
                        Toast.makeText(getApplicationContext(), getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        @Override
        public void onSessionClosed(final KakaoException exception){
            Log.d("Kakao","onSessionClosed");
            ((LoginButton)findViewById(R.id.login_kakao_sign_btn)).setVisibility(View.VISIBLE);
            if(exception != null) {
                exception.printStackTrace();
            }
        }
        @Override
        public void onSessionOpening() {
            Log.d("Kakao", "onSessionOpening");
        }

    }
}