package com.shemul.dxball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
    private final int SPLASH_SCREEN_DISPLAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashScreen.this, DxBall_FallActivity.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
				
			}
           
        }, SPLASH_SCREEN_DISPLAY_TIME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
