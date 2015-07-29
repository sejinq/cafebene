package cosmantic.cosmantic_khw;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommenddetail);

        // 액션바 생성 및 제목 추가
        TextView mTitleTextView = (TextView) View.findViewById(R.id.titletext);
        mTitleTextView.setText("지성용 화장품");
        FontApplyer.setFont(this, mTitleTextView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);

        // 액션바 뒤로가기 버튼 리스너
        ImageButton backButton = (ImageButton) View.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Button Clicked!", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
