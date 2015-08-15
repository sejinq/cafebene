package cosmantic.cosmantic_khw;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
