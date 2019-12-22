package com.test.demowiki.ui.customize_feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.demowiki.R;
import com.test.demowiki.ui.customize_feed.help.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class CustomizeActivity extends AppCompatActivity {
    ArrayList<FeedSetting> mFeedTypeList;
    RecyclerListAdapter adapter;
    private ItemTouchHelper mItemTouchHelper;
    String[] feedTypeArr;
    String[] feedTypeDescArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CUSTOM","On Create!!");

        setContentView(R.layout.customize_activity);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        mFeedTypeList = new ArrayList<>();
        adapter = new RecyclerListAdapter(this,mFeedTypeList);
        feedTypeArr=this.getResources().getStringArray(R.array.feed_type);
        feedTypeDescArr=this.getResources().getStringArray(R.array.feed_type_desc);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        setDefaultData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Drawable mDivider = ContextCompat.getDrawable(this, R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }



    private void setDefaultData() {
        for (int i =0;i<feedTypeArr.length;i++){
            FeedSetting mfType= new FeedSetting();
            mfType.setFeedType(feedTypeArr[i]);
            mfType.setFeedTypeDesc(feedTypeDescArr[i]);
            mfType.setStatus(true);
            mFeedTypeList.add(mfType);
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customize, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_all:
                Toast.makeText(getApplicationContext(), "Show all", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_hide_all:
                Toast.makeText(getApplicationContext(), "Hide all", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_restore_default_view:
                Toast.makeText(getApplicationContext(), "Restore default views", Toast.LENGTH_LONG).show();
                return true;
            default:
                super.onOptionsItemSelected(item);
                return false;
        }
    }


}
