package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by jsw on 2015. 7. 16..손세지니 시작한댜야ㅑㅑㅑㅑ8월 18일!!!!!!!!!
 */
public class InformationActivity extends Activity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_information);

        webview = new WebView(this);
        ((ImageButton) this.findViewById(R.id.inform_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //바로 웹뷰로 넘어감. 피부타입 테스트
                Intent nextIntent = new Intent(InformationActivity.this, WebViewActivity.class);
                nextIntent.putExtra(WebViewActivity.URL, "http://m.blog.naver.com/cosmeticforman/220438314170");
                nextIntent.putExtra(WebViewActivity.ACTIONBAR, MyApplication.action_bar_tag.AC_HOME);
                startActivity(nextIntent);
            }
        });
        //추천제품리뷰
        ((ImageButton) this.findViewById(R.id.inform_2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                nextIntent.putExtra(InfoDetailActivity.PAGE_TAG, web_pageFlag.PG_PRODUCT_REVIEW);
                nextIntent.putExtra(InfoDetailActivity.TITLE, "추천 제품 리뷰");
                startActivity(nextIntent);
            }
        });
        //화장품 기초 상식
        ((ImageButton) this.findViewById(R.id.inform_3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                nextIntent.putExtra(InfoDetailActivity.PAGE_TAG, web_pageFlag.PG_BAGIC_INFORM);
                nextIntent.putExtra(InfoDetailActivity.TITLE, "화장품 기초 상식");
                startActivity(nextIntent);
            }
        });
        //화장품 성분
        ((ImageButton) this.findViewById(R.id.inform_4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                nextIntent.putExtra(InfoDetailActivity.PAGE_TAG, web_pageFlag.PG_INGREDIENT);
                nextIntent.putExtra(InfoDetailActivity.TITLE, "화장품 성분");
                startActivity(nextIntent);
            }
        });

        // 액션바 검색 버튼 리스너 달아주는 코드
        ((ImageButton) findViewById(R.id.searchButton)).setOnClickListener(ClickListener);

        initTab();
        setFont();

    }

    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
                Intent Intent = new Intent(InformationActivity.this, SearchActivity.class);
                //nextIntent.putExtra("",type);
                //nextIntent.putExtra("",id);
                startActivity(Intent);
        }
    };
    View.OnClickListener ClickTabListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent Intent;
            switch (v.getId())
            {
                //홈으로 넘겨줌
                case R.id.tab1
                    :Intent = new Intent(InformationActivity.this, HomeActivity.class);
                    startActivity(Intent);break;
                //추천화장품으로넘겨줌
                case R.id.tab2
                    :Intent = new Intent(InformationActivity.this, RecommendIntroActivity.class);
                    startActivity(Intent);break;
                //마이페이지로넘김
                case R.id.tab4
                    :Intent = new Intent(InformationActivity.this, MyPageActivity.class);
                    startActivity(Intent);break;
            }

        }
    };

    private void initTab()
    {
        //리스너 부착
        ((Button) findViewById(R.id.tab1)).setOnClickListener(ClickTabListener);
        ((Button) findViewById(R.id.tab2)).setOnClickListener(ClickTabListener);
        ((Button) findViewById(R.id.tab4)).setOnClickListener(ClickTabListener);

        ((Button)findViewById(R.id.tab3)).setBackgroundResource(R.drawable.menu_tap);
    }
    public interface web_pageFlag{
        int PG_SKIN_TYPE_TEST = 0;// 피부타입 테스트
        int PG_BAGIC_INFORM = 2;// 화장품 상식
        int PG_PRODUCT_REVIEW = 1;// 추천 리뷰
        int PG_INGREDIENT = 3; //화장품성분
    }
    private void setFont()
    {
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
    }
    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}