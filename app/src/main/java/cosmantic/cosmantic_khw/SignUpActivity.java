package cosmantic.cosmantic_khw;

import android.app.ActionBar;
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
    Button btSk_Oily, btSk_Dry, btSk_Complex, btSk_Unknown, btEf_Share, btEf_Trouble, btEf_Wrinkle, btEf_Pore, btEf_Sun, btEf_White, btEf_Repair, btEf_Scrub;
    CheckBox ckAgree;
    Button btSignup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       /* ActionBar actionBar = getActionBar();
        actionBar.setTitle("회원가입");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();*/
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

        //유저의 피부타입 정보. 지성, 건성, 민감성, 모름
        btSk_Oily = (Button)findViewById(R.id.button8);
        btSk_Oily.setOnClickListener(new onbtSk_Oily());
        btSk_Dry = (Button)findViewById(R.id.button9);
        btSk_Dry.setOnClickListener(new onbtSk_Dry());
        btSk_Complex = (Button)findViewById(R.id.button10);
        btSk_Complex.setOnClickListener(new onbtSk_Complex());
        btSk_Unknown = (Button)findViewById(R.id.button11);
        btSk_Unknown.setOnClickListener(new onbtSk_Unknown());
        //유저의 관심효능 보습, 여드름개선, 주름개선, 모공관리, 자외선차단, 미백, 피부재생, 각질제거
        btEf_Share = (Button)findViewById(R.id.button12);
        btEf_Share.setOnClickListener(new onbtEf_Share());
        btEf_Trouble = (Button)findViewById(R.id.button13);
        btEf_Trouble.setOnClickListener(new onbtEf_Trouble());
        btEf_Wrinkle = (Button)findViewById(R.id.button14);
        btEf_Wrinkle.setOnClickListener(new onbtEf_Wrinkle());
        btEf_Pore = (Button)findViewById(R.id.button15);
        btEf_Pore.setOnClickListener(new onbtEf_Pore());
        btEf_Sun = (Button)findViewById(R.id.button16);
        btEf_Sun.setOnClickListener(new onbtEf_Sun());
        btEf_White = (Button)findViewById(R.id.button17);
        btEf_White.setOnClickListener(new onEf_White());
        btEf_Repair = (Button)findViewById(R.id.button18);
        btEf_Repair.setOnClickListener(new onbtEf_Repair());
        btEf_Scrub = (Button)findViewById(R.id.button19);
        btEf_Scrub.setOnClickListener(new onbtEf_Scrub());

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
