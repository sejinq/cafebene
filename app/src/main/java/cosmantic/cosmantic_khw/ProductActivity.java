package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProductActivity extends Activity {

    //new, hot 태그 , 제품이미지
    ImageView product;
    ImageView[] tag = new ImageView[2];
    //브랜드 이름, 제품명, 제품 별점 평균, actionbar title 텍스트
    TextView brand, name, average, title;
    ImageButton btShare;
    ImageButton review_write;
    ImageView[] star;
    Button[] smallTab = new Button[4];
    ImageButton btLike;
    String productId;
    //하위 탭 4개 생성 클래스
    SmallTab tabClass;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setView();
        //기본 정보들 설정
        new Thread(()->settingProduct()).start();
        //기본 탭으로 제품분석 탭 설정
        RelativeLayout view =  (RelativeLayout)findViewById(R.id.tablayout1);
        setting(view, findViewById(R.id.tab1));
        FontApplyer.setFont(getApplicationContext(), ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        tabClass = new ProductCurationTab(getApplicationContext(),view);
    }
    @Override
    protected void onResume(){
        super.onResume();
        //별 평균 적용
        ((MyApplication)getApplicationContext()).settingStar(star, average);
        //하트 눌려있으면 이미지도 on해주기.
        if(((MyApplication)getApplicationContext()).getUser().isLike(productId))
            btLike.setImageResource(R.drawable.love_inable);
    }
    private void setView(){
        setContentView(R.layout.activity_product);
        settingActionBar();
        // 제품박스 요소
        btLike = (ImageButton) findViewById(R.id.likebutton);
        btLike.setOnClickListener(onLike);
        product = (ImageView) findViewById(R.id.product_image);
        average = (TextView)findViewById(R.id.average);
        setFont();
        star = new ImageView[5];
        star[0] = (ImageView)findViewById(R.id.star1);
        star[1] = (ImageView)findViewById(R.id.star2);
        star[2] = (ImageView)findViewById(R.id.star3);
        star[3] = (ImageView)findViewById(R.id.star4);
        star[4] = (ImageView)findViewById(R.id.star5);

        settingSmallTab();

        review_write = (ImageButton) findViewById(R.id.review_pen_button);
        review_write.setOnClickListener(onReview);
    }
    private void setFont(){
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.brand_text)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.product_text)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.average)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
    }
    private void settingActionBar(){
        // 액션바 뒤로가기 버튼, 검색 버튼 리스너 달아주는 코드
        ((ImageButton) findViewById(R.id.backButton)).setOnClickListener(view->finish());
        ((ImageButton) findViewById(R.id.searchButton)).setImageResource(R.drawable.share_button);
        ((ImageButton) findViewById(R.id.searchButton)).setOnClickListener(view-> Log.d("PA","Click search button!"));
        TextView tv = (TextView) findViewById(R.id.titleText);
        tv.setText("화장품 상세");
        FontApplyer.setFont(this, tv, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
    }

/*별점 평균을 받아 평균에 맞는 별 이미지를 보여준다,*/
    private void settingProduct(){
        //정보 로드
        Product productObject = ((MyApplication) getApplicationContext()).getProduct();
        Bitmap image;
        if (productObject.getThumnail() != null) image = ((MyApplication) getApplicationContext()).getImage(productObject.getThumnail());
        else image = null;
        String brandText = productObject.getBrand();
        String nameText = productObject.getProductName();
        productId = ((MyApplication)getApplicationContext()).getProduct().getObjectId();

        //뷰 적용
        runOnUiThread(() -> {
            if (image != null)
                ((ImageView) findViewById(R.id.product_image)).setImageBitmap(image);
            ((TextView) findViewById(R.id.brand_text)).setText(brandText);
            ((TextView) findViewById(R.id.product_text)).setText(nameText);
        });
    }
    private void settingSmallTab()
    {
        smallTab[0] = (Button) findViewById(R.id.tab1);
        smallTab[0].setOnClickListener(new View.OnClickListener() {
            @Override
            //선택 시 frameInvisible로 탭 이미지 초기화. 보여줄 activity를 tabclass에서 액션 처리.
            public void onClick(View v) {
                RelativeLayout view =  (RelativeLayout)findViewById(R.id.tablayout1);
                //선택 될 탭의 내용만 활성화.
                setting(view, findViewById(R.id.tab1));
                FontApplyer.setFont(getApplicationContext(), ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                //RelativeLayout 정보와 ProductActivity의 content를 같이 넘겨주기.
                tabClass = new ProductCurationTab(getApplicationContext(),view);
            }
        });
        smallTab[1] = (Button) findViewById(R.id.tab2);
        smallTab[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                RelativeLayout view =  (RelativeLayout)findViewById(R.id.tablayout2);
                setting(view, findViewById(R.id.tab2));
                FontApplyer.setFont(getApplicationContext(), ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                tabClass = new ProductInformTab(getApplicationContext(),view);
            }
        });
        smallTab[2] = (Button) findViewById(R.id.tab3);
        smallTab[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.tablayout3);
                setting(view, findViewById(R.id.tab3));
                FontApplyer.setFont(getApplicationContext(), ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                tabClass = new PriceInformTab(ProductActivity.this, view);
            }
        });
        smallTab[3] = (Button) findViewById(R.id.tab4);
        smallTab[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.tablayout4);
                setting(view, findViewById(R.id.tab4));
                FontApplyer.setFont(getApplicationContext(), ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
                tabClass = new UserReviewTab(getApplicationContext(),view);
            }
        });

    }
    //레이아웃과 뷰의 visible, 선택시 이미지를 setting한다.
    private void setting(RelativeLayout layout, View view)
    {
        frameInvisible();
        layout.setVisibility(View.VISIBLE);
        view.setBackgroundResource(R.drawable.tap);
    }
    View.OnClickListener onShare = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };
    View.OnClickListener onLike = new View.OnClickListener() {
        public void onClick(View v) {
            //해당 제품을 찜 목록으로 등록, 이미 등록되어있으면 해제.
            if(!((MyApplication)getApplicationContext()).getUser().setLikeProducts(productId))
            {
                btLike.setImageResource(R.drawable.love_disable);
            }
            else
            {
                btLike.setImageResource(R.drawable.love_inable);
            }
        }
    };

    View.OnClickListener onReview = new View.OnClickListener() {
        public void onClick(View v) {
            //리뷰 쓴 제품인지 아닌지 검사.
            Intent Intent = new Intent(ProductActivity.this, WriteReviewActivity.class);
            //nextIntent.putExtra("",type);
            //nextIntent.putExtra("",id);
            startActivity(Intent);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            RelativeLayout view = (RelativeLayout) findViewById(R.id.tablayout4);
            setting(view, findViewById(R.id.tab4));
            FontApplyer.setFont(getApplicationContext(), ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
            tabClass = new UserReviewTab(getApplicationContext(),view);
        }
    }
    //초기화, 모두 비활성화.
    private void frameInvisible(){
        findViewById(R.id.tablayout1).setVisibility(View.GONE);
        findViewById(R.id.tablayout2).setVisibility(View.GONE);
        findViewById(R.id.tablayout3).setVisibility(View.GONE);
        findViewById(R.id.tablayout4).setVisibility(View.GONE);
        findViewById(R.id.tab1).setBackgroundResource(android.R.color.transparent);
        findViewById(R.id.tab2).setBackgroundResource(android.R.color.transparent);
        findViewById(R.id.tab3).setBackgroundResource(android.R.color.transparent);
        findViewById(R.id.tab4).setBackgroundResource(android.R.color.transparent);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public interface SmallTab{}
    public void changeActivity(Intent intent){
        startActivity(intent);
    }
}
