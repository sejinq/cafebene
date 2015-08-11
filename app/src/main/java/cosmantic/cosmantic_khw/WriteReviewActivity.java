package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class WriteReviewActivity extends Activity {

    // 제품 이미지, 이미지 그림자, 프로필 사진
    ImageView image, imageShade, myImage;
    //이미지 위의 찜하기 버튼, 평가완료 버튼, 액션바 공유버튼
    ImageButton btLike, btSave, btShare;
    //액션바 타이틀, 제품명, 브랜드명, 별점 수치화(평점), 내 별점 수치
    TextView title, product, brand, average, myave;
    //평균 별점, 내가 주는 별점
    ImageView[] star = new ImageView[5];
    ImageButton[] btStar = new ImageButton[5];
    //사용자가 쓴 리뷰
    EditText review;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_reviewwrite);

        title = (TextView) findViewById(R.id.titletext);

        image = (ImageView) findViewById(R.id.imageView1);
        imageShade = (ImageView) findViewById(R.id.imageView2);
        product = (TextView) findViewById(R.id.textView);
        brand = (TextView) findViewById(R.id.textView2);
        average = (TextView) findViewById(R.id.average);
        myave = (TextView) findViewById(R.id.textScore);
        review = (EditText) findViewById(R.id.editText);

        btShare = (ImageButton) findViewById(R.id.sideButton);
        btShare.setOnClickListener(new onbtShare());
        btSave = (ImageButton) findViewById(R.id.imageButton8);
        btSave.setOnClickListener(new onbtSave());
        btLike = (ImageButton) findViewById(R.id.likebutton);
        btLike.setOnClickListener(new onbtLike());

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
        btShare.setImageResource(R.drawable.shareButton);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.mystar1: break;
                case R.id.mystar2:break;
                case R.id.mystar3:break;
                case R.id.mystar4:break;
                case R.id.mystar5:break;
            }
        }
    };


    public class onbtShare implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtLike implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtSave implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}