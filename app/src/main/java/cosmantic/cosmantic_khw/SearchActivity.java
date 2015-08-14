package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search);

        // 임시로 보이지 않게 설정
        ((LinearLayout)findViewById(R.id.search_result)).setVisibility(View.GONE);
        ((RelativeLayout)findViewById(R.id.search_nosearch)).setVisibility(View.GONE);

        // 액션바 뒤로가기 버튼, 검색 버튼 리스너 달아주는 코드
        ((ImageButton) findViewById(R.id.backButton)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.searchButton)).setOnClickListener(ClickListener);
    }

    // 제품 클릭 시 동작하는 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    Toast.makeText(SearchActivity.this, "Back Button", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.searchButton:
                    if((int)(Math.random()*2)==0) {
                        ((LinearLayout) findViewById(R.id.search_result)).setVisibility(View.VISIBLE);
                        ((LinearLayout) findViewById(R.id.search_result)).invalidate();
                        ((RelativeLayout) findViewById(R.id.search_nosearch)).setVisibility(View.GONE);
                        ((RelativeLayout) findViewById(R.id.search_nosearch)).invalidate();
                    }
                    else {
                        ((RelativeLayout) findViewById(R.id.search_nosearch)).setVisibility(View.VISIBLE);
                        ((RelativeLayout) findViewById(R.id.search_nosearch)).invalidate();
                        ((LinearLayout) findViewById(R.id.search_result)).setVisibility(View.GONE);
                        ((LinearLayout) findViewById(R.id.search_result)).invalidate();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
