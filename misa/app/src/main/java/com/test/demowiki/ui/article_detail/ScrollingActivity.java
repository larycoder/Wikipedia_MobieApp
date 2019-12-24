package com.test.demowiki.ui.article_detail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.test.demowiki.R;

public class ScrollingActivity extends AppCompatActivity {
    private Menu menu;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        final CollapsingToolbarLayout collapsingToolbar= findViewById(R.id.toolbar_layout);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
        boolean isShow = false;
        int scrollRange = -1;
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (scrollRange == -1) {
                scrollRange = appBarLayout.getTotalScrollRange();
            }
            if((collapsingToolbar.getHeight()+verticalOffset)<(2*ViewCompat.getMinimumHeight(collapsingToolbar))){
                mToolbar.getOverflowIcon().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
//                mToolbar.getMenu().findItem(R.id.search_scroll).setIcon(R.drawable.ic_search);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            }
            else {
                mToolbar.getOverflowIcon().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
//                mToolbar.getMenu().findItem(R.id.search_scroll).setIcon(R.drawable.ic_search_white);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
            }}
        });

        /*AlertDialog*/
        builder= new AlertDialog.Builder(ScrollingActivity.this);
        ImageButton lockbtn= findViewById(R.id.lock_btn);
        lockbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage(R.string.noneditable_alert_content).setCancelable(false)
                        .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert=builder.create();
                alert.setTitle(R.string.noneditable_alert_title);
                alert.show();
            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.explore_action_scroll:
                Toast.makeText(this," Back to Explore", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.my_lists_action_scroll:
                Toast.makeText(this," Back to My lists", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.history_action_scroll:
                Toast.makeText(this," Back to History", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}
