package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;

public class SignUpActivity extends Activity {

    EditText etID, etPass, etR_Pass, etNick;
    Button btNick, btMale, btFemale, btAge_10, btAge_20, btAge_30, btAge_40;
    Button[] btSkinType= new Button[4];
    Button[] btEffect = new Button[8];
    CheckBox ckAgree;
    Button btSignup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*회원가입 방법 선택에 따라 보여주거나 안보여줄 행.인텐트 값 받아서 설정해 주기.
        passRow.setVisibility(View.GONE); passRow가 사라지고 동작하지 않음
        INVISIBLE : passRow가 사라지지만 투명하게 보이게함(공간은 남겨둠)
        VISIBLE : 보여진다. 디폴트 값.
        */
        TableRow passRow = (TableRow)findViewById(R.id.passRow);
        TableRow repassRow = (TableRow)findViewById(R.id.repassRow);

        //회원가입시 유저 정보. 아이디, 닉네임, 비밀번호, 성별, 나이
        etID = (EditText)findViewById(R.id.editText1);
        etNick = (EditText)findViewById(R.id.editText2);
        etPass = (EditText)findViewById(R.id.editText3);
        etR_Pass = (EditText)findViewById(R.id.editText4);

        btNick = (Button)findViewById(R.id.button1);
        btNick.setOnClickListener(new onbtNick());

        btMale = (Button)findViewById(R.id.button2);
        btMale.setOnClickListener(new onbtMale());
        btFemale = (Button)findViewById(R.id.button3);
        btFemale.setOnClickListener(new onbtFemale());

        btAge_10 = (Button)findViewById(R.id.button4);
        btAge_10.setOnClickListener(new onbtAge_10());
        btAge_20 = (Button)findViewById(R.id.button5);
        btAge_20.setOnClickListener(new onbtAge_20());
        btAge_30 = (Button)findViewById(R.id.button6);
        btAge_30.setOnClickListener(new onbtAge_30());
        btAge_40 = (Button)findViewById(R.id.button7);
        btAge_40.setOnClickListener(new onbtAge_40());

        //유저의 피부타입 정보. 0.지성, 1.건성, 2.민감성, 3.모름
        btSkinType[0] = (Button)findViewById(R.id.button8);
        btSkinType[0].setOnClickListener(new onbtSk_Oily());
        btSkinType[1] = (Button)findViewById(R.id.button9);
        btSkinType[1].setOnClickListener(new onbtSk_Dry());
        btSkinType[2] = (Button)findViewById(R.id.button10);
        btSkinType[2].setOnClickListener(new onbtSk_Complex());
        btSkinType[3] = (Button)findViewById(R.id.button11);
        btSkinType[3].setOnClickListener(new onbtSk_Unknown());
        //유저의 관심효능 0.보습, 1.여드름개선, 2.주름개선, 3.모공관리, 4.자외선차단, 5.미백, 6.피부재생, 7.각질제거
        btEffect[0] = (Button)findViewById(R.id.button12);
        btEffect[0].setOnClickListener(new onbtEf_Share());
        btEffect[1] = (Button)findViewById(R.id.button13);
        btEffect[1].setOnClickListener(new onbtEf_Trouble());
        btEffect[2] = (Button)findViewById(R.id.button14);
        btEffect[2].setOnClickListener(new onbtEf_Wrinkle());
        btEffect[3] = (Button)findViewById(R.id.button15);
        btEffect[3].setOnClickListener(new onbtEf_Pore());
        btEffect[4] = (Button)findViewById(R.id.button16);
        btEffect[4].setOnClickListener(new onbtEf_Sun());
        btEffect[5] = (Button)findViewById(R.id.button17);
        btEffect[5].setOnClickListener(new onEf_White());
        btEffect[6] = (Button)findViewById(R.id.button18);
        btEffect[6].setOnClickListener(new onbtEf_Repair());
        btEffect[7] = (Button)findViewById(R.id.button19);
        btEffect[7].setOnClickListener(new onbtEf_Scrub());

        btSignup = (Button)findViewById(R.id.button20);
        btSignup.setOnClickListener(new onbtSignup());

        ckAgree = (CheckBox)findViewById(R.id.checkbox);
        ckAgree.setOnClickListener(new onckAgree());

    }

    //닉네임 중복확인
    public class onbtNick implements View.OnClickListener {
        public void onClick(View v) {

            String nick = etNick.getText().toString();

        }
    }
    //성별 버튼 남자, 여자
    public class onbtMale implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtFemale implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    //나이 버튼 10대, 20대, 30대, 40대
    public class onbtAge_10 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtAge_20 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtAge_30 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtAge_40 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    //피부타입 버튼 지성, 건성, 복합성, 모름
    public class onbtSk_Oily implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtSk_Dry implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtSk_Complex implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtSk_Unknown implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    //관심효과 보습, 여드름, 주름, 모공, 자외선, 미백, 재생, 각질제거
    public class onbtEf_Share implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtEf_Trouble implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtEf_Wrinkle implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtEf_Pore implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtEf_Sun implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onEf_White implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtEf_Repair implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtEf_Scrub implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    //이용약관 동의 체크박스
    public class onckAgree implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    //가입완료 버튼
    public class onbtSignup implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
