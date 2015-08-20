package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoDetailActivity extends Activity {
    int pageTab;
    Intent intent;
    String title;
    Page pageTag;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_info_detail);
        intent = getIntent();

        title = intent.getExtras().getString("pageTitle");
        pageTab = intent.getExtras().getInt("pageTag");

        showWeb(pageTab);

    }
    private void showWeb(int tab)
    {

        switch (tab)
        {
            case InformationActivity.web_pageFlag.PG_BAGIC_INFORM
                :settingBasic(getApplicationContext(), (RelativeLayout) findViewById(R.id.info_detail_basic), tab);
                ((TextView) findViewById(R.id.titleText)).setText(title);
                 break;
            case InformationActivity.web_pageFlag.PG_PRODUCT_REVIEW
                    :((TextView) findViewById(R.id.titleText)).setText(title);     break;
            case InformationActivity.web_pageFlag.PG_INGREDIENT
                    :setContentView(R.layout.activity_info_detail);
                ((TextView) findViewById(R.id.titleText)).setText(title);
                break;



        }
    }
    private void settingBasic(Context context, RelativeLayout view, int pageTab) {
        ListView lv;

        //컨텐츠 개수 서버에서 받아오기. 수정해야됨.
        int contentsNum = ((MyApplication) context).getProduct().getReviewNum();
       // lv = ((ListView) view.findViewById(R.id.listReview));//카드 내부에 이벤트 등록
        final WebContents[] contentsList = new WebContents[contentsNum];
        //contentsList[] 서버 메소드 return 값 받아오기, int pageTab값 넘겨주기.!!!!!!!!!!!!!!!!!!!!!!!!!!

        for (int i = 0; i < contentsNum; ++i) {
            contentsList[i] = new WebContents();
            final WebContents contents = contentsList[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.inform_detail_box, null);
            //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
            ((ImageView) layout.findViewById(R.id.detail_image)).setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(contents.getImage()));
            ((TextView) layout.findViewById(R.id.content_title)).setText(contents.getTitle());
            ((TextView) layout.findViewById(R.id.content_subtitle)).setText(contents.getSubTitle());
            //유저의 사진 클릭시 유저 정보창으로 넘어간다.
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                    nextIntent.putExtra("webview_title", "화장품 기초 상식");
                    nextIntent.putExtra("webview_url", contents.getUrl());
                    getApplicationContext().startActivity(nextIntent);
                }
            });
          //  lv.addView(layout); // 부모에 부착
        }
    }
       public interface Page{}

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
