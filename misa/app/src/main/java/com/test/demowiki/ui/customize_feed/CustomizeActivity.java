package com.test.demowiki.ui.customize_feed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.demowiki.R;

public class CustomizeActivity extends AppCompatActivity {
    //private RecyclerListFragment mRecycleListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        RecyclerListFragment fragment = new RecyclerListFragment();
        getSupportFragmentManager().beginTransaction().add(
                R.id.customize_act, fragment).commit();
    }
}
