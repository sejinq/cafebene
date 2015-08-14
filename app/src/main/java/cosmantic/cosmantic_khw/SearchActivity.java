package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search);

        // 액션바 제목은 안 쓰므로 안 보이게 설정
        TextView textView = (TextView)findViewById(R.id.titleText);
        textView.setVisibility(View.GONE);

        //
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
