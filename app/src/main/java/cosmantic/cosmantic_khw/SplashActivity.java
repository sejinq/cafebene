package cosmantic.cosmantic_khw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/** 스플래시 (로딩화면) 액티비티 */
public class SplashActivity extends AppCompatActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		try{
			// 2초간 대기하고
			Thread.sleep(2000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}

		// 메인 액티비티로 넘어간다. -> fragment 액티비티로 넘어가게!!!!
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}
}