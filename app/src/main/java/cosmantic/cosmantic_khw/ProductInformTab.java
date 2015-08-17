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
    }
    private void settingEffect()
    {
        String text;
        text = ((MyApplication)context.getApplicationContext()).getProduct().getEffects();
        ((TextView)mainView.findViewById(R.id.product_curation)).setText(text);
    }
}
