package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search);

        // 임시로 보이지 않게 설정
        ((LinearLayout)findViewById(R.id.search_result)).setVisibility(View.GONE);
        ((RelativeLayout)findViewById(R.id.search_nosearch)).setVisibility(View.GONE);

        // 액션바 뒤로가기 버튼, 검색 버튼 리스너 달아주는 코드
        ((ImageButton)findViewById(R.id.backButton)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.searchButton)).setOnClickListener(ClickListener);

        // EditText에서 Enter키를 눌렀을 때 검색이 되게 하도록 리스너 달아줌.
        ((EditText)findViewById(R.id.actionbar_search_edittext)).setOnKeyListener(KeyListener);
    }

    // 제품 클릭 시 동작하는 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    finish();
                    break;
                // 검색 버튼 누르면 검색창이 떠야 하는데
                // 서버 통신이 안 되는 관계로 랜덤하게 xml 창이 뜨도록 해놓음.
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

    // 키 입력시 동작하는 리스너
    View.OnKeyListener KeyListener = new View.OnKeyListener(){
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
            // EditText에서 Enter를 눌러도 검색이 되도록 설정.
            if(keyCode==KeyEvent.KEYCODE_ENTER){
                ClickListener.onClick(findViewById(R.id.searchButton));
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
