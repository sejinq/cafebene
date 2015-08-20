package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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
                nextIntent.putExtra("webview_title", "화장품 상식");
                nextIntent.putExtra("webview_url", "http://m.blog.naver.com/cosmeticforman/220451294230");
                startActivity(nextIntent);
            }
        });
        //추천제품리뷰
        ((ImageButton) this.findViewById(R.id.inform_2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                nextIntent.putExtra("pageTag", web_pageFlag.PG_PRODUCT_REVIEW);
                nextIntent.putExtra("pageTitle", "추천 제품 리뷰");
                startActivity(nextIntent);
            }
        });
        //화장품 기초 상식
        ((ImageButton) this.findViewById(R.id.inform_3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                nextIntent.putExtra("pageTag", web_pageFlag.PG_BAGIC_INFORM);
                nextIntent.putExtra("pageTitle", "화장품 기초 상식");
                startActivity(nextIntent);
            }
        });
        //화장품 성분
        ((ImageButton) this.findViewById(R.id.inform_4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                nextIntent.putExtra("pageTag", web_pageFlag.PG_INGREDIENT);
                nextIntent.putExtra("pageTitle", "화장품 성분");
                startActivity(nextIntent);
            }
        });

        // 액션바 검색 버튼 리스너 달아주는 코드
        ((ImageButton) findViewById(R.id.searchButton)).setOnClickListener(ClickListener);

        initTab();

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
                    :Intent = new Intent(InformationActivity.this, SearchActivity.class);
                    startActivity(Intent);break;
                //추천화장품으로넘겨줌
                case R.id.tab2
                    :Intent = new Intent(InformationActivity.this, SearchActivity.class);
                    startActivity(Intent);break;
                //마이페이지로넘김
                case R.id.tab4
                    :Intent = new Intent(InformationActivity.this, SearchActivity.class);
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
        //선택해제해주기
        //((Button) findViewById(R.id.tab1)).setBackground(get);
        //((Button) findViewById(R.id.tab2)).setBackgroundResource(getResources().getColor(Color.TRANSPARENT));
        //((Button) findViewById(R.id.tab4)).setBackgroundResource(getResources().getColor(Color.TRANSPARENT));

        //화장품 상식 탭만 선택으로 이미지 바꾸기
     //   ((Button) findViewById(R.id.tab4)).setBackground(getResources().getDrawable(R.drawable.menu_tap));
    }
    public interface web_pageFlag{
        public static final int PG_BAGIC_INFORM = 0x0;		 //기초상식
        public static final int PG_PRODUCT_REVIEW = 0x1; //추천제품리뷰
        public static final int PG_INGREDIENT = 0x2; //화장품성분
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
