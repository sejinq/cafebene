package cosmantic.cosmantic_khw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kakao.auth.Session;
import com.kakao.auth.SessionCallback;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;


public class LoginActivity extends AppCompatActivity{
	private Session session;
	private LoginButton loginButton;
	private final SessionCallback mySessionCallback = new MySessionStatusCallback();

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);

	    // 로그인 버튼을 찾아온다.
	    loginButton = (LoginButton)findViewById(R.id.com_kakao_login);

	    // 세션 콜백 추가
	    session = Session.getCurrentSession();
	    session.addCallback(mySessionCallback);

	    if (session.isClosed()){
		    // 세션이 완전 종료된 상태로 갱신도 할 수 없으므로 명시적 오픈을 위한 로그인 버튼을 보여준다.
		    loginButton.setVisibility(View.VISIBLE);
	    } else {
		    // 세션을 가지고 있거나, 갱신할 수 있는 상태로 명시적 오픈을 위한 로그인 버튼을 보여주지 않는다.
		    loginButton.setVisibility(View.GONE);
		    session.implicitOpen();
	    }
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		session.removeCallback(mySessionCallback);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private class MySessionStatusCallback implements SessionCallback {
		@Override
		public void onSessionOpened() {
			// 프로그레스바 종료

			// 세션 오픈후 보일 페이지로 이동
			final Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
			startActivity(intent);
			finish();
		}

		@Override
		public void onSessionClosed(final KakaoException exception) {
			// 프로그레스바 종료
			// 세션 오픈을 못했으니 다시 로그인 버튼 노출.
			loginButton.setVisibility(View.VISIBLE);
		}

		@Override
		public void onSessionOpening(){
			//프로그레스바 시작
		}
    }
}