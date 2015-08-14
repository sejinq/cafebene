package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendDetailActivity extends Activity{
    public static String SKIN_INFORMATION = "cosmantic.cosmantic_khw.RecommendDetailActivity.skin_type";
    private int skin_type;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommenddetail);

        skin_type = this.getIntent().getIntExtra(SKIN_INFORMATION,0);

        // 액션바 제목 추가 및 글꼴 적용
        TextView textView = (TextView)findViewById(R.id.titleText);
        textView.setText("지성용 화장품");
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);

        // 검색 버튼은 안 쓰므로 안 보이게 설정
        ImageButton imgButton = (ImageButton)findViewById(R.id.searchButton);
        imgButton.setVisibility(View.GONE);

        // 나머지 글씨들 글꼴 적용
        settingProductLayer(R.id.product_layer1);
        settingProductLayer(R.id.product_layer2);
        settingProductLayer(R.id.product_layer3);
        settingProductLayer(R.id.product_layer4);
        settingProductLayer(R.id.product_layer5);

        // 액션바 뒤로가기 버튼 리스너 달아주는 코드
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(ClickListener);
    }

    private void settingProductLayer(int layoutId){
        Log.d("RDetail", "Draw init");

        LinearLayout container = (LinearLayout)findViewById(layoutId);

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
                    Toast.makeText(RecommendDetailActivity.this, "Back Button", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_left:
                    Toast.makeText(RecommendDetailActivity.this, "product_left", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_center:
                    Toast.makeText(RecommendDetailActivity.this, "product_center", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_right:
                    Toast.makeText(RecommendDetailActivity.this, "product_right", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void setProduct(){
        Product[] products = ServerInteraction.getRecommendList(skin_type);
        int img_id = R.id.product_left_image;
        int brand_id = R.id.product_left_brandname;
        int product_id = R.id.product_left_image;

        for(int p_loop = 0; p_loop<products.length;p_loop++){
            //이미지
            byte[] object = products[p_loop].getThumnail();
            Drawable img = new BitmapDrawable(BitmapFactory.decodeByteArray(object, 0, object.length));
            ImageView imgButton = (ImageView) findViewById(R.id.product_left_image);
            imgButton.setImageDrawable(img);
        }
    }

    private void onProductClick(int loc){
        //click animation
//        int id = products[0].getId();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
