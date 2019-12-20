package com.test.demowiki.ui.customize_feed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.test.demowiki.R;

public class CustomizeActivity extends AppCompatActivity {
    //private RecyclerListFragment mRecycleListFragment;
    RecyclerListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        fragment = new RecyclerListFragment();
        getSupportFragmentManager().beginTransaction().add(
                R.id.customize_act, fragment).commit();
//        if (savedInstanceState != null) {
//            //Restore fragment's instance
//            fragment = (RecyclerListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "Custom Fragment");
//        }
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        //Save fragment's instance
//        getSupportFragmentManager().putFragment(outState, "Custom Fragment", fragment);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customize, menu);
        getSupportActionBar().setIcon(R.drawable.ic_triple_dot);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_all:
                Toast.makeText(getApplicationContext(),"Show all",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_hide_all:
                Toast.makeText(getApplicationContext(),"Hide all",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_restore_default_view:
                Toast.makeText(getApplicationContext(),"Restore default views",Toast.LENGTH_LONG).show();
                return true;
            default:
                super.onOptionsItemSelected(item);
                return false;
        }
    }
}
