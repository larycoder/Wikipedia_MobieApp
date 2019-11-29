package com.test.demowiki.ui.setting;

import androidx.appcompat.app.AppCompatActivity;
import com.test.demowiki.R;
import com.test.demowiki.ui.language_setting.LanguageSettingActivity;

import android.content.Intent;
import android.os.Bundle;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }
    public void onClickLanguage(){
        Intent startLanguageSetting = new Intent(this, LanguageSettingActivity.class);
        startActivity(startLanguageSetting);
    }

}
