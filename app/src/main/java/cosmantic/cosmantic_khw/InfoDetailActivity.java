package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class InfoDetailActivity extends Activity {
    int pageTab;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_info_detail);

        Intent intent = getIntent();
        pageTab = intent.getExtras().getInt("pageNum");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
