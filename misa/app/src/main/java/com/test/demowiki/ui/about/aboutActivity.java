package com.test.demowiki.ui.about;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.test.demowiki.R;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        //Launch about activity
        final Button aboutActivityBtn =(Button) findViewById((R.id.aboutBtn));
        aboutActivityBtn.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View v) {
                Intent starAbout = new Intent(getApplicationContext(), aboutActivity.class);

                startActivity(starAbout);
            }
        });
    }
}
