package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search);

        // 임시로 보이지 않게 설정
        ((LinearLayout)findViewById(R.id.search_result)).setVisibility(View.VISIBLE);

        // 액션바 뒤로가기 버튼, 검색 버튼 리스너 달아주는 코드
        ((ImageButton)findViewById(R.id.backButton)).setOnClickListener(ClickListener);
//        ((ImageButton)findViewById(R.id.searchButton)).setOnClickListener(ClickListener);

        // EditText에서 Enter키를 눌렀을 때 검색이 되게 하도록 리스너 달아줌.
//        ((EditText)findViewById(R.id.actionbar_search_edittext)).setOnKeyListener(KeyListener);
    }
    //????????????????????????????????????????????????????이게뭐람??????????????
    // 제품 클릭 시 동작하는 리스너
    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    finish();
                    break;
                case R.id.searchButton:
                    String word =((EditText)findViewById(R.id.actionbar_search_edittext)).getText().toString();
                    Brand[] brands = ServerInteraction.searchBrand(word);
                    Product[] products = ServerInteraction.searchProduct(word);
                    LinearLayout layout = ((LinearLayout) findViewById(R.id.search_result));
                    settingList(getApplicationContext(), word, layout);
                    break;
            }
        }
    };

    // 키 입력시 동작하는 리스너
    View.OnKeyListener KeyListener = new View.OnKeyListener(){
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
            if(keyCode==KeyEvent.KEYCODE_ENTER){
                ClickListener.onClick(findViewById(R.id.searchButton));
                return true;
            }
            return false;
        }
    };

    private void settingList(Context context, String searchWord, LinearLayout list)
    {
        Brand brands[] = new Brand[4];
        Product products[] = ServerInteraction.searchProduct(searchWord);

        for (int i = 0; i < brands.length; ++i) {
            final Brand brand = brands[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.search_result_brand, null);
            //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
            setFont(layout);
              byte[] thumb = ((MyApplication)getApplicationContext()).getUser().getImage();
              if(brand.getThumnail()!=null)
                  ((ImageView)layout.findViewById(R.id.userThumnail)).setImageBitmap(((MyApplication) context.getApplicationContext()).getImage(thumb));
            //브랜드 이미지
//            ((ImageView) layout.findViewById(R.id.searchResultImage)).setImageBitmap(((MyApplication) context).getImage(brand.getThumnail()));
            //브랜드 제품 수
            ((TextView) layout.findViewById(R.id.searchResultBrand_BrandName)).setText(brand.getBrandName()+"");
            ((TextView) layout.findViewById(R.id.searchResultBrnad_ProductNum)).setText("등록 제품 수 : " + brand.getProductNum() + " 개");
            //브랜드 클릭시 그 브랜드의 제품들을 보여준다.
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.removeAllViews();
                    settingBrandList(context, list, brand.getBrandName(), brand.getProductNum());
                }
            });
            list.addView(layout); // 부모에 부착
        }

        //제품 검색.
        for (int i = 0; i < products.length; ++i) {
            final Product product = products[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.search_result_product, null);
            //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
            setFont(layout);
            //제품 이미지
            ((ImageView) layout.findViewById(R.id.searchResultProductimage)).setImageBitmap(((MyApplication) context).getImage(product.getThumnail()));
            //제품 브랜드
            ((TextView) layout.findViewById(R.id.searchResultProduct_BrandName)).setText(product.getBrand());
            //제품명
            ((TextView) layout.findViewById(R.id.searchResultProduct_ProductName)).setText(product.getProductName());
            settingHeart(product.getObjectId());
            //브랜드 클릭시 그 브랜드의 제품들을 보여준다.
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //제품 상세 페이지
                    Intent Intent = new Intent(SearchActivity.this, ProductActivity.class);
                    ((MyApplication)context).setProduct(ServerInteraction.getProductInform(product.getObjectId()));
                    startActivity(Intent);
                     }
            });
            list.addView(layout); // 부모에 부착
        }

    }
    private void settingHeart(String product_id)
    {
        if(((MyApplication)getApplicationContext()).getUser().isLike(product_id))
        {
            ((ImageView)findViewById(R.id.searchResultProduct_heartTrue)).setVisibility(View.VISIBLE);
        }
    }
    private void settingBrandList(Context context, LinearLayout list, String brandName, int ProductNum)
    {
        //서버에서 받아오기................searchProductInBrand(String brandName)
        Product[] products = ServerInteraction.searchProductInBrand(brandName);

        for (int i = 0; i < ProductNum; ++i) {
            final Product product = products[i];
            RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.search_result_product, null);
            //컨텐츠 이미지, 제목, 부제 받아와서 보여주기.
            setFont(layout);
            //제품 이미지
            ((ImageView) layout.findViewById(R.id.searchResultProductimage)).setImageBitmap(((MyApplication) context).getImage(product.getThumnail()));
            //제품 브랜드
            ((TextView) layout.findViewById(R.id.searchResultProduct_BrandName)).setText(product.getBrand());
            //제품명
            ((TextView) layout.findViewById(R.id.searchResultProduct_ProductName)).setText(product.getProductName());
            settingHeart(product.getObjectId());
            //브랜드 클릭시 그 브랜드의 제품들을 보여준다.
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //제품 상세 페이지
                    Intent Intent = new Intent(SearchActivity.this, ProductActivity.class);
                    ((MyApplication)context).setProduct(ServerInteraction.getProductInform(product.getObjectId()));
                    startActivity(Intent);
                }
            });
            list.addView(layout); // 부모에 부착
        }

    }
    private void setFont(RelativeLayout layout)
    {
        FontApplyer.setFont(this, ((TextView)layout.findViewById(R.id.searchResultBrand_BrandName)), FontApplyer.Font.NotoSans, FontApplyer.Style.Medium);
        FontApplyer.setFont(this, ((TextView)layout.findViewById(R.id.searchResultProduct_ProductName)), FontApplyer.Font.NotoSans, FontApplyer.Style.Regular);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}