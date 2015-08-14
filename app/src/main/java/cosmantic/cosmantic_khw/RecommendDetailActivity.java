package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class RecommendDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_reviewwrite);
        EditText et = (EditText) findViewById(R.id.editText);
        et.setMovementMethod(null);

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
