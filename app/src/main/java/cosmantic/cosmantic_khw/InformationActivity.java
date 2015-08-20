package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by jsw on 2015. 7. 16..손세지니 시작한댜야ㅑㅑㅑㅑ8월 18일!!!!!!!!!
 */
public class InformationActivity extends Activity {
    //page 탭 번호. 2번째 탭 선택 : 1, 3번빼 탭 : 2, 4번째 탭:3,
    int page;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_information);
        ((ImageButton) this.findViewById(R.id.inform_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //바로 웹뷰로 넘어감. 피부타입 테스트
                }
        });
        ((ImageButton) this.findViewById(R.id.inform_2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                page = 0;
                nextIntent.putExtra("pageNum",page);
                startActivity(nextIntent);
            }
        });
        ((ImageButton) this.findViewById(R.id.inform_3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                page = 1;
                nextIntent.putExtra("pageNum",page);
                startActivity(nextIntent);
            }
        });
        ((ImageButton) this.findViewById(R.id.inform_4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(InformationActivity.this, InfoDetailActivity.class);
                page = 2;
                nextIntent.putExtra("pageNum",page);
                startActivity(nextIntent);
            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
