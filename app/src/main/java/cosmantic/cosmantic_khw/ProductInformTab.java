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

    public ProductInformTab(ProductActivity productActivity, RelativeLayout view) {
        this.context = context;
        this.mainView = view;
        settingDescription();
        settingProducts();
    }
    //제품의 가격, 용량, 설명 받아오기
    private void settingDescription()
    {
        String text;
        text = ((MyApplication)context.getApplicationContext()).getProduct().getDescription();
        ((TextView)mainView.findViewById(R.id.effect)).setText(text);
        int value = ((MyApplication)context.getApplicationContext()).getProduct().getPrice();
        ((TextView)mainView.findViewById(R.id.price)).setText(value+"원");
        value = (int)((MyApplication)context.getApplicationContext()).getProduct().getSize();
        ((TextView)mainView.findViewById(R.id.amount)).setText(value+"ml");

    }
    //유사 제품 보여주기
    private void settingProducts()
    {

    }
}
