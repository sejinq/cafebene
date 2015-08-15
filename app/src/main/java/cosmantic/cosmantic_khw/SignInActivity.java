package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_signin);

        // 검색 버튼은 안 쓰므로 안 보이게 설정
        ImageButton imgButton = (ImageButton)findViewById(R.id.searchButton);
        imgButton.setVisibility(View.GONE);

        // 액션바 제목 추가 및 글꼴 변경
        TextView mTitleTextView = (TextView)findViewById(R.id.titletext);
        mTitleTextView.setText("로그인");

        // 제목 글꼴 변경
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);

        // xml 글씨들 글꼴 변경
        mTitleTextView = (TextView)findViewById(R.id.signInEmail);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);
        mTitleTextView = (TextView)findViewById(R.id.signInPw);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);
        mTitleTextView = (TextView)findViewById(R.id.signInPutEmail);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        mTitleTextView = (TextView)findViewById(R.id.signInPutPw);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        mTitleTextView = (TextView)findViewById(R.id.signInLoginText);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 버튼들 리스너 달아주기
        imgButton = (ImageButton)findViewById(R.id.signInLoginButton);
        imgButton.setOnTouchListener(TouchListener);
        imgButton = (ImageButton)findViewById(R.id.signInPwFindButton);
        imgButton.setOnTouchListener(TouchListener);
        imgButton = (ImageButton)findViewById(R.id.backButton);
        imgButton.setOnTouchListener(TouchListener);
    }

    // 액션바 뒤로가기 버튼, 로그인 버튼과 비밀번호 찾기 리스너
    View.OnTouchListener TouchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            // 터치할 때
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                switch(v.getId()){
                    case R.id.backButton:
                        Toast.makeText(SignInActivity.this, "Back Button", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.signInLoginButton:
                        Toast.makeText(SignInActivity.this, "signInLoginButton", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.signInPwFindButton:
                        Toast.makeText(SignInActivity.this, "signInPwFindButton", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }

            // 터치하고 뗄 때
//            if (event.getAction() == MotionEvent.ACTION_UP) {}
            return false;
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
