package cosmantic.cosmantic_khw;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by secpc on 2015-08-16.
 */
public class ProductCurationTab implements ProductActivity.SmallTab{

    private Context context;
    private RelativeLayout mainView;

    public ProductCurationTab(Context context, RelativeLayout view){
        this.context = context;
        this.mainView = view;

        effectSetting();
        graphSetting();
        curationgSetting();
    }

    private void effectSetting(){
        int[] effects = ((MyApplication)context.getApplicationContext()).getProduct().getTag();
        for(int loop=0; loop<effects.length; loop++){
            if(effects[loop] == 0){ //0을 플래그 참조로 바꿀것
                ((TextView)mainView.findViewById(R.id.effect_tag1)).setTextColor(context.getResources().getColor(R.color.effect_enable));
            }else if(effects[loop] == 1){
                ((TextView)mainView.findViewById(R.id.effect_tag2)).setTextColor(context.getResources().getColor(R.color.effect_enable));
            }else {
                ((TextView)mainView.findViewById(R.id.effect_tag3)).setTextColor(context.getResources().getColor(R.color.effect_enable));
            }
        }
    }
    private void graphSetting(){

    }
    private void curationgSetting(){
        String text;
        String[] curation = new String[3];

        text = ((MyApplication)context.getApplicationContext()).getProduct().getCuratingInfo();
        //split 특수기호?
        //공백 단위로 curation 을 나눈다.
        curation = text.split(" ");
        text="";
        //출력할 형식대로 curation 만들어 주기.
        for(int i=0;i<3;++i)
        {
            text+="-"+curation[i]+"\n";
        }
        //curation 출력
        ((TextView)mainView.findViewById(R.id.product_curation)).setText(text);
    }
}
