package cosmantic.cosmantic_khw;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by secpc on 2015-08-16.
 */
public class ProductInformTab implements ProductActivity.SmallTab {
    private Context context;
    private RelativeLayout mainView;

    public ProductInformTab(Context context, RelativeLayout view) {
        this.context = context;
        this.mainView = view;
        settingDescription();
        settingProducts();
        setFont();
    }
    private void setFont()
    {
        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text2)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text3)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        FontApplyer.setFont(context, ((TextView)mainView.findViewById(R.id.text4)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        FontApplyer.setFont(context, ((TextView) mainView.findViewById(R.id.price)), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        FontApplyer.setFont(context, ((TextView) mainView.findViewById(R.id.amount)), FontApplyer.Font.NotoSans, FontApplyer.Style.Thin);
        FontApplyer.setFont(context, ((TextView) mainView.findViewById(R.id.effect)), FontApplyer.Font.NotoSans, FontApplyer.Style.Thin);
    }

    //제품의 가격, 용량, 설명 받아오기
    private void settingDescription()
    {
        String text;
        text = ((MyApplication)context.getApplicationContext()).getProduct().getDescription();
        ((TextView)mainView.findViewById(R.id.effect)).setText(text);
        int value = ((MyApplication)context.getApplicationContext()).getProduct().getPrice();
        ((TextView)mainView.findViewById(R.id.price)).setText(value+"원");
        ((TextView)mainView.findViewById(R.id.amount)).setText(((MyApplication)context.getApplicationContext()).getProduct().getSize());

    }
    //유사 제품 보여주기
    private void settingProducts()
    {

    }
}
