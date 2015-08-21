package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyPageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_mypage);

        // 액션바의 마이 페이지 버튼에 배경 설정
        Button btn = (Button) findViewById(R.id.tab4);
        btn.setBackgroundResource(R.drawable.menu_tap);

        // 글꼴 설정
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.mypage_profile_titletxt)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.mypage_nickname)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.mypage_genender_age)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.mypage_skintype)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 피부 고민들 글꼴 적용
        for(int i=1;i<=8;i++){
            int resourceId = getResources().getIdentifier("mypage_skin_problem"+i, "id", "cosmantic.cosmantic_khw");
            TextView textView = (TextView)findViewById(resourceId);
            textView.setText("# "+textView.getText().toString());
            FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        }

        // Click 리스너 달기
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab1).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab2).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab3).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.searchButton).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.mypage_profile_photo)).setOnClickListener(ClickListener);
        ((LinearLayout)findViewById(R.id.mypage_likelist)).setOnClickListener(ClickListener);
        ((LinearLayout)findViewById(R.id.mypage_myreview)).setOnClickListener(ClickListener);
        ((LinearLayout)findViewById(R.id.mypage_settings)).setOnClickListener(ClickListener);

        initTab();
    }
    View.OnClickListener ClickTabListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent Intent;
            switch (v.getId())
            {
                //홈으로 넘겨줌
                case R.id.tab1
                        :Intent = new Intent(MyPageActivity.this, HomeActivity.class);
                    startActivity(Intent);break;
                //추천화장품으로넘겨줌
                case R.id.tab3
                        :Intent = new Intent(MyPageActivity.this, InformationActivity.class);
                    startActivity(Intent);break;
                //마이페이지로넘김
                case R.id.tab2
                        :Intent = new Intent(MyPageActivity.this, RecommendIntroActivity.class);
                    startActivity(Intent);break;
            }

        }
    };
    private void initTab()
    {
        //리스너 부착
        ((Button) findViewById(R.id.tab1)).setOnClickListener(ClickTabListener);
        ((Button) findViewById(R.id.tab2)).setOnClickListener(ClickTabListener);
        ((Button) findViewById(R.id.tab3)).setOnClickListener(ClickTabListener);

        ((Button)findViewById(R.id.tab4)).setBackgroundResource(R.drawable.menu_tap);
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
                    intent = new Intent(MyPageActivity.this, InformationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mypage_profile_photo:
                    Toast.makeText(MyPageActivity.this, "프로필 사진 기능은 추후 지원 예정입니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mypage_likelist:
                    Toast.makeText(MyPageActivity.this, "찜 목록", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mypage_myreview:
                    Toast.makeText(MyPageActivity.this, "나의 리뷰", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mypage_settings:
                    Toast.makeText(MyPageActivity.this, "로그아웃의 탈을 쓴 환경설정", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent(MyPageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0,0);
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
