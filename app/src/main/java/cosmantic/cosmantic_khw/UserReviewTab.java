package cosmantic.cosmantic_khw;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by secpc on 2015-08-16.
 */
public class UserReviewTab implements ProductActivity.SmallTab {
    private Context context;
    private RelativeLayout mainView;
    public UserReviewTab(ProductActivity productActivity, RelativeLayout view) {
        this.context = context;
        this.mainView = view;
    }
}
