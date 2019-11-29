package com.test.demowiki;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import com.test.demowiki.ui.customize_feed.CustomizeActivity;
import com.test.demowiki.ui.login.LoginActivity;
import com.test.demowiki.ui.setting.SettingActivity;
import com.test.demowiki.ui.about.aboutActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Customize listener for menu
        navigationView.setNavigationItemSelectedListener(new
                NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        switch (menuItem.getItemId()){
                            case R.id.nav_setting:
                                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                                drawer.closeDrawers();
                                return true;
                            case R.id.nav_customize:
                                startActivity(new Intent(MainActivity.this, CustomizeActivity.class));
                                drawer.closeDrawers();
                                return true;
                            case R.id.nav_about:
                                startActivity(new Intent(MainActivity.this, aboutActivity.class));
                                drawer.closeDrawers();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home).setDrawerLayout(drawer).build();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger_icon_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        return true;
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onClickLogIn(View view) {
        Intent startLogIn = new Intent(this, LoginActivity.class);
        startActivity(startLogIn);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}

