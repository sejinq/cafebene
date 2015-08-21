package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jsw on 2015. 7. 16..
 */
public class WebViewActivity extends Activity {
    public static final String URL = "cosmantic.cosmantic_khw.WebViewActivity.URL";
    public static final String TITLE = "cosmantic.cosmantic_khw.WebViewActivity.TITLE";
    public static final String ACTIONBAR = "cosmantic.cosmantic_khw.WebViewActivity.ACTIONBAR";

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_webview);

        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String url = intent.getExtras().getString(URL);
        int actionbar = intent.getExtras().getInt(ACTIONBAR);
        // WebViewClient 지정
        webview.setWebViewClient(new WebViewClientClass());

        if(actionbar == MyApplication.action_bar_tag.AC_HOME)
        {
            ((RelativeLayout)findViewById(R.id.webview_action_bar_home)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.tab3)).setBackgroundResource(R.drawable.menu_tap);
        }
        else
        {
            String title = intent.getExtras().getString(TITLE);
            ((TextView) findViewById(R.id.titleText)).setText(title);
            ((RelativeLayout)findViewById(R.id.webview_action_bar_sub)).setVisibility(View.VISIBLE);
        }

        // 구글홈페이지 지정
        webview.loadUrl(url);
    }
    View.OnClickListener ClickTabListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent Intent;
            switch (v.getId())
            {
                //홈으로 넘겨줌
                case R.id.tab1
                        :Intent = new Intent(WebViewActivity.this, HomeActivity.class);
                    startActivity(Intent);break;
                //추천화장품으로넘겨줌
                case R.id.tab2
                        :Intent = new Intent(WebViewActivity.this, RecommendIntroActivity.class);
                    startActivity(Intent);break;
                //마이페이지로넘김
                case R.id.tab4
                        :Intent = new Intent(WebViewActivity.this, MyPageActivity.class);
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

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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
