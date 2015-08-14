package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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
        // 비밀번호 찾기 버튼도 현재는 안 보이게 설정
        imgButton = (ImageButton)findViewById(R.id.signInPwFindButton);
        imgButton.setVisibility(View.GONE);

        // 액션바 제목 추가 및 글꼴 변경
        TextView mTitleTextView = (TextView)findViewById(R.id.titleText);
        mTitleTextView.setText("로그인");

        // 제목 글꼴 변경
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // xml 글씨들 글꼴 변경
        mTitleTextView = (TextView)findViewById(R.id.signInEmail);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);
        mTitleTextView = (TextView)findViewById(R.id.signInPw);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);
        mTitleTextView = (TextView)findViewById(R.id.signInPutEmail);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        mTitleTextView = (TextView)findViewById(R.id.signInPutPw);
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        Button btn = (Button)findViewById(R.id.signInLoginButton);
        FontApplyer.setFont(this, btn, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 버튼들 리스너 달아주기
        btn.setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.signInPwFindButton)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.backButton)).setOnClickListener(ClickListener);
    }

    // 액션바 뒤로가기 버튼, 로그인 버튼과 비밀번호 찾기 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.backButton:
                    Toast.makeText(SignInActivity.this, "Back Button", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.signInLoginButton:
                    Toast.makeText(SignInActivity.this, "signInLoginButton", Toast.LENGTH_SHORT).show();
                    break;

                // 버튼 눌린 걸 처리해주기 위해 타이머 달아줌.
                case R.id.signInPwFindButton:
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignInActivity.this, "Button이 눌리고 0.2초 후에 나타나는 토스트", Toast.LENGTH_SHORT).show();
                        }
                    }, 200);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
