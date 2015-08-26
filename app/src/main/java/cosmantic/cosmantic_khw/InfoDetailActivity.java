package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class InfoDetailActivity extends Activity {
    public static final String TITLE = "cosmantic.cosmantic_khw.InfoDetailActivity.TITLE";
    public static final String PAGE_TAG = "cosmantic.cosmantic_khw.InfoDetailActivity.PAGE_TAG";

    int pageTab;
    Intent intent;
    String title;
    Page pageTag;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_info_detail);
        intent = getIntent();

        ((ImageButton)findViewById(R.id.searchButton)).setVisibility(View.GONE);
        title = intent.getExtras().getString(TITLE);
        pageTab = intent.getExtras().getInt(PAGE_TAG);
        TextView tv = (TextView) findViewById(R.id.titleText);
        tv.setText(title);
        FontApplyer.setFont(this, tv, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        showWeb(pageTab);

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(ClickListener);

    }
    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    finish();
                    break;
            }
        }
    };
    private void setFont(RelativeLayout layout)
    {
        FontApplyer.setFont(getApplicationContext(), ((TextView) layout.findViewById(R.id.content_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(getApplicationContext(), ((TextView) layout.findViewById(R.id.content_subtitle)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        //  FontApplyer.setFont(getApplicationContext(), ((TextView) layout.findViewById(R.id.review_brand)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        //review_name
    }
    private void setReviewFont(RelativeLayout layout)
    {
        FontApplyer.setFont(getApplicationContext(), ((TextView) layout.findViewById(R.id.review_brand)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(getApplicationContext(), ((TextView) layout.findViewById(R.id.review_name)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        //  FontApplyer.setFont(getApplicationContext(), ((TextView) layout.findViewById(R.id.review_brand)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        //review_name
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
        ParseQuery<ParseObject> basicQuery = ParseQuery.getQuery("webContents");
        basicQuery.whereEqualTo("type",2);
        basicQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> contentslist, ParseException e) {
                //컨텐츠 개수 서버에서 받아오기.
                int contentsNum = contentslist.size();
                WebContents[] contentsList = new WebContents[contentsNum];
                //서버 받아오면 됨. 생성 필요 음슴.
                try {
                    for (int loop = 0; loop < contentslist.size(); loop++) {
                        ParseObject object = contentslist.get(loop);
                        contentsList[loop] = new WebContents((object.getParseFile("image") == null) ? null : object.getParseFile("image").getData(),
                                object.getString("title"),object.getString("subTitle"),null,object.getString("url"));
                    }
                }catch(ParseException pe){
                    pe.printStackTrace();
                }
                //contentsList =
                //contentsList[] 서버 메소드 return 값 받아오기, int pageTab값 넘겨주기.!!!!!!!!!!!!!!!!!!!!!!!!!!
                runOnUiThread(()->{
                    for (int i = 0; i < contentsNum; ++i) {
                        final WebContents contents = contentsList[i];
                        RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.inform_detail_box, null);
                        //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
                        setFont(layout);
                        ((RelativeLayout)layout.findViewById(R.id.info_detail_box_default)).setVisibility(View.VISIBLE);
                        ((ImageView) layout.findViewById(R.id.detail_image)).setImageBitmap(((MyApplication) context).getImage(contents.getImage()));
                        ((TextView) layout.findViewById(R.id.content_title)).setText(contents.getTitle());
                        ((TextView) layout.findViewById(R.id.content_subtitle)).setText(contents.getSubTitle());
                        FontApplyer.setFont(InfoDetailActivity.this, ((TextView) findViewById(R.id.content_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                        FontApplyer.setFont(InfoDetailActivity.this, ((TextView) findViewById(R.id.content_subtitle)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
                        //유저의 사진 클릭시 유저 정보창으로 넘어간다.
                        layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent nextIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                                nextIntent.putExtra(WebViewActivity.TITLE, "화장품 기초 상식");
                                nextIntent.putExtra(WebViewActivity.ACTIONBAR, MyApplication.action_bar_tag.AC_SUB);
                                nextIntent.putExtra(WebViewActivity.URL, contents.getUrl());
                                callIntent(nextIntent);
                            }
                        });
                        list.addView(layout); // 부모에 부착
                    }
                });
            }

        });
    }
                //화장품 성분
    private void settingIngredient(Context context, int pageTab)
    {
        ParseQuery<ParseObject> ingreQuery = ParseQuery.getQuery("webContents");
        ingreQuery.whereEqualTo("type",3);
        ingreQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> contentslist, ParseException e) {
                //서버 받아오면 됨. 생성 필요 음슴.
                int contentsNum = contentslist.size();
                WebContents[] contentsList = new WebContents[contentsNum];
                WebContents[] temp = new WebContents[contentsNum];
                //서버 받아오면 됨. 생성 필요 음슴.
                try {
                    for (int loop = 0; loop < contentslist.size(); loop++) {
                        ParseObject object = contentslist.get(loop);
                        temp[loop] = new WebContents((object.getParseFile("image") == null) ? null : object.getParseFile("image").getData(),
                                object.getString("title"),object.getString("subTitle"),null,object.getString("url"));
                    }
                }catch(ParseException pe){
                    pe.printStackTrace();
                }
                contentsList[2] = temp[1];
                contentsList[1] = temp[2];
                contentsList[0] = temp[0];
                runOnUiThread(()->{
                    RelativeLayout[] tab = new RelativeLayout[3];
                    tab[0]=(RelativeLayout) findViewById(R.id.info_detail_ingredient_tab1);
                    tab[1]=(RelativeLayout) findViewById(R.id.info_detail_ingredient_tab2);
                    tab[2]=(RelativeLayout) findViewById(R.id.info_detail_ingredient_tab3);

                    for(int i=2;i>=0;--i)
                    {
//                        contentsList[i] = new WebContents();
                        final WebContents listenerContent = contentsList[i];
                        ((RelativeLayout)tab[i].findViewById(R.id.info_detail_box_default)).setVisibility(View.VISIBLE);
                        setFont(tab[i]);
                        ((ImageView) tab[i].findViewById(R.id.detail_image)).setImageBitmap(((MyApplication) context).getImage(listenerContent.getImage()));
                        // ((ImageButton) tab[i].findViewById(R.id.detail_image)).setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(listenerContent.getImage()));
                        ((TextView) tab[i].findViewById(R.id.content_title)).setText(listenerContent.getTitle());
                        ((TextView) tab[i].findViewById(R.id.content_subtitle)).setText(listenerContent.getSubTitle());
                        FontApplyer.setFont(InfoDetailActivity.this, ((TextView) findViewById(R.id.content_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                        FontApplyer.setFont(InfoDetailActivity.this, ((TextView) findViewById(R.id.content_subtitle)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

                        tab[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent nextIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                                nextIntent.putExtra(WebViewActivity.TITLE, "화장품 성분");
                                nextIntent.putExtra(WebViewActivity.URL, listenerContent.getUrl());
                                nextIntent.putExtra(WebViewActivity.ACTIONBAR, MyApplication.action_bar_tag.AC_SUB);
                                callIntent(nextIntent);
                            }
                        });
                    }
                });
            }
        });

    }
    private void settingReview(Context context, LinearLayout list, int pageTab)
    {
        ParseQuery<ParseObject> reviewQuery = ParseQuery.getQuery("webContents");
        reviewQuery.whereEqualTo("type",1);
        reviewQuery.whereExists("image");
        reviewQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> contentslist, ParseException e) {
                //서버 받아오면 됨. 생성 필요 음슴.
                int contentsNum = contentslist.size();
                WebContents[] contentsList = new WebContents[contentsNum];
                //서버 받아오면 됨. 생성 필요 음슴.
                try {
                    for (int loop = 0; loop < contentslist.size(); loop++) {
                        ParseObject object = contentslist.get(loop);
                        contentsList[loop] = new WebContents((object.getParseFile("image") == null) ? null : object.getParseFile("image").getData(),
                                object.getString("title"),object.getString("subTitle"),object.getString("productObjectId"),object.getString("url"));
                    }
                }catch(ParseException pe){
                    pe.printStackTrace();
                }
                runOnUiThread(() -> {
                    for (int i = 0; i < contentsNum; ++i) {
//                        contentsList[i] = new WebContents();
                        final WebContents contents = contentsList[i];
                        RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.inform_detail_box, null);
                        setReviewFont(layout);
                        //컨텐츠 이미지, 제품 이미지, 브랜드, 이름 받아와서 보여주기.
                        ((RelativeLayout) layout.findViewById(R.id.info_detail_box_review)).setVisibility(View.VISIBLE);
                        //이미지 받아오기
                        if(contentsList[i].getImage() == null) continue;
                        ((ImageView) layout.findViewById(R.id.detail_image)).
                                setImageBitmap(((MyApplication) context).getImage(contentsList[i].getImage()));
                        ((ImageView) layout.findViewById(R.id.info_detail_product_image)).
                                setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(contentsList[i].getProductImage()));
                        ((TextView) layout.findViewById(R.id.review_brand)).setText(contents.getProductBrand());
                        ((TextView) layout.findViewById(R.id.review_name)).setText(contents.getProductName());
                        FontApplyer.setFont(InfoDetailActivity.this, ((TextView) findViewById(R.id.review_brand)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                        FontApplyer.setFont(InfoDetailActivity.this, ((TextView) findViewById(R.id.review_name)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

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
                                nextIntent.putExtra(WebViewActivity.TITLE, "추천 제품 리뷰");
                                nextIntent.putExtra(WebViewActivity.URL, contents.getUrl());
                                nextIntent.putExtra(WebViewActivity.ACTIONBAR, MyApplication.action_bar_tag.AC_SUB);
                                callIntent(nextIntent);
                            }
                        });
                        list.addView(layout); // 부모에 부착
                    }
                });
            }

        });
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