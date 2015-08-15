package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
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
        TextView textView = (TextView)findViewById(R.id.titletext);
        textView.setText("지성용 화장품");
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);

        // 나머지 글씨들 글꼴 적용
        // 분류명
        textView = (TextView)findViewById(R.id.product1_title);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        textView = (TextView)findViewById(R.id.product2_title);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        textView = (TextView)findViewById(R.id.product3_title);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);

        // 브랜드명
        textView = (TextView)findViewById(R.id.product1_left_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product1_center_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product1_right_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product2_left_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product2_center_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product2_right_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product3_left_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product3_center_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)findViewById(R.id.product3_right_brandname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 제품명
        textView = (TextView)findViewById(R.id.product1_left_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product1_center_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product1_right_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product2_left_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product2_center_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product2_right_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product3_left_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product3_center_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)findViewById(R.id.product3_right_productname);
        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 검색 버튼은 안 쓰므로 안 보이게 설정
        ImageButton imgButton = (ImageButton)findViewById(R.id.searchButton);
        imgButton.setVisibility(View.GONE);

        // 제품 클릭시 리스너를 나타내주기 위한 코드
        LinearLayout productLayout = (LinearLayout)findViewById(R.id.product1_left);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product1_center);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product1_right);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product2_left);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product2_center);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product2_right);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product3_left);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product3_center);
        productLayout.setOnTouchListener(TouchListener);
        productLayout = (LinearLayout)findViewById(R.id.product3_right);
        productLayout.setOnTouchListener(TouchListener);

        // 액션바 뒤로가기 버튼 리스너 달아주는 코드
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnTouchListener(TouchListener);

    }

    // 제품 클릭 시 동작하는 리스너
    View.OnTouchListener TouchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            // 터치할 때
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                switch(v.getId()){
                    case R.id.backButton:
                        Toast.makeText(RecommendDetailActivity.this, "Back Button", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product1_left:
                        Toast.makeText(RecommendDetailActivity.this, "product1_left", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product1_center:
                        Toast.makeText(RecommendDetailActivity.this, "product1_center", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product1_right:
                        Toast.makeText(RecommendDetailActivity.this, "product1_right", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product2_left:
                        Toast.makeText(RecommendDetailActivity.this, "product2_left", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product2_center:
                        Toast.makeText(RecommendDetailActivity.this, "product2_center", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product2_right:
                        Toast.makeText(RecommendDetailActivity.this, "product2_right", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product3_left:
                        Toast.makeText(RecommendDetailActivity.this, "product3_left", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product3_center:
                        Toast.makeText(RecommendDetailActivity.this, "product3_center", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.product3_right:
                        Toast.makeText(RecommendDetailActivity.this, "product3_right", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }

            // 터치하고 뗄 때
            if (event.getAction() == MotionEvent.ACTION_UP) {
                switch (v.getId()){
                }
            }
            return false;
        }
    };

    private void setProduct(){
        Product[] products = ServerInteraction.getRecommendList(skin_type);
        int img_id = R.id.product1_left_image;
        int brand_id = R.id.product1_left_brandname;
        int product_id = R.id.product1_left_image;

        for(int p_loop = 0; p_loop<products.length;p_loop++){
            //이미지
            byte[] object = products[p_loop].getThumnail();
            Drawable img = new BitmapDrawable(BitmapFactory.decodeByteArray(object, 0, object.length));
            ImageView imgButton = (ImageView) findViewById(R.id.product1_left_image);
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
