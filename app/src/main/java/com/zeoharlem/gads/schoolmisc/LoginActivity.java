package com.zeoharlem.gads.schoolmisc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.zeoharlem.gads.schoolmisc.Utils.Helpers;
import com.zeoharlem.gads.schoolmisc.Utils.L;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox showHidePassword;
    private EditText editTextEmailAdd;
    private EditText editTextPassword;
    private TextView forgotPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        editTextEmailAdd    = findViewById(R.id.login_emailid);
        editTextPassword    = findViewById(R.id.login_password);
        showHidePassword    = findViewById(R.id.show_hide_password);
        forgotPassword      = findViewById(R.id.forgot_password);
        buttonLogin         = findViewById(R.id.loginBtn);

        showHidePassword.setOnCheckedChangeListener(this);

        //Set the OnFocused Listener when the Layout is clicked
        editTextEmailAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideSoftKeyBoard(v);
                }
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideSoftKeyBoard(v);
                }
            }
        });
        buttonLogin.setOnClickListener(loginButtonClicked);
    }

    private void submitInputsAction(){
        if(validateRow()){
            Intent intent   = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    View.OnClickListener loginButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submitInputsAction();
        }
    };

    private void checkLoginDetailApi(){
        String uriString    = Helpers.URL_STRING;
    }

    private boolean validateRow(){
        String textEmailId  = editTextEmailAdd.getText().toString().trim();
        String textPassword = editTextPassword.getText().toString().trim();
        Pattern pattern     = Pattern.compile(Helpers.regEx);
        Matcher matcher     = pattern.matcher(textEmailId);
        if(textEmailId.equals("") || textEmailId.isEmpty()){
            L.l(getApplicationContext(), "Your Username Field is Empty");
            return false;
        }
        else if(textPassword.equals("") || textPassword.isEmpty()){
            L.l(getApplicationContext(), "Your Password Field is Empty");
            return false;
        }
        else if(!matcher.find()){
            L.l(getApplicationContext(), "Your Email Address is Invalid Format");
            return false;
        }
        return true;
    }

    private void hideSoftKeyBoard(View view) {
        InputMethodManager inputMethodManager   = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.getId() == R.id.show_hide_password){
            if(!b){
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
            else{
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        }
    }
}