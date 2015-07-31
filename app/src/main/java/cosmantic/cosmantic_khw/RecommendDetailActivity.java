package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommenddetail);

        // 액션바 제목 추가 및 글꼴 변경
        TextView mTitleTextView = (TextView)findViewById(R.id.titleText);
        mTitleTextView.setText("지성용 화장품");
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);
    }

    // 액션바 뒤로가기 버튼 리스너
    public void mClickListener(View v){
       switch (v.getId()) {
            case R.id.product1_left_image:
            case R.id.product1_center_image:
            case R.id.product1_right_image:
            case R.id.product2_left_image:
            case R.id.product2_center_image:
            case R.id.product2_right_image:
            case R.id.product3_left_image:
            case R.id.product3_center_image:
            case R.id.product3_right_image:
                Toast.makeText(RecommendDetailActivity.this, "productButton", Toast.LENGTH_SHORT).show();
                break;
            case R.id.backButton:
                Toast.makeText(RecommendDetailActivity.this, "backButton", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
