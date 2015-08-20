package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendActivity extends Activity{
    private int flag_info;
    // 키 값 불러오기
    public static final String RECOMMEND_KEY = "cosmantic.cosmantic_khw.RecommendActivity.RECOMMEND_KEY";

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
            case 1:
                textView.setText("추천 지성용 화장품");
                break;
            case 2:
                textView.setText("추천 건성용 화장품");
                break;
            case 3:
                textView.setText("추천 민감성용 화장품");
                break;
            case 4:
                textView.setText("군인을 위한 화장품");
                break;
        }

        FontApplyer.setFont(this, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 검색 버튼은 안 쓰므로 안 보이게 설정
        ImageButton imgButton = (ImageButton)findViewById(R.id.searchButton);
        imgButton.setVisibility(View.GONE);

        // 제품 분류바 글씨
        ((TextView)(findViewById(R.id.product_layer1)).findViewById(R.id.product_title)).setText("추천 올인원");
        ((TextView)(findViewById(R.id.product_layer2)).findViewById(R.id.product_title)).setText("추천 스킨");
        ((TextView)(findViewById(R.id.product_layer3)).findViewById(R.id.product_title)).setText("추천 로션");
        ((TextView)(findViewById(R.id.product_layer4)).findViewById(R.id.product_title)).setText("추천 썬크림");
        ((TextView)(findViewById(R.id.product_layer5)).findViewById(R.id.product_title)).setText("추천 폼클렌징");

        // 나머지 글씨들 글꼴 적용
        for(int i=1;i<=5;i++){
            int resourceId = getResources().getIdentifier("product_layer"+i, "id", "cosmantic.cosmantic_khw");
            settingProductLayer(resourceId);
        }

        // 액션바 뒤로가기 버튼 리스너 달아주는 코드
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(ClickListener);
    }

    // 제품 컨테이너 안에 있는 글씨들 글꼴 적용
    private void settingProductLayer(int layoutId){
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
                    finish();
                    break;
                case R.id.product_left:
                    Toast.makeText(RecommendActivity.this, "product_left", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_center:
                    Toast.makeText(RecommendActivity.this, "product_center", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_right:
                    Toast.makeText(RecommendActivity.this, "product_right", Toast.LENGTH_SHORT).show();
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

    // 서버에 관한 메서드인데 미완.
    private void setProduct(){
        Product[] products = ServerInteraction.getRecommendList(flag_info);
        int img_id = R.id.product_left_image;
        int brand_id = R.id.product_left_brandname;
        int product_id = R.id.product_left_productname;
        int price_and_volume_id = R.id.product_left_priceandvolume;

        for(int p_loop = 0; p_loop<products.length;p_loop++){
            //이미지
            byte[] object = products[p_loop].getThumnail();
            Drawable img = new BitmapDrawable(BitmapFactory.decodeByteArray(object, 0, object.length));
            ImageView imgButton = (ImageView) findViewById(R.id.product_left_image);
            imgButton.setImageDrawable(img);
        }
    }

    private void onProductClick(int loc){
        // click animation
//        int id = products[0].getId();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
