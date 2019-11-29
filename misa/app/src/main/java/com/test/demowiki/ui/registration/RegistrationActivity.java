package com.test.demowiki.ui.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.basgeekball.awesomevalidation.AwesomeValidation;
//import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import com.google.android.material.textfield.TextInputLayout;
import com.test.demowiki.R;
import com.test.demowiki.ui.login.LoginActivity;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;


public class RegistrationActivity extends AppCompatActivity {

    //the view objects
    private TextInputLayout editTextName,editTextPassword, editTextRepeatPassword, editTextEmail;
    private Button buttonNext;
    //private AwesomeValidation awesomeValidation;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(".{5,}");

    //String regexPassword =".{5,}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextName = findViewById(R.id.input_layout_name);
        editTextPassword = findViewById(R.id.input_layout_password);
        editTextRepeatPassword = findViewById(R.id.input_layout_repeat_password);
        editTextEmail = findViewById(R.id.input_layout_email);
        buttonNext = findViewById(R.id.button_next);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateUsername();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextName.getEditText().addTextChangedListener(textWatcher);

            buttonNext.setClickable(true);

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!validateUsername() | validatePassword() | validateRepeatPassword() | validateEmail()) {
                        return;
                    }
                }
            });
        }

    private boolean validateEmail(){
        String email=editTextEmail.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            editTextEmail.setErrorEnabled(false);
            editTextEmail.setError(null);

            return true;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getText(R.string.invalid_email));
            editTextEmail.requestFocus();

            return false;
        }
        else {
            editTextEmail.setErrorEnabled(false);
            editTextEmail.setError(null);

        return true;}
    }
    private boolean validateUsername(){
        String username = editTextName.getEditText().getText().toString().trim();
        if (username.equalsIgnoreCase("Mido")){
            editTextName.setError(getText(R.string.invalid_name1)+username+getText(R.string.invalid_name2));
            editTextName.requestFocus();

            return false;
        }
        else if (username.isEmpty()){
            editTextName.setError("This field cannot be empty!");
            return false;
        }else
            {
                editTextEmail.setErrorEnabled(false);
                editTextName.setError(null);
                return true;
            }
        }

    private boolean validatePassword(){
        String password =editTextPassword.getEditText().getText().toString().trim();
        if(!PASSWORD_PATTERN.matcher(password).matches()){
            editTextPassword.setError(getText(R.string.invalid_reg_password));
            editTextPassword.requestFocus();

            return false;
        }
        else{
            editTextEmail.setErrorEnabled(false);
            editTextPassword.setError(null);

            return true;
        }
    }
    private boolean validateRepeatPassword(){
        String password =editTextPassword.getEditText().getText().toString().trim();
        String repeatPassword = editTextRepeatPassword.getEditText().getText().toString().trim();
        if(!repeatPassword.equals(password)){
            editTextRepeatPassword.setError(getText(R.string.invalid_repeat_password));
            editTextRepeatPassword.requestFocus();

            return false;
        }
        else{editTextEmail.setErrorEnabled(false);
            editTextRepeatPassword.setError(null);

            return true;
        }
    }


    public void onClickLogIn(View view) {
        Intent startLogin = new Intent(this, LoginActivity.class);
        startActivity(startLogin);
    }

    public void onClickPrivacyPolicy(View view){

    }
}
