package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyPageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_mypage);

        // 액션바의 마이 페이지 버튼에 배경 설정
        Button btn = (Button) findViewById(R.id.tab4);
        btn.setBackgroundResource(R.drawable.menutap);

        // 글꼴 설정
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 액션바 버튼 리스너 달기
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab2).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab3).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab4).setOnClickListener(ClickListener);
    }

    // 버튼 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        private Intent intent;
        @Override
        public void onClick(View v) {
            Log.d("Home", "Click:" + v.getId());
            switch (v.getId()) {
                case R.id.searchButton:
                    intent = new Intent(MyPageActivity.this, SearchActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab1:
                    intent = new Intent(MyPageActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab2:
                    intent = new Intent(MyPageActivity.this, RecommendIntroActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab3:
                    intent = new Intent(MyPageActivity.this, InfoDetailActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
