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

    String objectId, username, password, displayedName;
    boolean gender;
    int age;
    String skinType;
    String[] skinProblem;

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
        btNick.setOnClickListener(onNick);

        btMale = (Button)findViewById(R.id.button2);
        btMale.setOnClickListener(listener);
        btFemale = (Button)findViewById(R.id.button3);
        btFemale.setOnClickListener(listener);

        btAge_10 = (Button)findViewById(R.id.button4);
        btAge_10.setOnClickListener(listener2);
        btAge_20 = (Button)findViewById(R.id.button5);
        btAge_20.setOnClickListener(listener2);
        btAge_30 = (Button)findViewById(R.id.button6);
        btAge_30.setOnClickListener(listener2);
        btAge_40 = (Button)findViewById(R.id.button7);
        btAge_40.setOnClickListener(listener2);

        //유저의 피부타입 정보. 0.지성, 1.건성, 2.민감성, 3.모름
        btSkinType[0] = (Button)findViewById(R.id.button8);
        btSkinType[0].setOnClickListener(listener3);
        btSkinType[1] = (Button)findViewById(R.id.button9);
        btSkinType[1].setOnClickListener(listener3);
        btSkinType[2] = (Button)findViewById(R.id.button10);
        btSkinType[2].setOnClickListener(listener3);
        btSkinType[3] = (Button)findViewById(R.id.button11);
        btSkinType[3].setOnClickListener(listener3);
        //유저의 관심효능 0.보습, 1.여드름개선, 2.주름개선, 3.모공관리, 4.자외선차단, 5.미백, 6.피부재생, 7.각질제거
        btEffect[0] = (Button)findViewById(R.id.button12);
        btEffect[0].setOnClickListener(listener4);
        btEffect[1] = (Button)findViewById(R.id.button13);
        btEffect[1].setOnClickListener(listener4);
        btEffect[2] = (Button)findViewById(R.id.button14);
        btEffect[2].setOnClickListener(listener4);
        btEffect[3] = (Button)findViewById(R.id.button15);
        btEffect[3].setOnClickListener(listener4);
        btEffect[4] = (Button)findViewById(R.id.button16);
        btEffect[4].setOnClickListener(listener4);
        btEffect[5] = (Button)findViewById(R.id.button17);
        btEffect[5].setOnClickListener(listener4);
        btEffect[6] = (Button)findViewById(R.id.button18);
        btEffect[6].setOnClickListener(listener4);
        btEffect[7] = (Button)findViewById(R.id.button19);
        btEffect[7].setOnClickListener(listener4);

        btSignup = (Button)findViewById(R.id.button20);
        btSignup.setOnClickListener(onSignup);

        ckAgree = (CheckBox)findViewById(R.id.checkbox);
        ckAgree.setOnClickListener(onAgree);

    }

    //닉네임 중복확인
    View.OnClickListener onNick = new View.OnClickListener() {
        public void onClick(View v) {
            String text = etNick.getText().toString();
          //  if(compareNickName(text)==true)

            }
    };
    //성별 선택
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button2:
                    gender = false; break;
                case R.id.button3:
                    gender = true; break;
            }
        }
    };
    //나이 선택 + 50대 추가해야됨.
    View.OnClickListener listener2 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button4:
                    age = 10; break;
                case R.id.button5:
                    age = 20; break;
                case R.id.button6:
                    age = 30; break;
                case R.id.button7:
                    age = 40; break;
               /* case R.id.button7:
                    age = 50; break;*/
            }
        }
    };
    //피부타입 선택
    View.OnClickListener listener3 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button8:
                    break;
                case R.id.button9:
                    break;
                case R.id.button10:
                    break;
                case R.id.button11:
                    break;
            }
        }
    };
    //관심효능 선택
    View.OnClickListener listener4 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button12:
                    break;
                case R.id.button13:
                    break;
                case R.id.button14:
                    break;
                case R.id.button15:
                    break;
                case R.id.button16:
                    break;
                case R.id.button17:
                    break;
                case R.id.button18:
                    break;
                case R.id.button19:
                    break;
            }
        }
    };
    //이용약관 동의 체크박스
    View.OnClickListener onAgree = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };
    //가입완료 버튼
    View.OnClickListener onSignup = new View.OnClickListener() {
        public void onClick(View v) {
            /*User user = new User();*/
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
