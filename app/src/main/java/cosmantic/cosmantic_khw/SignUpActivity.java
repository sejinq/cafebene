package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;

public class SignUpActivity extends Activity {

    EditText etID, etPass, etR_Pass, etNick;
    Button btNick, btMale, btFemale;
    Button[] btAge = new Button[5];
    Button[] btSkinType= new Button[4];
    Button[] btEffect = new Button[8];
    CheckBox ckAgree;
    Button btSignup;

    String username=null;
    String displayedName;
    boolean gender;
    int age, skinType;
    boolean[] skinProblem = new boolean[8];

    //입력 유무 체크
    boolean checkNick=false, checkGender = false, checkAge = false;
    //관심효능 변수
    int effect=0;
    int userType; // 인텐트 값 저장해라...1~~!!@~@!@!@!@

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*회원가입 방법 선택에 따라 보여주거나 안보여줄 행.인텐트 값 받아서 설정해 주기.
        passRow.setVisibility(View.GONE); passRow가 사라지고 동작하지 않음
        INVISIBLE : passRow가 사라지지만 투명하게 보이게함(공간은 남겨둠)
        VISIBLE : 보여진다. 디폴트 값.
        인텐트 받아오기
        if()
        {
            passRow.setVisibility(View.GONE);
        }
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

        btAge[0] = (Button)findViewById(R.id.button4);
        btAge[1] = (Button)findViewById(R.id.button5);
        btAge[2] = (Button)findViewById(R.id.button6);
        btAge[3] = (Button)findViewById(R.id.button7);
        for(int i=0;i<4;++i)
        {
            btAge[i].setOnClickListener(listener2);
        }
        //유저의 피부타입 정보. 0.지성, 1.건성, 2.민감성, 3.모름
        btSkinType[0] = (Button)findViewById(R.id.button8);
        btSkinType[1] = (Button)findViewById(R.id.button9);
        btSkinType[2] = (Button)findViewById(R.id.button10);
        btSkinType[3] = (Button)findViewById(R.id.button11);
        for(int i=0;i<4;++i)
        {
            btSkinType[i].setOnClickListener(listener3);
        }

        //유저의 관심효능 0.보습, 1.여드름개선, 2.주름개선, 3.모공관리, 4.자외선차단, 5.미백, 6.피부재생, 7.각질제거
        btEffect[0] = (Button)findViewById(R.id.button12);
        btEffect[1] = (Button)findViewById(R.id.button13);
        btEffect[2] = (Button)findViewById(R.id.button14);
        btEffect[3] = (Button)findViewById(R.id.button15);
        btEffect[4] = (Button)findViewById(R.id.button16);
        btEffect[5] = (Button)findViewById(R.id.button17);
        btEffect[6] = (Button)findViewById(R.id.button18);
        btEffect[7] = (Button)findViewById(R.id.button19);

        for(int i=0;i<8;++i)
        {
            btEffect[i].setOnClickListener(listener4);
        }

        btSignup = (Button)findViewById(R.id.button20);
        btSignup.setOnClickListener(onSignup);

        ckAgree = (CheckBox)findViewById(R.id.checkbox);
       // ckAgree.setOnClickListener(onAgree);

    }

    //닉네임 중복확인
    View.OnClickListener onNick = new View.OnClickListener() {
        public void onClick(View v) {
            displayedName = etNick.getText().toString();
            checkNick=ServerInteraction.compareNickName(displayedName);
            if(checkNick)
            {
                //팝업창 띄우기. 사용가능한 어쩌구..
            }
            else
            {
                //팝업창 띄우기. 사용가능한 어쩌구..
            }
        }
    };
    //성별 선택
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            checkGender = true;
            initSelect(btMale);initSelect(btFemale);
            switch (v.getId()) {
                case R.id.button2:
                    gender = false;
                    //다른 버튼 해제 이미지로 바꿔 주기.
                    break;
                case R.id.button3:
                    gender = true;
                    //다른 버튼 해제 이미지로 바꿔 주기.
                    break;
            }
        }
    };
    //나이 선택 + 50대 추가해야됨.
    View.OnClickListener listener2 = new View.OnClickListener() {
        public void onClick(View v) {
            checkAge = true;
            for(int i=0;i<5;++i)
            {
                initSelect(btAge[i]);
            }
            switch (v.getId()) {
                case R.id.button4:
                    btAge[0].setTextColor(0xf28314); age = 10; break;
                case R.id.button5:
                    btAge[1].setTextColor(0xf28314); age = 20; break;
                case R.id.button6:
                    btAge[2].setTextColor(0xf28314); age = 30; break;
                case R.id.button7:
                    btAge[3].setTextColor(0xf28314); age = 40; break;
               /* case R.id.button7:
                    age = 50;btAge_50.setTextColor(0xf28314); break;*/
            }
        }
    };
    private void initSelect(Button button)
    {
        button.setTextColor(0x9fa6ad);
        //button.setBackground(R.drawable.);
        //btAge_50.setTextColor(0x9fa6ad);
    }
    private void initSelect2(Button button)
    {
        button.setTextColor(0x9fa6ad);
        //button.setBackground(R.drawable.);
        //btAge_50.setTextColor(0x9fa6ad);
    }
    //피부타입 선택
    View.OnClickListener listener3 = new View.OnClickListener() {
        public void onClick(View v) {
            for(int i=0;i<4;++i)
            {
                initSelect(btSkinType[i]);
            }
            switch (v.getId()) {
                case R.id.button8:
                    skinType=0; break;
                case R.id.button9:
                    skinType=1; break;
                case R.id.button10:
                    skinType=2; break;
                case R.id.button11:
                    skinType=3; break;
            }
        }
    };
    //관심효능 선택
    View.OnClickListener listener4 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button12:
                     multiSelect(btEffect[0], 0);break;
                case R.id.button13:
                    multiSelect(btEffect[1], 1);break;
                case R.id.button14:
                    multiSelect(btEffect[2], 2);break;
                case R.id.button15:
                    multiSelect(btEffect[3], 3);break;
                case R.id.button16:
                    multiSelect(btEffect[4], 4);break;
                case R.id.button17:
                    multiSelect(btEffect[5], 5);break;
                case R.id.button18:
                    multiSelect(btEffect[6], 6);break;
                case R.id.button19:
                    multiSelect(btEffect[7], 7);break;
            }
        }
    };
    //버튼 상태에 따라 처리를 달리 해 준다.
    private void multiSelect(Button button, int index)
    {
        //선택 안되어있을 때
        if(button.getCurrentTextColor()==0x9fa6ad)
        {
            skinProblem[index] = true;
            button.setTextColor(0xf28314);
           // button.setBackground(R.drawable.);
        }
        //선택 되어있을 때
        else
        {
            skinProblem[index] = false;
            button.setTextColor(0x9fa6ad);
            //button.setBackground(R.drawable.);
        }
    }
    /*이용약관 동의 체크박스
    View.OnClickListener onAgree = new View.OnClickListener() {
        public void onClick(View v) {
            if(checkAgree)
            {
                ckAgree.set
            }
        }
    };*/
    //가입완료 버튼
    View.OnClickListener onSignup = new View.OnClickListener() {
        public void onClick(View v) {
            //아이디 서버와 중복 검사.
            String password = etPass.getText().toString();
            String R_password = etR_Pass.getText().toString();
            if(!checkNick)
            { //메세지 팝업 띄우기. 닉네임 중복확인을 하지 않음.
             }
            else if(password.length()<6)
            { //메세지 팝업 띄우기. 비밀번호 6자 이상 해라.
            }
            else if(!password.equals(R_password))
            { //메세지 팝업 띄우기. 비밀번호 재확인 부분이 다르다.
            }
            else if(!checkGender)
            { //메세지 팝업 띄우기. 성별 체크해라
            }
            else if(!checkAge)
            { //메세지 팝업 띄우기. 나이 체크해라
            }
            else if(!ckAgree.isChecked())
            { //메세지 팝업 띄우기. 동의 체크해라
            }
            else
            {
                int[] effect = new int[checkBoolean(skinProblem)]; int j=0;
                for(int i=0;i<8;++i)
                {
                    if(skinProblem[i])
                    {
                        effect[j++] = i;
                    }
                }
                //MyApplication에 생성하기.
                User user = new User(userType, username, password, displayedName, gender, age, skinType, effect);
                if(ServerInteraction.onSignUp(user) == ServerInteraction.signUpFlag.SUCCESS)
                {
                    ((MyApplication)getApplicationContext()).setUser(user);
                    //가입성공 메세지, 다른창으로 넘어가기.
                }
                else
                {
                    //네트워크 장애 팝업창 띄워주기.
                }

            }
        }
    };
    private int checkBoolean(boolean[] array)
    {
        int num=0;
        for(int i=0;i<8;++i)
        {
            if(array[i])
            {
                num++;
            }
        }
        return num;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
