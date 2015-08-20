package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jsw on 2015. 7. 16..
 */
public class WebViewActivity extends Activity {
    public static final String URL = "cosmantic.cosmantic_khw.WebViewActivity.URL";
    public static final String TITLE = "cosmantic.cosmantic_khw.WebViewActivity.TITLE";

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_webview);

        webView = (WebView)findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra(URL));
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
