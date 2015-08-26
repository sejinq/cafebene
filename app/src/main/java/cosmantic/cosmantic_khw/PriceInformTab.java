package cosmantic.cosmantic_khw;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by secpc on 2015-08-16.
 */
public class PriceInformTab implements ProductActivity.SmallTab {
    private Context context;
    private ProductActivity activity;
    private RelativeLayout mainView;

    public PriceInformTab(ProductActivity activity, RelativeLayout view) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.mainView = view;

        buttonSetting();
        setFont();
    }
    private void setFont()
    {
//        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
//        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
//        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.tab_title)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        FontApplyer.setFont(context, ((TextView) mainView.findViewById(R.id.tv_naver)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(context, ((TextView) mainView.findViewById(R.id.tv_gmarket)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(context, ((TextView) mainView.findViewById(R.id.tv_auction)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);


    }
    private void buttonSetting()
    {
        //네이버 검색, 공백은 "+" 로 변환해서 검색해준다.
        ((ImageButton) mainView.findViewById(R.id.naver)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //제품 이름 검색할 text로 저장,
                final String text = ((MyApplication)context.getApplicationContext()).getProduct().getProductName();
                text.replaceAll("\\p{Space}", "+");
                //키워드 검색 페이지 주소 만들어 주기, 해당 url로 넘어감.
                Uri uri = Uri.parse("http://m.shopping.naver.com/search/all_search.nhn?query=" + text);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });
        //g-market 검색.
        ((ImageButton) mainView.findViewById(R.id.gmarket)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //제품 이름 검색할 text로 저장,
                final String text = ((MyApplication)context.getApplicationContext()).getProduct().getProductName();
                text.replaceAll("\\p{Space}", "+");
                Uri uri = Uri.parse("http://mobile.gmarket.co.kr/Search/Search?topKeyword=" + text);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });
        //action검색. 한글 유니코드로 인코딩 해주기.
        ((ImageButton) mainView.findViewById(R.id.auction)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //제품 이름 검색할 text로 저장,
                final String text = ((MyApplication)context.getApplicationContext()).getProduct().getProductName();
                Uri uri = Uri.parse("http://search.auction.co.kr/search/search.aspx?keyword="+ Uni_encode(text));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });
        /*
        //모르겠져유ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
        ((ImageButton) mainView.findViewById(R.id.store11)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //제품 이름 검색할 text로 저장,
                final String text = ((MyApplication)context.getApplicationContext()).getProduct().getProductName();
                text.replaceAll("\\p{Space}", "+");
                Uri uri = Uri.parse("http://mobile.gmarket.co.kr/Search/Search?topKeyword=" + text);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });
        */
    }
    //throw Exception 지웠음..
    private String Uni_encode(String unicode)
    {
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < unicode.length(); i++)
        {
            if (((int) unicode.charAt(i) == 32 ))
            {
                str .append ("+" );
                continue;
            }
            str.append ("%u");
            str.append (Integer .toHexString((int) unicode.charAt(i)));
        }

        return str.toString();
    }
}
