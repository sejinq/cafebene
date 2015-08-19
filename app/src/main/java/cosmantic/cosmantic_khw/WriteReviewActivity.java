package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        image = (ImageView) findViewById(R.id.product_image);
        product = (TextView) findViewById(R.id.product_text);
        brand = (TextView) findViewById(R.id.brand_text);
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
        //image.setImageBitmap(((MyApplication) getApplicationContext()).getImage(((MyApplication) getApplicationContext()).getProduct().getThumnail()));
        //myImage.setImageBitmap(((MyApplication) getApplicationContext()).getImage(((MyApplication) getApplicationContext()).getUser().getImage()));
        //int aver = Math.round(((MyApplication) getApplicationContext()).getProduct().getScore());
        int aver = 4;
        for(int i=0;i<aver;++i)
        {
            star[i].setImageResource(R.drawable.star_inable);
        }
        average.setText("별점 ("+aver+")");
        //likeProducts = ((MyApplication)getApplicationContext()).getProduct().getObjectId();
        //if(((MyApplication)getApplicationContext()).getUser().isLike(likeProducts))
        //   btLike.setImageResource(R.drawable.love_inable);
    }
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
                    ((MyApplication)getApplicationContext()).getUser().getObjectId(),
                    ((MyApplication)getApplicationContext()).getUser().getDisplayedName(),
                    ((MyApplication)getApplicationContext()).getUser().getImage(),
                    (double)star_on, content);
            //review 서버 업로드
            int i=0;
            while(true)
            {
                if(ServerInteraction.onReviewUpload(review))
                    //홈화면으로 으로 넘겨주기.
                    break;
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