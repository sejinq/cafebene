package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class HomeActivity extends Activity {
    private ViewFlipper viewFlipper;
    int flipperCount = 0;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_home);

        // 액션바의 홈 버튼에 배경 설정
        Button btn = (Button) findViewById(R.id.tab1);
        btn.setBackgroundResource(R.drawable.menutap);

        // 글꼴 설정
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab1)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.tab4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, ((TextView) findViewById(R.id.home_mainbar_txt)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);

        // 나머지 글씨들 글꼴 적용
        for (int i = 1; i < 3; i++) {
            int resourceId = getResources().getIdentifier("home_productLayer" + i, "id", "cosmantic.cosmantic_khw");
            settingProductLayer(resourceId);
        }

        // 상단 레이아웃 리스너 달기
        ((ViewFlipper) findViewById(R.id.home_product_container)).setOnTouchListener(TouchListener);

        // 액션바 searchButton 리스너 달기
        ((ImageButton) findViewById(R.id.searchButton)).setOnClickListener(ClickListener);
    }

    // 제품 컨테이너 안에 있는 글씨들 글꼴 적용
    private void settingProductLayer(int layoutId) {
        LinearLayout container = (LinearLayout) findViewById(layoutId);

        // 브랜드명
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_left_brandname), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_center_brandname), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_right_brandname), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 제품명
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_left_productname), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_center_productname), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_right_productname), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 용량 및 가격
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_left_priceandvolume), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_center_priceandvolume), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(this, (TextView) container.findViewById(R.id.product_right_priceandvolume), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 제품 클릭시 리스너를 나타내주기 위한 코드
//        (container.findViewById(R.id.product_left)).setOnClickListener(ClickListener);
//        (container.findViewById(R.id.product_center)).setOnClickListener(ClickListener);
//        (container.findViewById(R.id.product_right)).setOnClickListener(ClickListener);
    }

    // 애니메이션을 위한 코드
    View.OnTouchListener TouchListener = new View.OnTouchListener() {
        float xAtDown;

        public boolean onTouch(View v, MotionEvent event) {
            // 이벤트가 터치(ACTION_DOWN)일 때, 손가락이 화면에 붙어 있을 때
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 시작좌표입니다. 터치 시점을 저장합니다.
                xAtDown = event.getX();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // 터치 이벤트가 끝난 위치와 드래그한 총 거리를 계산. (손가락이 화면에서 떨어졌을 때)
                float xAtUp = event.getX();

                // 드래그 이동거리를 구한다. x축 이동거리 y축 이동거리.
                float xDelta = Math.abs(xAtDown - xAtUp);

                // 충분히 이동했는지를 테스트 한다.
                int mMinimumFlipDrag = 50;

                if (mMinimumFlipDrag > xDelta) {
                    return false;
                }
                if (xAtUp > xAtDown) {
                    // 좌측 이동시 처리내용.
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.home_left_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.home_left_out));

                    switch (flipperCount) {
                        case 0:
                            flipperCount = 2;
                            ((ImageView) (findViewById(R.id.home_circle1))).setImageResource(R.drawable.uncircle);
                            ((ImageView) (findViewById(R.id.home_circle3))).setImageResource(R.drawable.oncircle);
                            viewFlipper.showPrevious();
                            break;
                        case 1:
                            flipperCount--;
                            ((ImageView) (findViewById(R.id.home_circle2))).setImageResource(R.drawable.uncircle);
                            ((ImageView) (findViewById(R.id.home_circle1))).setImageResource(R.drawable.oncircle);
                            viewFlipper.showPrevious();
                            break;
                        case 2:
                            flipperCount--;
                            ((ImageView) (findViewById(R.id.home_circle3))).setImageResource(R.drawable.uncircle);
                            ((ImageView) (findViewById(R.id.home_circle2))).setImageResource(R.drawable.oncircle);
                            viewFlipper.showPrevious();
                            break;
                    }
                } else {
                    // 우측 이동 시 처리 내용.
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.home_right_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.home_right_out));

                    switch (flipperCount) {
                        case 0:
                            flipperCount++;
                            ((ImageView) (findViewById(R.id.home_circle1))).setImageResource(R.drawable.uncircle);
                            ((ImageView) (findViewById(R.id.home_circle2))).setImageResource(R.drawable.oncircle);
                            viewFlipper.showNext();
                            break;
                        case 1:
                            flipperCount++;
                            viewFlipper.showNext();
                            ((ImageView) (findViewById(R.id.home_circle2))).setImageResource(R.drawable.uncircle);
                            ((ImageView) (findViewById(R.id.home_circle3))).setImageResource(R.drawable.oncircle);
                            break;
                        case 2:
                            flipperCount = 0;
                            viewFlipper.showNext();
                            ((ImageView) (findViewById(R.id.home_circle3))).setImageResource(R.drawable.uncircle);
                            ((ImageView) (findViewById(R.id.home_circle1))).setImageResource(R.drawable.oncircle);
                            break;
                    }
                }
            }
            return true;
        }
    };

    // 버튼 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.searchButton:
                    Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(intent);
                    break;
                case R.id.product_left:
                    Toast.makeText(HomeActivity.this, "product_left", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_center:
                    Toast.makeText(HomeActivity.this, "product_center", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.product_right:
                    Toast.makeText(HomeActivity.this, "product_right", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}