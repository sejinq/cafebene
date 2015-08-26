package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

/*손!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111세~~~~~~~~~~~~~~~~~~~~~~진@@@@@@@@@@@@@@@@@@@@@@@@@ 내가다함*/
public class WriteReviewActivity extends Activity {

    // 제품 이미지, 이미지 그림자, 프로필 사진
    /*사용자가 선택한 제품 ID 불러오기...? 이미지, 브랜드, 가격 등 정보 띄우기. intent로 Product타입 받기.*/
    ImageView image, imageShade, myImage;
    //이미지 위의 찜하기 버튼, 평가완료 버튼, 액션바 공유버튼
    ImageButton btLike, btSave, btShare;
    //액션바 타이틀, 제품명, 브랜드명, 별점 수치화(평점), 내 별점 수치
    TextView title, product, brand, average, myave;
    //평균 별점, 내가 주는 별점
    ImageView[] star = new ImageView[5];
    ImageButton[] btStar = new ImageButton[5];
    //사용자가 쓴 리뷰
    EditText etContent;
    //별 버튼 이벤트 변수, 찜하기 버튼 이벤트 변수
    int star_on=0;
    String likeProducts;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_review_write);

        title = (TextView) findViewById(R.id.titleText);

        //Product
        Product productObject = ((MyApplication)getApplicationContext()).getProduct();
        image = (ImageView) findViewById(R.id.product_image);
        product = (TextView) findViewById(R.id.product_text);
        product.setText(productObject.getProductName());
        brand = (TextView) findViewById(R.id.brand_text);
        brand.setText(productObject.getBrand());
        average = (TextView) findViewById(R.id.average);
        myave = (TextView) findViewById(R.id.textScore);
        etContent = (EditText) findViewById(R.id.editText);

        btShare = (ImageButton) findViewById(R.id.searchButton);
        btShare.setOnClickListener(onShare);
        btSave = (ImageButton) findViewById(R.id.imageButton8);
        btSave.setOnClickListener(onSave);
        btLike = (ImageButton) findViewById(R.id.likebutton);
        btLike.setOnClickListener(onLike);

        star[0] = (ImageView) findViewById(R.id.star1);
        star[1] = (ImageView) findViewById(R.id.star2);
        star[2] = (ImageView) findViewById(R.id.star3);
        star[3] = (ImageView) findViewById(R.id.star4);
        star[4] = (ImageView) findViewById(R.id.star5);

        btStar[0] = (ImageButton) findViewById(R.id.mystar1);
        btStar[0].setOnClickListener(listener);
        btStar[1] = (ImageButton) findViewById(R.id.mystar2);
        btStar[1].setOnClickListener(listener);
        btStar[2] = (ImageButton) findViewById(R.id.mystar3);
        btStar[2].setOnClickListener(listener);
        btStar[3] = (ImageButton) findViewById(R.id.mystar4);
        btStar[3].setOnClickListener(listener);
        btStar[4] = (ImageButton) findViewById(R.id.mystar5);
        btStar[4].setOnClickListener(listener);

        title.setText("평가하기");

        btShare.setImageResource(R.drawable.share_button);
        ((ImageView) findViewById(R.id.writebox)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.editText)).setVisibility(View.VISIBLE);

        /*myImage.setImageResource(); byte로 저장되어있음*/
        byte[] byteImage = ((MyApplication) getApplicationContext()).getProduct().getThumnail();
        if(byteImage != null)
            image.setImageBitmap(((MyApplication) getApplicationContext()).getImage(byteImage));
        byteImage = ((MyApplication) getApplicationContext()).getUser().getImage();
        if(byteImage != null)
            myImage.setImageBitmap(((MyApplication) getApplicationContext()).getImage(byteImage));
        int aver = Math.round(((MyApplication) getApplicationContext()).getProduct().getScore());
//        int aver = 4;
        for(int i=0;i<aver;++i)
        {
            star[i].setImageResource(R.drawable.star_inable);
        }
        average.setText(productObject.getScore() + " (" + productObject.getReviewNum() + "명)");
        //찜하기를 한건지 안한건지 판별
        likeProducts = ((MyApplication)getApplicationContext()).getProduct().getObjectId();
        if(((MyApplication)getApplicationContext()).getUser().isLike(likeProducts))
           btLike.setImageResource(R.drawable.love_inable);

        setFont();
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(ClickListener);

    }
    private void setFont()
    {
//폰트 설정
        TextView nick = (TextView) findViewById(R.id.userNick);
        nick.setText(((MyApplication) getApplicationContext()).getUser().getDisplayedName());
        FontApplyer.setFont(this, nick, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, (TextView) findViewById(R.id.userScore), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, (TextView) findViewById(R.id.userScore), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, (TextView) findViewById(R.id.textScore), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, myave, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, product, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, title, FontApplyer.Font.NotoSans, FontApplyer.Style.Bold);
        FontApplyer.setFont(this, brand, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this, average, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        FontApplyer.setFont(this,  (TextView) findViewById(R.id.editText), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this,  (TextView) findViewById(R.id.myreview), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(this,  (TextView) findViewById(R.id.button_text), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);



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
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v)
        {
            star_on=0;
            switch(v.getId())
            {
                case R.id.mystar5:
                    ++star_on;
                    btStar[4].setImageResource(R.drawable.my_star_inable);
                case R.id.mystar4:
                    ++star_on;
                    btStar[3].setImageResource(R.drawable.my_star_inable);
                case R.id.mystar3:
                    ++star_on;
                    btStar[2].setImageResource(R.drawable.my_star_inable);
                case R.id.mystar2:
                    ++star_on;
                    btStar[1].setImageResource(R.drawable.my_star_inable);
                case R.id.mystar1:
                    ++star_on;
                    btStar[0].setImageResource(R.drawable.my_star_inable);
            }
            myave.setText(star_on+".0");
            for(;star_on<5;++star_on)
            {
                btStar[star_on].setImageResource(R.drawable.my_star_disable);
            }
        }
    };


    View.OnClickListener onShare = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };
    View.OnClickListener onLike = new View.OnClickListener() {
        public void onClick(View v) {
            //해당 제품을 찜 목록으로 등록, 이미 등록되어있으면 해제.

            if(!((MyApplication)getApplicationContext()).getUser().setLikeProducts(likeProducts))
            {
                btLike.setImageResource(R.drawable.love_disable);
            }
            else
            {
                btLike.setImageResource(R.drawable.love_inable);
            }
        }
    };
    View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            //사용자가 쓴 리뷰를 받아 Review 클래스 생성.
            String content =etContent.getText().toString();
            Review review = new Review(((MyApplication)getApplicationContext()).getProduct().getObjectId(),
                    ParseUser.getCurrentUser().getObjectId(),
                    ParseUser.getCurrentUser().getString("displayedName"),
                    ((MyApplication)getApplicationContext()).getUser().getImage(),
                    (double)star_on, content);
            //review 서버 업로드
            int i=0;
            while(true)
            {
                if(ServerInteraction.onReviewUpload(review)) {
                    //홈화면으로 으로 넘겨주기.
                    Intent finishIntent = new Intent();
                    finishIntent.putExtra("result","SUCCESS");
                    WriteReviewActivity.this.setResult(Activity.RESULT_OK,finishIntent);
                    WriteReviewActivity.this.finish();
                    break;
                }
                if(++i>3)
                    //메세지 띄워주기 "음.. 안됨 어쩌구"
                    break;
            }
        }
    };
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}