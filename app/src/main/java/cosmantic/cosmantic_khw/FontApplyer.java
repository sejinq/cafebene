package cosmantic.cosmantic_khw;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontApplyer {

    /**
     * 뷰에 폰트를 적용시키는 메소드이다.
     * @param context 메소드를 사용하는 액티비티
     * @param view 폰트를 적용시킬 뷰
     * @param font 적용할 폰트의 이름
     * @param style 적용할 폰트의 스타일
     */
    public static void setFont(Context context, TextView view, String font, String style){
        String format = ".ttf";
        if(font.equals("NotoSansCJKkr")) format = ".otf";
        Typeface face = ((MyApplication)context.getApplicationContext()).getFont("fonts/"+font+"-"+style+format,context);

        view.setTypeface(face);
    }

    /**
     * Roboto - 'Roboto' 폰트(영문 폰트)
     * <br/>&#9;가능한 스타일: Regular, Italic, Thin, ThinItalic, Light, LightItalic, Medium, MediumItalic, Bold, BoldItalic, Black, BlackItalic
     * <br/>NotoSans - 'NotoSansCJKkr' 폰트(한글 폰트)
     * <br/>&#9;가능한 스타일:  Regular, Thin, DemiLight, Light, Medium, Bold, Black
     */
    public interface Font{
        /**
         * Roboto - 'Roboto' 폰트(영문 폰트)
         * <br/>&#9;가능한 스타일: Regular, Italic, Thin, ThinItalic, Light, LightItalic, Medium, MediumItalic, Bold, BoldItalic, Black, BlackItalic
         */
        public String Roboto = "Roboto";
        /**
         * NotoSans - 'NotoSansCJKkr' 폰트(한글 폰트)
         * <br/>&#9;가능한 스타일: Regular, Thin, DemiLight, Light, Medium, Bold, Black
         */
        public String NotoSans = "NotoSansCJKkr";
    }
    public interface Style{
        public String Italic = "Italic";
        public String DemiLight = "DemiLight";
        public String Light = "Light";
        public String LightItalic = "LightItalic";
        public String Medium = "Medium";
        public String MediumItalic = "MediumItalic";
        public String Regular = "Regular";
        public String Thin = "Thin";
        public String ThinItalic = "ThinItalic";
        public String Black = "Black";
        public String BlackItalic = "BlackItalic";
        public String Bold = "Bold";
        public String BoldItalic = "BoldItalic";
    }
}
