package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        ((TextView) findViewById(R.id.titleText)).setText(title);
        showWeb(pageTab);

    }
    private void showWeb(int tab)
    {

        switch (tab)
        {
            case InformationActivity.web_pageFlag.PG_BAGIC_INFORM
                :((LinearLayout) findViewById(R.id.info_detail_basic)).setVisibility(View.VISIBLE);
                settingBasic(getApplicationContext(), (LinearLayout) findViewById(R.id.info_detail_basic), tab);
                 break;
            case InformationActivity.web_pageFlag.PG_PRODUCT_REVIEW
                    : ((LinearLayout) findViewById(R.id.info_detail_review)).setVisibility(View.VISIBLE);
                settingReview(getApplicationContext(), (LinearLayout) findViewById(R.id.info_detail_review), tab);
                break;
            case InformationActivity.web_pageFlag.PG_INGREDIENT
                    :settingIngredient(getApplicationContext(), tab);
                ((RelativeLayout) findViewById(R.id.info_detail_ingredient)).setVisibility(View.VISIBLE);
                break;
        }
    }
    private void settingBasic(Context context, LinearLayout list, int pageTab) {
        //컨텐츠 개수 서버에서 받아오기. 수정해야됨.
        //int contentsNum = ((MyApplication) context).getProduct().getReviewNum();
        int contentsNum = 4;

        WebContents[] contentsList = new WebContents[contentsNum];
        //contentsList[] 서버 메소드 return 값 받아오기, int pageTab값 넘겨주기.!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (int i = 0; i < contentsNum; ++i) {
            contentsList[i] = new WebContents();
            final WebContents contents = contentsList[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.inform_detail_box, null);
            //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
            ((RelativeLayout)layout.findViewById(R.id.info_detail_box_default)).setVisibility(View.VISIBLE);
           // ((ImageButton) layout.findViewById(R.id.detail_image)).setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(contents.getImage()));
            ((TextView) layout.findViewById(R.id.content_title)).setText(contents.getTitle());
            ((TextView) layout.findViewById(R.id.content_subtitle)).setText(contents.getSubTitle());
            //유저의 사진 클릭시 유저 정보창으로 넘어간다.
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                    nextIntent.putExtra("webview_title", "화장품 기초 상식");
                    nextIntent.putExtra("webview_url", contents.getUrl());
                    callIntent(nextIntent);
                }
            });
          list.addView(layout); // 부모에 부착
        }
    }
    //화장품 성분
    private void settingIngredient(Context context, int pageTab)
    {
        WebContents[] contentsList = new WebContents[3];
        RelativeLayout[] tab = new RelativeLayout[3];
        tab[0]=(RelativeLayout) findViewById(R.id.info_detail_ingredient_tab1);
        tab[1]=(RelativeLayout) findViewById(R.id.info_detail_ingredient_tab2);
        tab[2]=(RelativeLayout) findViewById(R.id.info_detail_ingredient_tab3);

        for(int i=0;i<3;++i)
        {
            contentsList[i] = new WebContents();
            final WebContents listenerContent = contentsList[i];
            ((RelativeLayout)tab[i].findViewById(R.id.info_detail_box_default)).setVisibility(View.VISIBLE);
           // ((ImageButton) tab[i].findViewById(R.id.detail_image)).setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(listenerContent.getImage()));
            ((TextView) tab[i].findViewById(R.id.content_title)).setText(listenerContent.getTitle());
            ((TextView) tab[i].findViewById(R.id.content_subtitle)).setText(listenerContent.getSubTitle());

            tab[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                    nextIntent.putExtra("webview_title", "화장품 성분");
                    nextIntent.putExtra("webview_url", listenerContent.getUrl());
                    callIntent(nextIntent);
                }
            });
        }

    }
    private void settingReview(Context context, LinearLayout list, int pageTab)
    {
        //int contentsNum = ((MyApplication) context).getProduct().getReviewNum();
        int contentsNum = 4;

        final WebContents[] contentsList = new WebContents[contentsNum];
        //contentsList[] 서버 메소드 return 값 받아오기, int pageTab값 넘겨주기.!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (int i = 0; i < contentsNum; ++i) {
            contentsList[i] = new WebContents();
            final WebContents contents = contentsList[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.inform_detail_box, null);
            //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
            ((RelativeLayout)layout.findViewById(R.id.info_detail_box_review)).setVisibility(View.VISIBLE);
            //이미지 받아오기
            /*((ImageButton) layout.findViewById(R.id.detail_image)).
                    setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(contentsList[i].getImage()));
            ((ImageButton) layout.findViewById(R.id.info_detail_product_image)).setImageBitmap(((MyApplication) getApplicationContext()).
                    getImage(contents.getProductImage());
            ((TextView) layout.findViewById(R.id.review_brand)).setText(contents.getProductBrand());
            ((TextView) layout.findViewById(R.id.review_name)).setText(contents.getProductName());
            */
            ((ImageButton) layout.findViewById(R.id.inform_detail_product_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyApplication) getApplicationContext()).setProduct(ServerInteraction.getProductInform(contents.getProduct_objectId()));
                    Intent nextIntent = new Intent(getApplicationContext(), ProductActivity.class);
                    callIntent(nextIntent);
                }
            });
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                    nextIntent.putExtra("webview_title", "추천 제품 리뷰");
                    nextIntent.putExtra("webview_url", contents.getUrl());
                    callIntent(nextIntent);
                }
            });
            list.addView(layout); // 부모에 부착
        }


    }
        private void callIntent(Intent intent)
    {
        startActivity(intent);
    }
       public interface Page{}

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
