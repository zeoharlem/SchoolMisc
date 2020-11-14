package com.zeoharlem.gads.schoolmisc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.zeoharlem.gads.schoolmisc.Utils.L;

import java.util.concurrent.TimeUnit;

public class SendOtpActivity extends AppCompatActivity {

    private EditText inputMobileNum;
    private Button sendOtpButton;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        mProgressBar    = findViewById(R.id.progressBar);
        setReadyActions();

        //Call permission grant
        requestForPermissionsGranted();
    }

    private void setReadyActions(){
        sendOtpButton   = findViewById(R.id.phoneVerify);
        inputMobileNum  = findViewById(R.id.phone_number_id);
        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputMobileNum.getText().toString().isEmpty()){
                    mProgressBar.setVisibility(View.VISIBLE);
                    sendOtpButton.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+234"+inputMobileNum.getText().toString().trim(),
                            60, TimeUnit.SECONDS,SendOtpActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    mProgressBar.setVisibility(View.GONE);
                                    sendOtpButton.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    mProgressBar.setVisibility(View.GONE);
                                    sendOtpButton.setVisibility(View.VISIBLE);
                                    L.l(SendOtpActivity.this, e.getMessage());
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationId, forceResendingToken);
                                    mProgressBar.setVisibility(View.GONE);
                                    sendOtpButton.setVisibility(View.VISIBLE);

                                    Intent intent   = new Intent(SendOtpActivity.this, VerifyOtpActivity.class);
                                    intent.putExtra("mobile_num", inputMobileNum.getText().toString().trim());
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });
        inputMobileNum.addTextChangedListener(mTextWatcher);
    }

    private void requestForPermissionsGranted(){
        if(ContextCompat.checkSelfPermission(SendOtpActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SendOtpActivity.this, new String[]{
                    Manifest.permission.RECEIVE_SMS
            }, 100);
        }
    }



    TextWatcher mTextWatcher    = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.toString().length() == 1 && editable.toString().startsWith("0")){
                editable.clear();
            }
        }
    };
}