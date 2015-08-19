package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendIntroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_recommend_intro);

        // 액션바의 홈 버튼에 배경 설정
        Button btn = (Button) findViewById(R.id.tab2);
        btn.setBackgroundResource(R.drawable.menutap);

        // 글꼴 설정
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.recommend_intro_skintype_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.recommend_intro_situation_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.recommend_intro_season_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);

        // ClickListener 달아주기
        ((ImageButton) findViewById(R.id.searchButton)).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab1).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab3).setOnClickListener(ClickListener);
        ((RelativeLayout)findViewById(R.id.home_action_bar)).findViewById(R.id.tab4).setOnClickListener(ClickListener);
        ((ImageButton) findViewById(R.id.oily_button)).setOnClickListener(ClickListener);
        ((ImageButton) findViewById(R.id.dry_button)).setOnClickListener(ClickListener);
        ((ImageButton) findViewById(R.id.sensitive_button)).setOnClickListener(ClickListener);
        ((ImageButton) findViewById(R.id.situation_button)).setOnClickListener(ClickListener);
        ((ImageButton) findViewById(R.id.season_btn1)).setOnClickListener(ClickListener);
        ((ImageButton) findViewById(R.id.season_btn2)).setOnClickListener(ClickListener);
    }

    // 버튼 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        private Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.searchButton:
                    intent = new Intent(RecommendIntroActivity.this, SearchActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab1:
                    finish();
                    break;
                case R.id.tab3:
                    intent = new Intent(RecommendIntroActivity.this, InfoDetailActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab4:
                    intent = new Intent(RecommendIntroActivity.this, MyPageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.oily_button:
                    intent = new Intent(RecommendIntroActivity.this, RecommendActivity.class);
                    intent.putExtra(RecommendActivity.RECOMMEND_KEY, User.SKIN_TYPE_OILY);
                    startActivity(intent);
                    break;
                case R.id.dry_button:
                    intent = new Intent(RecommendIntroActivity.this, RecommendActivity.class);
                    intent.putExtra(RecommendActivity.RECOMMEND_KEY, User.SKIN_TYPE_DRY);
                    startActivity(intent);
                    break;
                case R.id.sensitive_button:
                    intent = new Intent(RecommendIntroActivity.this, RecommendActivity.class);
                    intent.putExtra(RecommendActivity.RECOMMEND_KEY, User.SKIN_TYPE_SENSITIVE);
                    startActivity(intent);
                    break;
                case R.id.situation_button:
                    intent = new Intent(RecommendIntroActivity.this, RecommendActivity.class);
                    intent.putExtra(RecommendActivity.RECOMMEND_KEY, User.SEASON_SOLDIER);
                    startActivity(intent);
                    break;
                case R.id.season_btn1:
                case R.id.season_btn2:
                    Toast.makeText(RecommendIntroActivity.this, "추후 지원 예정입니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
        }
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
