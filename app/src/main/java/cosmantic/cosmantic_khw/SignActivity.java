package cosmantic.cosmantic_khw;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends Activity {

    EditText etID, etPass, etR_Pass, etNick, etAge;
    Button btc1, btc2, btc3, btc4, btc5, btc6, btc7, btc8,  bts1, bts2, bts3, bts4, btFind, btF, btM;
    //gck = gendercheck, ����üũ, sck = skincheck, �Ǻ�üũ

    String nick;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etID = (EditText)findViewById(R.id.editText1);
        etPass = (EditText)findViewById(R.id.editText2);
        etR_Pass = (EditText)findViewById(R.id.editText3);
        etNick = (EditText)findViewById(R.id.editText4);
        etAge = (EditText)findViewById(R.id.editText5);

        btFind = (Button)findViewById(R.id.button1);
        btFind.setOnClickListener(new onbtfind());
        btM = (Button)findViewById(R.id.button2);
        btM.setOnClickListener(new onbtM());
        btF = (Button)findViewById(R.id.button3);
        btF.setOnClickListener(new onbtF());

        bts1 = (Button)findViewById(R.id.button4);
        bts1.setOnClickListener(new onbts1());
        bts2 = (Button)findViewById(R.id.button5);
        bts2.setOnClickListener(new onbts2());
        bts3 = (Button)findViewById(R.id.button6);
        bts3.setOnClickListener(new onbts3());
        bts4 = (Button)findViewById(R.id.button7);
        bts4.setOnClickListener(new onbts4());

        btc1 = (Button)findViewById(R.id.button8);
        btc1.setOnClickListener(new onbtc1());
        btc2 = (Button)findViewById(R.id.button9);
        btc2.setOnClickListener(new onbtc2());
        btc3 = (Button)findViewById(R.id.button10);
        btc3.setOnClickListener(new onbtc3());
        btc4 = (Button)findViewById(R.id.button11);
        btc4.setOnClickListener(new onbtc4());
        btc5 = (Button)findViewById(R.id.button12);
        btc5.setOnClickListener(new onbtc5());
        btc6 = (Button)findViewById(R.id.button13);
        btc6.setOnClickListener(new onbtc6());
        btc7 = (Button)findViewById(R.id.button14);
        btc7.setOnClickListener(new onbtc7());
        btc8 = (Button)findViewById(R.id.button15);
        btc8.setOnClickListener(new onbtc8());


    }


    public class onbtfind implements View.OnClickListener {
        public void onClick(View v) {

            nick = etNick.getText().toString();
            //���� �����Ϳ� �� '1'�ڸ�
            if(nick.equals("1"))
            {
                etNick.setText(nick+"  ����� �� ���� �г��� �Դϴ�.");
            }
            else
            {
                nick=nick+"  ��� ������ �г��� �Դϴ�.";
                etNick.setText(nick);
            }

        }
    }
    public class onbtM implements View.OnClickListener {
        public void onClick(View v) {
        }

        }
    public class onbtF implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbts1 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbts2 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbts3 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbts4 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc1 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc2 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc3 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc4 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc5 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc6 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc7 implements View.OnClickListener {
        public void onClick(View v) {
        }

    }
    public class onbtc8 implements View.OnClickListener {
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




