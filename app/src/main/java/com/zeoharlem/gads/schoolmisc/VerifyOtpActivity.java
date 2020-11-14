package com.zeoharlem.gads.schoolmisc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.zeoharlem.gads.schoolmisc.Receivers.OtpVerifyResponse;
import com.zeoharlem.gads.schoolmisc.Utils.L;
import com.zeoharlem.gads.schoolmisc.Utils.MyConfig;

import java.util.Objects;

public class VerifyOtpActivity extends AppCompatActivity implements OtpVerifyResponse.iOtpVerifyResponseListener {

    private EditText code1, code2, code3, code4, code5, code6;
    private TextView mTextViewMobile;
    private String verificationId;
    private Button buttonVerify;
    private ProgressBar mProgressBar;
    private String mobilePhoneNumber;

    private OtpVerifyResponse myReceiverOtp;
    private SharedPreferences sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        myReceiverOtp   = new OtpVerifyResponse();

        //FirebaseApp.initializeApp(this);

        mTextViewMobile = findViewById(R.id.mobilePhoneNum);
        buttonVerify    = findViewById(R.id.buttonVerify);
        mProgressBar    = findViewById(R.id.progressBar);
        verificationId  = getIntent().getStringExtra("verificationId");
        mobilePhoneNumber   = String.format("+234-%s", getIntent().getStringExtra("mobile_num"));
        mTextViewMobile.setText(mobilePhoneNumber);

        setEditTextCodesActions();

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code1.getText().toString().trim().isEmpty() ||
                        code2.getText().toString().trim().isEmpty() ||
                        code3.getText().toString().trim().isEmpty() ||
                        code4.getText().toString().trim().isEmpty() ||
                        code5.getText().toString().trim().isEmpty()){
                    L.l(getApplicationContext(), "Enter the code sent to you");
                    return;
                }
                String codeSent = code1.getText().toString().trim()
                        + code2.getText().toString().trim()
                        + code3.getText().toString().trim()
                        + code4.getText().toString().trim()
                        + code5.getText().toString().trim()
                        + code6.getText().toString().trim();
                if(verificationId != null){
                    mProgressBar.setVisibility(View.VISIBLE);
                    buttonVerify.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, codeSent);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            buttonVerify.setVisibility(View.VISIBLE);
                            if(task.isSuccessful()){
                                shareTaskPreference(mobilePhoneNumber, true);
                                Intent intent   = new Intent(VerifyOtpActivity.this, RegisterActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else{
                                L.l(VerifyOtpActivity.this, Objects.requireNonNull(task.getException()).getMessage());
                            }
                        }
                    });
                }
            }
        });

        //On SMSMessage Response Perform Actions
        myReceiverOtp.setOtpVerifyResponseListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter   = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(myReceiverOtp, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiverOtp);
    }

    //Set the preference task
    private void shareTaskPreference(String phoneNumber, boolean verified){
        sharePref   = getSharedPreferences(MyConfig.MY_PHONE_PREF_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        editor.putString(MyConfig.PHONE_NUMBER_USED, phoneNumber);
        editor.putBoolean(MyConfig.VERIFY_PHONE_TASK, verified);
        editor.apply();
    }

    private void setEditTextCodesActions(){
        code1   = findViewById(R.id.inputCode1);
        code2   = findViewById(R.id.inputCode2);
        code3   = findViewById(R.id.inputCode3);
        code4   = findViewById(R.id.inputCode4);
        code5   = findViewById(R.id.inputCode5);
        code6   = findViewById(R.id.inputCode6);

        //AddtextchangeListener to each editext
//        code1.addTextChangedListener(code1TextListener);
//        code2.addTextChangedListener(code2TextListener);
//        code3.addTextChangedListener(code3TextListener);
//        code4.addTextChangedListener(code4TextListener);
//        code5.addTextChangedListener(code5TextListener);
    }

//    TextWatcher code1TextListener   = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            if(!charSequence.toString().trim().isEmpty()){
//                code2.requestFocus();
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };
//
//    TextWatcher code2TextListener   = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            code3.requestFocus();
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };
//
//    TextWatcher code3TextListener   = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            code4.requestFocus();
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };
//
//    TextWatcher code4TextListener   = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            code5.requestFocus();
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };
//
//    TextWatcher code5TextListener   = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            code6.requestFocus();
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };


    @Override
    public void onSmsReceived(String sender, String smsMessage) {
        String[] smsMessageArr  = smsMessage.trim().split("");
        code1.setText(smsMessageArr[0]);
        code2.setText(smsMessageArr[1]);
        code3.setText(smsMessageArr[2]);
        code4.setText(smsMessageArr[3]);
        code5.setText(smsMessageArr[4]);
        code6.setText(smsMessageArr[5]);
        L.l(getApplicationContext(), "From VerifyOtp:= "+sender+" : " + smsMessage);
    }

}