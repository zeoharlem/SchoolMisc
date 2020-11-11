package com.zeoharlem.gads.schoolmisc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendOtpActivity extends AppCompatActivity {

    private EditText inputMobileNum;
    private Button sendOtpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        setReadyActions();
    }

    private void setReadyActions(){
        sendOtpButton   = findViewById(R.id.phoneVerify);
        inputMobileNum  = findViewById(R.id.phone_number_id);
        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputMobileNum.getText().toString().isEmpty()){
                    Intent intent   = new Intent(SendOtpActivity.this, VerifyOtpActivity.class);
                    intent.putExtra("mobile_num", inputMobileNum.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
        inputMobileNum.addTextChangedListener(mTextWatcher);
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