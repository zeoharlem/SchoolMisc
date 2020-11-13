package com.zeoharlem.gads.schoolmisc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.zeoharlem.gads.schoolmisc.Utils.MyConfig;

public class WelcomeScreenActivity extends AppCompatActivity {

    private boolean verifyPhone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
        waitSyncMainActivity();
    }

    private void waitSyncMainActivity(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checkVerifyPhoneTask()){
                    Intent intent   = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else{
                    Intent intent   = new Intent(getApplicationContext(), SendOtpActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        }, 2000);
    }

    private boolean checkVerifyPhoneTask(){
        SharedPreferences sharedPreferences = getSharedPreferences(MyConfig.MY_PHONE_PREF_STORE, MODE_PRIVATE);
        verifyPhone                         = sharedPreferences.getBoolean(MyConfig.VERIFY_PHONE_TASK, false);
        return verifyPhone;
    }
}