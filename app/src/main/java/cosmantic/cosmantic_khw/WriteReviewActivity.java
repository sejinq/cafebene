package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.widget.Toast.makeText;

public class WriteReviewActivity extends Activity {

    // ��ǰ �̹���, �̹��� �׸���, ������ ����
    ImageView image, imageShade, myImage;
    //�̹��� ���� ���ϱ� ��ư, �򰡿Ϸ� ��ư, �׼ǹ� ������ư
    ImageButton btLike, btSave, btShare;
    //�׼ǹ� Ÿ��Ʋ, ��ǰ��, �귣���, ���� ��ġȭ(����), �� ���� ��ġ
    TextView title, product, brand, average, myave;
    //��� ����, ���� �ִ� ����
    ImageView[] star = new ImageView[5];
    ImageButton[] btStar = new ImageButton[5];
    //����ڰ� �� ����
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

        title.setText("���ϱ�");
        btShare.setImageResource(R.drawable.sharebutton);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        int i;
        public void onClick(View v)
        {
            //�� Ŭ�� �̺�Ʈ. i��°�� Ŭ���ϸ� 0~i��° ������ ���� onstar�̹����� �ٲ��.
            i=0;
            switch(v.getId())
            {
                case R.id.mystar5:
                    ++i;
                    btStar[4].setImageResource(R.drawable.onstar);
                case R.id.mystar4:
                    ++i;
                    btStar[3].setImageResource(R.drawable.onstar);
                case R.id.mystar3:
                    ++i;
                    btStar[2].setImageResource(R.drawable.onstar);
                case R.id.mystar2:
                    ++i;
                    btStar[1].setImageResource(R.drawable.onstar);
                case R.id.mystar1:
                    ++i;
                    btStar[0].setImageResource(R.drawable.onstar);
            }
            //0~i ��°�� ������ ������ ������ �ٽ� unstar�� �ٲ��ֱ�.
            for(;i<5;++i)
            {
                btStar[i].setImageResource(R.drawable.unstar);
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