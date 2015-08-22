package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignUpActivity extends Activity implements View.OnTouchListener {
    public static String USER_TYPE = "cosmantic.cosmantic_khw.SignUpActivity.USER_TYPE";
    public static String USER_NAME = "cosmantic.cosmantic_khw.SignUpActivity.USER_NAME";

    EditText etID, etPass, etR_Pass, etNick;
    Button btNick, btMale, btFemale;
    Button[] btAge = new Button[5];
    Button[] btSkinType= new Button[4];
    Button[] btEffect = new Button[8];
    CheckBox ckAgree;
    Button btSignup;
    TextWatcher nickWatcher;

    String username=null;
    String displayedName;
    boolean gender;
    int age, skinType;
    boolean[] skinProblem = new boolean[8];

    User signingUser;

    //입력 유무 체크
    boolean checkNick=false, checkGender = false, checkAge = false;
    //관심효능 변수
    int effect=0, userType;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingView();

        Intent intent = getIntent();
        userType = intent.getIntExtra(USER_TYPE, User.UserType.EMAIL);
        signingUser = new User();
        settingUserType(userType, intent.getStringExtra(USER_NAME));

        // 검색 버튼은 안 쓰므로 안 보이게 설정
        ImageButton imgButton = (ImageButton)findViewById(R.id.searchButton);
        imgButton.setVisibility(View.GONE);

        // 액션바 설정
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(ClickListener);
    }

    @Override
    // EditText 밖을 터치하면 키보드 가리게 설정.
    public boolean onTouch(View v, MotionEvent event){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((EditText)findViewById(R.id.etNick)).getWindowToken(), 0);
        imm.hideSoftInputFromWindow(((EditText)findViewById(R.id.etPass)).getWindowToken(), 0);
        imm.hideSoftInputFromWindow(((EditText)findViewById(R.id.etRePass)).getWindowToken(), 0);

        return true;
    }

    View.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backButton:
                    finish();
                    break;
            }
        }
    };


    private void limitInput(CharSequence repair){
        etNick.removeTextChangedListener(nickWatcher);
        etNick.setText(repair);
        etNick.addTextChangedListener(nickWatcher);
    }

    private void settingView(){
        setContentView(R.layout.activity_sign_up);
        ((TextView)findViewById(R.id.titleText)).setText(R.string.signup_title);
        //회원가입시 유저 정보. 아이디, 닉네임, 비밀번호, 성별, 나이
        etID = (EditText)findViewById(R.id.etID);
        etNick = (EditText)findViewById(R.id.etNick);
        nickWatcher = new TextWatcher() {
            CharSequence repair = null;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                repair = etNick.getText();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().getBytes().length > 14){
                    limitInput(repair);
                    etNick.removeTextChangedListener(this);
                    etNick.setText(repair);
                    etNick.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etNick.addTextChangedListener(nickWatcher);
        etPass = (EditText)findViewById(R.id.etPass);
        etR_Pass = (EditText)findViewById(R.id.etRePass);

        btNick = (Button)findViewById(R.id.check_button);
        btNick.setOnClickListener(onNick);

        btMale = (Button)findViewById(R.id.gender_male);
        btMale.setOnClickListener(listener);
        btFemale = (Button)findViewById(R.id.gender_female);

        btFemale.setOnClickListener(listener);

        btAge[0] = (Button)findViewById(R.id.age10);
        btAge[1] = (Button)findViewById(R.id.age20);
        btAge[2] = (Button)findViewById(R.id.age30);
        btAge[3] = (Button)findViewById(R.id.age40);
        btAge[4] = (Button)findViewById(R.id.age50);
        for(int i=0;i<5;++i)
        {
            btAge[i].setOnClickListener(listener2);
        }
        //유저의 피부타입 정보.  1.건성, 2.지성, 3.민감성, 0.모름
        btSkinType[0] = (Button)findViewById(R.id.skin_unknown);
        btSkinType[1] = (Button)findViewById(R.id.skin_dry);
        btSkinType[2] = (Button)findViewById(R.id.skin_oily);
        btSkinType[3] = (Button)findViewById(R.id.skin_sensitive);
        for(int i=0;i<4;++i)
        {
            btSkinType[i].setOnClickListener(listener3);
        }

        //유저의 관심효능 0.피지, 1.주름, 2.유분, 3.다크써클, 4.아토피, 5.여드름, 6.피부톤, 7.민감성
        btEffect[0] = (Button)findViewById(R.id.effect_sebum);
        btEffect[1] = (Button)findViewById(R.id.effect_wrinkle);
        btEffect[2] = (Button)findViewById(R.id.effect_oil);
        btEffect[3] = (Button)findViewById(R.id.effect_dark);
        btEffect[4] = (Button)findViewById(R.id.effect_dry);
        btEffect[5] = (Button)findViewById(R.id.effect_trouble);
        btEffect[6] = (Button)findViewById(R.id.effect_color);
        btEffect[7] = (Button)findViewById(R.id.effect_sensitive);

        for(int i=0;i<8;++i)
        {
            btEffect[i].setOnClickListener(listener4);
        }

        btSignup = (Button)findViewById(R.id.signup_button);
        btSignup.setOnClickListener(onSignup);

        ckAgree = (CheckBox)findViewById(R.id.checkBox);
        // ckAgree.setOnClickListener(onAgree);

        //폰트 설정
        TextView[] regularSet = new TextView[]{
                (TextView)findViewById(R.id.titleText),
                etID, etPass, etR_Pass, etNick, btSignup, btMale, btFemale,
                btAge[0],btAge[1],btAge[2],btAge[3],btAge[4],
                btSkinType[0],btSkinType[1],btSkinType[2],btSkinType[3],
                btEffect[0],btEffect[1],btEffect[2],btEffect[3],btEffect[4],btEffect[5],btEffect[6],btEffect[7]
        };
        TextView[] mediumSet = new TextView[]{
                (TextView)findViewById(R.id.inform),
                (TextView)findViewById(R.id.sel_inform),
                (TextView)findViewById(R.id.tvId),
                (TextView)findViewById(R.id.tv2),
                (TextView)findViewById(R.id.tv3),
                (TextView)findViewById(R.id.tv4),
                (TextView)findViewById(R.id.tv5),
                (TextView)findViewById(R.id.tv6),
                (TextView)findViewById(R.id.tv7),
                (TextView)findViewById(R.id.tv8),
                (TextView)findViewById(R.id.tv8_small)
        };
        FontApplyer.setFonts(getApplicationContext(),regularSet,FontApplyer.Font.NotoSans,FontApplyer.Style.Regular);
        FontApplyer.setFonts(getApplicationContext(),mediumSet,FontApplyer.Font.NotoSans,FontApplyer.Style.Medium);
        FontApplyer.setFont(getApplicationContext(),(TextView)findViewById(R.id.checkBox), FontApplyer.Font.NotoSans, FontApplyer.Style.Light);
    }

    //유저 타입에 따라 화면 레이아웃 바꿔 주기.
    private void settingUserType(int userType, String id)
    {
        //그냥 가입하기, 플래그로 바꿔주기..?설정 없음..
        //이메일이 아니면(카카오톡 또는 페이스북)
        if(userType!=User.UserType.EMAIL)
        {
            //dp값 설정해 주기.>기기에 맞게 바뀌게 dp 코딩 해 주어야함.
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int size = Math.round(50 * dm.density);
            //dp값 변경하고자 하는 부분에 값 넣어주기.
            RelativeLayout.LayoutParams plControl = (RelativeLayout.LayoutParams) ((RelativeLayout) findViewById(R.id.rel1)).getLayoutParams();
            plControl.topMargin = size;
            ((RelativeLayout) findViewById(R.id.rel1)).setLayoutParams(plControl);
            //아이디, 비밀번호 입력란 사라지기.
            findViewById(R.id.signup_id_row).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.passRow)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.repassRow)).setVisibility(View.GONE);
            etID.setText(id);
            etID.setFocusable(false);
            etID.setClickable(false);
            username = id;
        }
    }
    //닉네임 중복확인
    View.OnClickListener onNick = new View.OnClickListener() {
        public void onClick(View v) {
            displayedName = etNick.getText().toString();
            checkNick=ServerInteraction.compareNickName(displayedName);
            if(checkNick)
            {
                //팝업창 띄우기. 사용가능한 어쩌구..
                Toast.makeText(SignUpActivity.this,"사용가능한 닉네임입니다.",Toast.LENGTH_SHORT).show();
            }
            else
            {
                //팝업창 띄우기. 사용가능한 어쩌구..
                Toast.makeText(SignUpActivity.this,"이미 사용중인 닉네임입니다.",Toast.LENGTH_SHORT).show();
            }
        }
    };
    //성별 선택
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            checkGender = true;
            //모든 버튼 해제 이미지로 초기화
            initSelect(btMale);initSelect(btFemale);
            if(Build.VERSION.SDK_INT < 16) {
                switch (v.getId()) {
                    //선택된 버튼만 선택 이미지로 바꿔주고, gender에 값 저장.
                    case R.id.gender_male:
                        gender = false;
                        selectEvent(btMale);
                        break;
                    case R.id.gender_female:
                        selectEvent(btFemale);
                        gender = true;
                        break;
                }
            }else{
                switch (v.getId()) {
                    //선택된 버튼만 선택 이미지로 바꿔주고, gender에 값 저장.
                    case R.id.gender_male:
                        gender = false;
                        selectEvent(btMale);
                        break;
                    case R.id.gender_female:
                        selectEvent(btFemale);
                        gender = true;
                        break;
                }
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
                case R.id.age10:
                    selectEvent(btAge[0]);
                    age = 10; break;
                case R.id.age20:
                    selectEvent(btAge[1]);
                    age = 20; break;
                case R.id.age30:
                    selectEvent(btAge[2]);
                    age = 30; break;
                case R.id.age40:
                    selectEvent(btAge[3]);
                    age = 40; break;
                case R.id.age50:
                    age = 50;
                    selectEvent(btAge[4]);
                    break;
            }
        }
    };
    private void initSelect(Button button) {
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.unselect_small_box));
        button.setTextColor(getApplicationContext().getResources().getColor(R.color.signup_btunselect));
    }
    private void selectEvent(Button button) {
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_small_box));
        button.setTextColor(getApplicationContext().getResources().getColor(R.color.signup_btselect));
    }
    //피부타입 선택
    View.OnClickListener listener3 = new View.OnClickListener() {
        public void onClick(View v) {
            for(int i=0;i<4;++i)
            {
                initSelect(btSkinType[i]);
            }
            switch (v.getId()) {
                case R.id.skin_unknown:
                    selectEvent(btSkinType[0]);
                    skinType=0; break;
                case R.id.skin_dry:
                    selectEvent(btSkinType[1]);
                    skinType=1; break;
                case R.id.skin_oily:
                    selectEvent(btSkinType[2]);
                    skinType=2; break;
                case R.id.skin_sensitive:
                    selectEvent(btSkinType[3]);
                    skinType=3; break;
            }
        }
    };
    //관심효능 선택
    View.OnClickListener listener4 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.effect_sebum:
                    multiSelect(btEffect[0], 0);break;
                case R.id.effect_wrinkle:
                    multiSelect(btEffect[1], 1);break;
                case R.id.effect_oil:
                    multiSelect(btEffect[2], 2);break;
                case R.id.effect_dark:
                    multiSelect(btEffect[3], 3);break;
                case R.id.effect_dry:
                    multiSelect(btEffect[4], 4);break;
                case R.id.effect_trouble:
                    multiSelect(btEffect[5], 5);break;
                case R.id.effect_color:
                    multiSelect(btEffect[6], 6);break;
                case R.id.effect_sensitive:
                    multiSelect(btEffect[7], 7);break;
            }
        }
    };
    //버튼 상태에 따라 처리를 달리 해 준다.
    private void multiSelect(Button button, int index)
    {
        //선택 안되어있을 때>선택이미지로
        if(skinProblem[index]==false)
        {
            skinProblem[index] = true;
            selectEvent(button);
        }
        //선택 되어있을 때>선택 해제 이미지로
        else
        {
            skinProblem[index] = false;
            initSelect(button);
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
            boolean signable = true;
            //아이디 서버와 중복 검사.
            if(userType == User.UserType.EMAIL) {
                username = etID.getText().toString();
                String[] checkSet = username.split("@");
                if(checkSet.length != 2){
                    Toast.makeText(SignUpActivity.this, "아이디를 이메일 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                    signable = false;
                }
                else if(!ServerInteraction.compareUserName(username)){
                    Toast.makeText(SignUpActivity.this, "이미 가입된 이메일입니다.",Toast.LENGTH_SHORT).show();
                    signable = false;
                }
            }
            String password = etPass.getText().toString();
            String R_password = etR_Pass.getText().toString();
            if(!checkNick)
            { //메세지 팝업 띄우기. 닉네임 중복확인을 하지 않음.
                Toast.makeText(SignUpActivity.this, "닉네임 중복 확인을 해주세요.",Toast.LENGTH_SHORT).show();
                signable = false;
            }
            if(userType == User.UserType.EMAIL) {
                if (password.length() < 6) { //메세지 팝업 띄우기. 비밀번호 6자 이상 해라.
                    Toast.makeText(SignUpActivity.this, "비밀번호를 6자 이상 설정해주세요.", Toast.LENGTH_SHORT).show();
                    signable = false;
                }
                if (!password.equals(R_password)) { //메세지 팝업 띄우기. 비밀번호 재확인 부분이 다르다.
                    Toast.makeText(SignUpActivity.this, "비밀번호와 재확인 번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    signable = false;
                }
            }
            if(!checkGender)
            { //메세지 팝업 띄우기. 성별 체크해라
                Toast.makeText(SignUpActivity.this, "성별을 설정해주세요.",Toast.LENGTH_SHORT).show();
                signable = false;
            }
            else if(!checkAge)
            { //메세지 팝업 띄우기. 나이 체크해라
                Toast.makeText(SignUpActivity.this, "나이를 설정해주세요.",Toast.LENGTH_SHORT).show();
                signable = false;
            }
            if(!ckAgree.isChecked())
            { //메세지 팝업 띄우기. 동의 체크해라
                Toast.makeText(SignUpActivity.this, "이용 약관 동의를 하셔야 가입할 수 있습니다.",Toast.LENGTH_SHORT).show();
                signable = false;
            }
            if(signable)
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

                //임시 테스트
//                String text = "아이디 : "+user.getUsername()+"\n 비밀번호 : "+user.getPassword()+"\n 닉네임 :"+user.getDisplayedName();
//                Toast.makeText(SignUpActivity.this, text, Toast.LENGTH_SHORT).show();
//                text = "나이 : "+user.getAge()+"\n 피부타입 : "+user.getSkinType()+"\n 관심효과 :"+user.getSkinProblem();
//                Toast.makeText(SignUpActivity.this, text, Toast.LENGTH_SHORT).show();

                if(ServerInteraction.onSignUp(user,userType) == ServerInteraction.signUpFlag.SUCCESS)
                {
                    if(userType!= User.UserType.EMAIL) {
                        ((MyApplication) getApplicationContext()).setUser(ServerInteraction.onLoginWithParseUser(ParseUser.getCurrentUser()));
                        startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                        finish();
                    }else {
                        Log.d("User Type Check", "" + userType);
//                    ((MyApplication)getApplicationContext()).setUser(user);
                        Toast.makeText(SignUpActivity.this, "가입 성공!", Toast.LENGTH_SHORT).show();
                        //가입성공 메세지, 다른창으로 넘어가기.
                        ParseUser.logInInBackground(user.getUsername(), user.getPassword(), new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {
                                ((MyApplication) getApplicationContext()).setUser(ServerInteraction.onLoginWithParseUser(parseUser));
                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                                finish();
                            }
                        });
                    }
                }
                else
                {
                    //네트워크 장애 팝업창 띄워주기.
                    Toast.makeText(SignUpActivity.this, "가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
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