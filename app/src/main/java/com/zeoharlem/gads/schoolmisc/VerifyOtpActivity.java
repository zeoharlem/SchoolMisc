package com.zeoharlem.gads.schoolmisc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class VerifyOtpActivity extends AppCompatActivity {

    private EditText code1, code2, code3, code4, code5, code6;
    private TextView mTextViewMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        mTextViewMobile = findViewById(R.id.mobilePhoneNum);
        mTextViewMobile.setText(String.format("+234-%s", getIntent().getStringExtra("mobile_num")));
        setEditTextCodesActions();
    }

    private void setEditTextCodesActions(){
        code1   = findViewById(R.id.inputCode1);
        code2   = findViewById(R.id.inputCode2);
        code3   = findViewById(R.id.inputCode3);
        code4   = findViewById(R.id.inputCode4);
        code5   = findViewById(R.id.inputCode5);
        code6   = findViewById(R.id.inputCode6);

        //AddtextchangeListener to each editext
        code1.addTextChangedListener(code1TextListener);
        code2.addTextChangedListener(code2TextListener);
        code3.addTextChangedListener(code3TextListener);
        code4.addTextChangedListener(code4TextListener);
        code5.addTextChangedListener(code5TextListener);
    }

    TextWatcher code1TextListener   = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!charSequence.toString().trim().isEmpty()){
                code2.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher code2TextListener   = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            code3.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher code3TextListener   = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            code4.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher code4TextListener   = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            code5.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher code5TextListener   = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            code6.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}