package com.test.demowiki.ui.article_detail;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.VolleySingleton;
import com.test.demowiki.wikiAPI.wikipediaAPI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.test.demowiki.R;


public class ScrollingActivity extends AppCompatActivity {
    private Menu menu;
    AlertDialog.Builder builder;
    boolean isPress = false;
    ImageButton saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        updateDetailArticle();
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        final CollapsingToolbarLayout collapsingToolbar= findViewById(R.id.toolbar_layout);
        AppBarLayout mAppBarLayout = findViewById(R.id.app_bar);
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
//        builder= new AlertDialog.Builder(ScrollingActivity.this);
//        ImageButton lockbtn= findViewById(R.id.lock_btn);
//        lockbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.setMessage(R.string.noneditable_alert_content).setCancelable(false)
//                        .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alert=builder.create();
//                alert.setTitle(R.string.noneditable_alert_title);
//                alert.show();
//            }
//        });

        saveBtn = findViewById(R.id.save_article_detail);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPress==false){
                    saveBtn.setImageResource(R.drawable.ic_bookmark_blue);
                    Toast.makeText(ScrollingActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    isPress =true;
                }
                else{
                    saveBtn.setImageResource(R.drawable.ic_save_article);
                    Toast.makeText(ScrollingActivity.this, "Removed from your list", Toast.LENGTH_SHORT).show();
                    isPress =false;
                }
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

    private void updateDetailArticle(){
        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().get("INTERNET").equals("YES")) {
                ((ImageView) findViewById(R.id.expandedImage)).setImageDrawable(null);
                VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, getIntent().getExtras().getString("articleDescriptionUrl"), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        wikipediaAPI wikiAPI = new wikipediaAPI();
                        if (!getIntent().getExtras().getString("articleImageUrl").equals("Not Exit Image")) {
                            VolleySingleton.getQueue().add(new ImageRequest(getIntent().getExtras().getString("articleImageUrl"), new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    ((ImageView) findViewById(R.id.expandedImage)).setImageBitmap(response);
                                }
                            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null));
                        } else {
                            (findViewById(R.id.expandedImage)).setVisibility(View.GONE);
                        }

                        TextView articleDetailHeader = findViewById(R.id.article_detail_header);
                        articleDetailHeader.setText(wikiAPI.getExtract(response).get(0));
                        TextView articleDetailSubHeader = findViewById(R.id.article_detail_sub_header);
                        articleDetailSubHeader.setText(wikiAPI.getExtract(response).get(1));
                    }
                }, null));
            }
            else if(getIntent().getExtras().getString("INTERNET").equals("NO")){
                TextView articleDetailHeader = findViewById(R.id.article_detail_header);
                articleDetailHeader.setText(getIntent().getExtras().getString("featuredFeedTitle"));
                TextView articleDetailSubHeader = findViewById(R.id.article_detail_sub_header);
                articleDetailSubHeader.setText(getIntent().getExtras().getString("featuredFeedDescription"));
            }
        }
        //WebView webView = findViewById(R.id.webview);
        //webView.setWebViewClient(new WebViewClient());
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl(getIntent().getExtras().getString("articleDescriptionUrl"));
        // remove bar navigation of article
    }

}
