package cosmantic.cosmantic_khw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

//outer:372px, inner:290px, diff:82px
//outer:93dp, inner:73dp, diff:20dp
public class IngredientChartView extends View {

    private int[] values;
    private String typeText;
    private int size;
    private int c[] = {0xffff7e27,0xff00c3dd};
    private int non_c[] = {0xffccd4db};

    Paint circlePaint;
    Paint typeTextPaint;
    Paint ingredientTextPaint;
    RectF frame, inner;
    Path circlePath;

    public IngredientChartView(Context context, AttributeSet attrs){
        super(context, attrs);
        int[] attrList = new int[]{android.R.attr.layout_width,android.R.attr.layout_height};
        TypedArray ta = context.obtainStyledAttributes(attrs, attrList);
        int width = ta.getDimensionPixelSize(0, 372);
        int height = ta.getDimensionPixelSize(0, 372);
        this.size = (width<height)?width:height;
        Log.d("Chart","width:"+width+",size:"+size);
        ta.recycle();

        TypedArray taCustom = context.obtainStyledAttributes(attrs, R.styleable.IngredientChartView);
        int pos = taCustom.getInt(R.styleable.IngredientChartView_positive, 0);
        int neg = taCustom.getInt(R.styleable.IngredientChartView_negative, 0);
        typeText = taCustom.getString(R.styleable.IngredientChartView_type_text);

        Log.d("Chart","width:"+width+",size:"+size+",pos:"+pos+",neg:"+neg);
        values = new int[]{pos,neg};
        taCustom.recycle();

        float gap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10, getContext().getResources().getDisplayMetrics());
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/NotoSansCJKkr-Medium.otf");
        float font_size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,12.8f, getContext().getResources().getDisplayMetrics());
        typeTextPaint = new Paint();
        typeTextPaint.setAntiAlias(true);
        typeTextPaint.setColor(Color.BLACK);
        typeTextPaint.setTextAlign(Paint.Align.CENTER);
        typeTextPaint.setTextSize(font_size);
        typeTextPaint.setTypeface(typeface);
        font_size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,7.2f, getContext().getResources().getDisplayMetrics());
        ingredientTextPaint = new Paint();
        ingredientTextPaint.setAntiAlias(true);
        ingredientTextPaint.setTextSize(font_size);
        ingredientTextPaint.setTypeface(typeface);
        frame = new RectF(0,0,size,size);
        inner = new RectF(gap,gap,size-gap,size-gap);
        circlePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(values!=null){
            //Circle
            float[] angles = calcAngle();
            if(angles[0] != 0.0 & angles[1] != 0.0) {
                //positive
                circlePaint.setColor(c[0]);
                circlePath.reset();
                circlePath.arcTo(frame, -90, angles[0], false);
                circlePath.arcTo(inner, -90 + angles[0], -angles[0], false);
                canvas.drawPath(circlePath, circlePaint);
                //negative
                circlePaint.setColor(c[1]);
                circlePath.reset();
                circlePath.arcTo(frame, -90+angles[0], angles[1], false);
                circlePath.arcTo(inner, 270, -angles[1], false);
                canvas.drawPath(circlePath, circlePaint);
            } else if(angles[0] != 0.0){
                circlePaint.setColor(c[0]);
                circlePath.reset();
                circlePath.arcTo(frame, -89.9f, 359.8f, false);
                circlePath.arcTo(inner, 269.9f, -359.8f, false);
                canvas.drawPath(circlePath, circlePaint);
            } else if(angles[1] != 0.0){
                circlePaint.setColor(c[1]);
                circlePath.reset();
                circlePath.arcTo(frame, -89.9f, 359.8f, false);
                circlePath.arcTo(inner, 269.9f, -359.8f, false);
                canvas.drawPath(circlePath, circlePaint);
            } else {
                //non-draw
                circlePaint.setColor(non_c[0]);
                circlePath.reset();
                circlePath.arcTo(frame, -89.9f, 359.8f, false);
                circlePath.arcTo(inner, 269.9f, -359.8f, false);
                canvas.drawPath(circlePath, circlePaint);
            }
            //Text
            float main_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,2.86f, getContext().getResources().getDisplayMetrics());
            float sub_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,12.14f, getContext().getResources().getDisplayMetrics());
            canvas.drawText(typeText,size/2,(size/2)+main_height,typeTextPaint);
            ingredientTextPaint.setColor(c[1]);
            ingredientTextPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("+"+values[0],size/2,(size/2)+sub_height,ingredientTextPaint);
            ingredientTextPaint.setColor(c[0]);
            ingredientTextPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("-"+values[1],size/2,(size/2)+sub_height,ingredientTextPaint);
        }
    }

    private float[] calcAngle(){
        float[] angles = new float[2];

        int tot = values[0]+values[1];

        if(tot != 0) {
            angles[0] = 360 * (values[1] / (float) tot);
            angles[1] = 360 * (values[0] / (float) tot);
        }else{
            angles = new float[]{0.0f,0.0f};
        }
        return angles;
    }

    public void setIngredientValue(int pos, int neg){
        this.values = new int[]{pos,neg};
        this.invalidate();
    }
}
