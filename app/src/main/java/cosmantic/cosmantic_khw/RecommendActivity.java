package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class RecommendActivity extends Activity{
    private int flag_info;
    // 키 값 불러오기
    public static final String RECOMMEND_KEY = "cosmantic.cosmantic_khw.RecommendActivity.RECOMMEND_KEY";

    private Product[] products;
    LinearLayout[] containers;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        // 이전 액티비티에서 어떤 추천 화장품을 보여줄 것인지 넘겨준 데이터를 putIntExtra 해서 받아준다.
        flag_info = this.getIntent().getIntExtra(RECOMMEND_KEY, 0);

        // 액션바 제목 추가 및 글꼴 적용
        TextView textView = (TextView)findViewById(R.id.titleText);

        switch(flag_info){
            case 0:
                // 에러 발생이므로 강제 종료
                Log.e("RecommendActivity", "skin_type 확인");
                android.os.Process.killProcess(android.os.Process.myPid());
            case User.SKIN_TYPE_OILY:
                textView.setText("추천 지성용 화장품");
                break;
            case User.SKIN_TYPE_DRY:
                textView.setText("추천 건성용 화장품");
                break;
            case User.SKIN_TYPE_SENSITIVE:
                textView.setText("추천 민감성용 화장품");
                break;
            case User.SEASON_SOLDIER:
                textView.setText("군인을 위한 화장품");
                break;
        }

        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 검색 버튼은 안 쓰므로 안 보이게 설정
        ImageButton imgButton = (ImageButton)findViewById(R.id.searchButton);
        imgButton.setVisibility(View.GONE);

        // 액션바 뒤로가기 버튼 리스너 달아주는 코드
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(ClickListener);

        // 제품 목록 받아오기
        String[] pids = ServerInteraction.getRecommendList(flag_info);
        if(pids == null) finish();
        products = new Product[pids.length];

        setContainer(pids);
    }

    private void setContainer(String[] pids){

        LinearLayout frame = (LinearLayout)findViewById(R.id.recommend_frame);
        switch (flag_info){
            case User.SKIN_TYPE_OILY:
            case User.SKIN_TYPE_DRY:
            case User.SKIN_TYPE_SENSITIVE:
                containers = new LinearLayout[5];
                for(int i=0; i < 5; i++) {
                    containers[i] = (LinearLayout)View.inflate(this,R.layout.recommend_product,null);
                    frame.addView(containers[i]);
                    ((TextView)containers[i].findViewById(R.id.product_title)).setText(this.getResources().getIdentifier("recommend_default_" + i, "string", "cosmantic.cosmantic_khw"));
                    settingProductLayer(containers[i]);
                }
                break;
            case User.SEASON_SOLDIER:
                containers = new LinearLayout[3];
                for(int i=0; i < 3; i++) {
                    containers[i] = (LinearLayout)View.inflate(this,R.layout.recommend_product,null);
                    frame.addView(containers[i]);
                    ((TextView)containers[i].findViewById(R.id.product_title)).setText(this.getResources().getIdentifier("recommend_soldier_" + i, "string", "cosmantic.cosmantic_khw"));
                    settingProductLayer(containers[i]);
                }
                break;
        }
        for(int loop = 0; loop < pids.length; loop++){
            final int index = loop;
            new Thread(()->setEachProduct(pids[index], index)).start();
        }
    }

    private void setEachProduct(String productID, final int index){
        Product product = ServerInteraction.getProductInform(productID);
        products[index] = product;
        LinearLayout parent = containers[index/3];
        runOnUiThread(() -> {
            switch (index % 3) {
                case 0:
                    if (product.getThumnail() != null)
                        ((ImageView) parent.findViewById(R.id.product_left_image))
                                .setImageBitmap(((MyApplication) getApplicationContext()).getImage(product.getThumnail()));
                    ((TextView) parent.findViewById(R.id.product_left_brandname)).setText(product.getBrandKor());
                    String name = product.getProductName();
                    if (name.length() <= 8)
                        ((TextView) parent.findViewById(R.id.product_left_productname)).setText(name);
                    else
                        ((TextView) parent.findViewById(R.id.product_left_productname)).setText(name.substring(0, 7) + "...");
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    ((TextView) parent.findViewById(R.id.product_left_priceandvolume))
                            .setText(product.getSize() + " / " + formatter.format(product.getPrice())+"원");
                    parent.findViewById(R.id.product_left).setOnClickListener(view -> setProductListener(index));
                    break;
                case 1:
                    if (product.getThumnail() != null)
                        ((ImageView) parent.findViewById(R.id.product_center_image))
                                .setImageBitmap(((MyApplication) getApplicationContext()).getImage(product.getThumnail()));
                    ((TextView) parent.findViewById(R.id.product_center_brandname)).setText(product.getBrandKor());
                    name = product.getProductName();
                    if (name.length() <= 8)
                        ((TextView) parent.findViewById(R.id.product_center_productname)).setText(name);
                    else
                        ((TextView) parent.findViewById(R.id.product_center_productname)).setText(name.substring(0, 7) + "...");
                    formatter = new DecimalFormat("#,###,###");
                    ((TextView) parent.findViewById(R.id.product_center_priceandvolume))
                            .setText(product.getSize() + " / " + formatter.format(product.getPrice())+"원");
                    parent.findViewById(R.id.product_center).setOnClickListener(view -> setProductListener(index));
                    break;
                default:
                    if (product.getThumnail() != null)
                        ((ImageView) parent.findViewById(R.id.product_right_image))
                                .setImageBitmap(((MyApplication) getApplicationContext()).getImage(product.getThumnail()));
                    ((TextView) parent.findViewById(R.id.product_right_brandname)).setText(product.getBrandKor());
                    name = product.getProductName();
                    if (name.length() <= 8)
                        ((TextView) parent.findViewById(R.id.product_right_productname)).setText(name);
                    else
                        ((TextView) parent.findViewById(R.id.product_right_productname)).setText(name.substring(0, 7) + "...");
                    formatter = new DecimalFormat("#,###,###");
                    ((TextView) parent.findViewById(R.id.product_right_priceandvolume))
                            .setText(product.getSize() + " / " + formatter.format(product.getPrice())+"원");
                    parent.findViewById(R.id.product_right).setOnClickListener(view -> setProductListener(index));
            }
        });
    }

    private void setProductListener(int index){
        ((MyApplication) getApplicationContext()).setProduct(products[index]);
        Intent intent = new Intent(RecommendActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    // 제품 컨테이너 안에 있는 글씨들 글꼴 적용
    private void settingProductLayer(LinearLayout container){

        // 분류명
        TextView textView = (TextView)container.findViewById(R.id.product_title);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);

        // 브랜드명
        textView = (TextView)container.findViewById(R.id.product_left_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)container.findViewById(R.id.product_center_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)container.findViewById(R.id.product_right_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 제품명
        textView = (TextView)container.findViewById(R.id.product_left_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)container.findViewById(R.id.product_center_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)container.findViewById(R.id.product_right_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 용량 및 가격
        textView = (TextView)container.findViewById(R.id.product_left_priceandvolume);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)container.findViewById(R.id.product_center_priceandvolume);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)container.findViewById(R.id.product_right_priceandvolume);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 제품 클릭시 리스너를 나타내주기 위한 코드
        (container.findViewById(R.id.product_left)).setOnClickListener(ClickListener);
        (container.findViewById(R.id.product_center)).setOnClickListener(ClickListener);
        (container.findViewById(R.id.product_right)).setOnClickListener(ClickListener);
    }

    // 제품 클릭 시 동작하는 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    finish();
                    break;
                case R.id.product_left:
                    Toast.makeText(RecommendActivity.this, "로딩중입니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_center:
                    Toast.makeText(RecommendActivity.this, "로딩중입니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_right:
                    Toast.makeText(RecommendActivity.this, "로딩중입니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
        }
        return true;
    }

//    private void setProduct(){
//        Product[] products = ServerInteraction.getRecommendList(flag_info);
//        int img_id = R.id.product_left_image;
//        int brand_id = R.id.product_left_brandname;
//        int product_id = R.id.product_left_productname;
//        int price_and_volume_id = R.id.product_left_priceandvolume;
//
//        for(int p_loop = 0; p_loop<products.length;p_loop++){
//            //이미지
//            byte[] object = products[p_loop].getThumnail();
//            Drawable img = new BitmapDrawable(BitmapFactory.decodeByteArray(object, 0, object.length));
//            ImageView imgButton = (ImageView) findViewById(R.id.product_left_image);
//            imgButton.setImageDrawable(img);
//        }
//    }

    private void onProductClick(int loc){
        // click animation
//        int id = products[0].getId();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
