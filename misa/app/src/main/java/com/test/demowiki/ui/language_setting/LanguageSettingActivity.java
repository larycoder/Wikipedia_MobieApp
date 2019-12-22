package com.test.demowiki.ui.language_setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.test.demowiki.R;
import com.test.demowiki.ui.language_setting.help.ItemTouchHelperAdapter;
import com.test.demowiki.ui.language_setting.help.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class LanguageSettingActivity extends AppCompatActivity {
    ArrayList<Language> languageList;
    LanguageListAdapter adapter;
    private ItemTouchHelper mItemTouchHelper;
    String[] langArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        languageList= new ArrayList<>();

        langArr=this.getResources().getStringArray(R.array.lang_array);

        RecyclerView recyclerView = findViewById(R.id.language_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new LanguageListAdapter(this, languageList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        setData();

    }

    private void setData() {
        for(int i=0; i<langArr.length;i++){
            Language lang= new Language();
            lang.setLanguage(langArr[i]);
            lang.setLanguageNum(i+1);
            languageList.add(lang);
            adapter.notifyDataSetChanged();
        }

    }
}
