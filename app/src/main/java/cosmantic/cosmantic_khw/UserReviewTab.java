package cosmantic.cosmantic_khw;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * Created by secpc on 2015-08-16.
 */
//세진이가함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!>&&&&&&&&&&&&&&&&
public class UserReviewTab implements ProductActivity.SmallTab {
    private Context context;
    private RelativeLayout mainView;
    private LinearLayout lv;

    public UserReviewTab(Context context, RelativeLayout view) {
        this.context = context;
        this.mainView = view;

        listViewSetting();

    }
    private void listViewSetting()
    {
        ImageButton[] star = new ImageButton[5];

        int reviewNum = ((MyApplication)context).getProduct().getReviewNum();
        lv = ((LinearLayout)mainView.findViewById(R.id.listReview));//카드 내부에 이벤트 등록
        final Review[] reviewList = new Review[reviewNum];
        //Review[] 서버 메소드 return 값 받아오기!!!!!!!!!!!!!!!!!!!!!!!!!!
        for(int i=0;i<reviewNum;++i) {
            reviewList[i] = new Review();
            final Review review = reviewList[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.user_review_box, null);
            //((ImageView)layout.findViewById(R.id.userThumnail)).setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(review.getThumnail()));
            //유저의 사진 클릭시 유저 정보창으로 넘어간다.
            ((ImageView)layout.findViewById(R.id.userThumnail)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(context, UserInformActivity.class);
                    nextIntent.putExtra("userID", review.getUserObjectId());
                    context.startActivity(nextIntent);
                }
            });
            //유저가 매긴 별점 읽어와서 이미지로 보여주기
            star[0] = (ImageButton)layout.findViewById(R.id.mystar1);
            star[1] = (ImageButton)layout.findViewById(R.id.mystar2);
            star[2] = (ImageButton)layout.findViewById(R.id.mystar3);
            star[3] = (ImageButton)layout.findViewById(R.id.mystar4);
            star[4] = (ImageButton)layout.findViewById(R.id.mystar5);
            int aver = (int)review.getRate();

            for(int j=0;j<aver;++j)
            {
                star[j].setImageResource(R.drawable.star_inable);
            }
            //유저의 닉네임, 별점 스코어, 리뷰내용 보여주기
            ((TextView)layout.findViewById(R.id.userNick)).setText(review.getDisplayedname());
            ((TextView)layout.findViewById(R.id.userScore)).setVisibility(View.GONE);

            ((TextView)layout.findViewById(R.id.textScore)).setText(aver+".0 ("+reviewNum+"명)");
            ((TextView)layout.findViewById(R.id.reviewText)).setText(review.getContent());
            lv.addView(layout); // 부모에 부착
        }
    }
}
