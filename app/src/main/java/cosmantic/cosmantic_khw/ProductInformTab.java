package cosmantic.cosmantic_khw;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by secpc on 2015-08-16.
 */
public class ProductInformTab implements ProductActivity.SmallTab {
    private Context context;
    private RelativeLayout mainView;
    private Product[] products;

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
        ((TextView)mainView.findViewById(R.id.price)).setText(value + "원");
        ((TextView)mainView.findViewById(R.id.amount)).setText(((MyApplication) context.getApplicationContext()).getProduct().getSize());

    }
    //유사 제품 보여주기
    private void settingProducts()
    {
        TextView textView;
        // 브랜드명
        textView = (TextView)mainView.findViewById(R.id.product_left_brandname);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)mainView.findViewById(R.id.product_center_brandname);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
        textView = (TextView)mainView.findViewById(R.id.product_right_brandname);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);

        // 제품명
        textView = (TextView)mainView.findViewById(R.id.product_left_productname);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)mainView.findViewById(R.id.product_center_productname);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)mainView.findViewById(R.id.product_right_productname);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 용량 및 가격
        textView = (TextView)mainView.findViewById(R.id.product_left_priceandvolume);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)mainView.findViewById(R.id.product_center_priceandvolume);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
        textView = (TextView)mainView.findViewById(R.id.product_right_priceandvolume);
        FontApplyer.setFont(context, textView, FontApplyer.Font.NotoSans, FontApplyer.Style.Light);

        // 제품 클릭시 리스너를 나타내주기 위한 코드
        (mainView.findViewById(R.id.product_left)).setOnClickListener(view->{
            Toast.makeText(context, "로딩중입니다.", Toast.LENGTH_SHORT).show();});
        (mainView.findViewById(R.id.product_center)).setOnClickListener(view->{
            Toast.makeText(context, "로딩중입니다.", Toast.LENGTH_SHORT).show();});
        (mainView.findViewById(R.id.product_right)).setOnClickListener(view->{
            Toast.makeText(context, "로딩중입니다.", Toast.LENGTH_SHORT).show();});

        // 제품 목록 받아오기
//        String[] productIds = ServerInteraction.getRelaventProduct(((MyApplication) context.getApplicationContext()).getProduct());
//        for(int loop = 0; loop<productIds.length; loop++) {
//            final int index = loop;
//            Log.d("M:Relevant",productIds[loop]);
//            new Thread(() -> setEachProduct(productIds[index], index)).start();
//        }
    }

    private void setEachProduct(String productID, final int index){
        Product product = ServerInteraction.getProductInform(productID);
        products[index] = product;
        RelativeLayout parent = mainView;
        Log.d("Product Inform","Relevant Num: "+index);
        ((ProductActivity)context).runOnUiThread(() -> {
            switch (index % 3) {
                case 0:
                    if (product.getThumnail() != null)
                        ((ImageView) parent.findViewById(R.id.product_left_image))
                                .setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(product.getThumnail()));
                    ((TextView) parent.findViewById(R.id.product_left_brandname)).setText(product.getBrandKor());
                    String name = product.getProductName();
                    if (name.length() <= 8)
                        ((TextView) parent.findViewById(R.id.product_left_productname)).setText(name);
                    else
                        ((TextView) parent.findViewById(R.id.product_left_productname)).setText(name.substring(0, 7) + "...");
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    ((TextView) parent.findViewById(R.id.product_left_priceandvolume))
                            .setText(product.getSize() + " / " + formatter.format(product.getPrice()));
                    parent.findViewById(R.id.product_left).setOnClickListener(view -> setProductListener(index));
                    break;
                case 1:
                    if (product.getThumnail() != null)
                        ((ImageView) parent.findViewById(R.id.product_center_image))
                                .setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(product.getThumnail()));
                    ((TextView) parent.findViewById(R.id.product_center_brandname)).setText(product.getBrandKor());
                    name = product.getProductName();
                    if (name.length() <= 8)
                        ((TextView) parent.findViewById(R.id.product_center_productname)).setText(name);
                    else
                        ((TextView) parent.findViewById(R.id.product_center_productname)).setText(name.substring(0, 7) + "...");
                    formatter = new DecimalFormat("#,###,###");
                    ((TextView) parent.findViewById(R.id.product_center_priceandvolume))
                            .setText(product.getSize() + " / " + formatter.format(product.getPrice()) + "원");
                    parent.findViewById(R.id.product_center).setOnClickListener(view -> setProductListener(index));
                    break;
                default:
                    if (product.getThumnail() != null)
                        ((ImageView) parent.findViewById(R.id.product_right_image))
                                .setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(product.getThumnail()));
                    ((TextView) parent.findViewById(R.id.product_right_brandname)).setText(product.getBrandKor());
                    name = product.getProductName();
                    if (name.length() <= 8)
                        ((TextView) parent.findViewById(R.id.product_right_productname)).setText(name);
                    else
                        ((TextView) parent.findViewById(R.id.product_right_productname)).setText(name.substring(0, 7) + "...");
                    formatter = new DecimalFormat("#,###,###");
                    ((TextView) parent.findViewById(R.id.product_right_priceandvolume))
                            .setText(product.getSize() + " / " + formatter.format(product.getPrice()));
                    parent.findViewById(R.id.product_right).setOnClickListener(view -> setProductListener(index));
            }
        });
    }
    private void setProductListener(int index){
//        if(flag_info != User.SEASON_SOLDIER) {
        Product old_product = ((MyApplication) context.getApplicationContext()).getProduct();
        ((MyApplication) context.getApplicationContext()).setProduct(products[index]);
        Intent intent = new Intent(((ProductActivity)context), ProductActivity.class);
        ((ProductActivity)context).startActivity(intent);
        ((MyApplication) context.getApplicationContext()).setProduct(old_product);
//        }else Toast.makeText(this,"준비중입니다.",Toast.LENGTH_SHORT).show();
    }

}
