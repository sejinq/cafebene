package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecommendIntroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_recommend_intro);

        // 버튼 리스너 달기
        (findViewById(R.id.btn1)).setOnClickListener(ClickListener);
        (findViewById(R.id.btn2)).setOnClickListener(ClickListener);
        (findViewById(R.id.btn3)).setOnClickListener(ClickListener);
    }

    // 버튼 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecommendIntroActivity.this, RecommendActivity.class);
            switch (v.getId()){
                case R.id.btn1:
                    intent.putExtra(RecommendActivity.SKIN_INFORMATION, User.SKIN_TYPE_OILY);
                    startActivity(intent);
                    break;
                case R.id.btn2:
                    intent.putExtra(RecommendActivity.SKIN_INFORMATION, User.SKIN_TYPE_DRY);
                    startActivity(intent);
                    break;
                case R.id.btn3:
                    intent.putExtra(RecommendActivity.SKIN_INFORMATION, User.SKIN_TYPE_SENSITIVE);
                    startActivity(intent);
                    break;
            }

        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
