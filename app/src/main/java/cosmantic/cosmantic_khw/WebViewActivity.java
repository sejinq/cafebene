package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jsw on 2015. 7. 16..
 */
public class WebViewActivity extends Activity {
    public static final String URL = "cosmantic.cosmantic_khw.WebViewActivity.URL";
    public static final String TITLE = "cosmantic.cosmantic_khw.WebViewActivity.TITLE";

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_webview);

        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String title = intent.getExtras().getString(TITLE);

        ((TextView) findViewById(R.id.titleText)).setText(title);

        String url = intent.getExtras().getString(URL);
        // WebViewClient 지정
        webview.setWebViewClient(new WebViewClientClass());

        // 구글홈페이지 지정
        webview.loadUrl(url);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
