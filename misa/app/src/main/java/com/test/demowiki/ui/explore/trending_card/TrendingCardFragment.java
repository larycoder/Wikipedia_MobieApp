package com.test.demowiki.ui.explore.trending_card;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.test.demowiki.R;
import com.test.demowiki.ui.customize_feed.CustomizeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingCardFragment extends Fragment {
    RecyclerView recyclerView;
    ItemListAdapter adapter;
    ArrayList<Item> listItem;
    public TrendingCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trending_card, container, false);

        ImageButton button = v.findViewById(R.id.option_btn_trending_article);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.hide_card:
                                // do your code
                                return true;
                            case R.id.edit_card_lang:
                                return true;
                            // do your code
                            case R.id.customize_feed:
                                // do your code
                                Intent startCustomize = new Intent(getContext(), CustomizeActivity.class);
                                startActivity(startCustomize);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.news_card_option_menu);
                popup.show();
            }
        });

        /*RecyclerView*/
        recyclerView=v.findViewById(R.id.recycler_list_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Drawable mDivider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);
        listItem= new ArrayList<>();
        adapter = new ItemListAdapter(getContext(),listItem);
        recyclerView.setAdapter(adapter);

        /*set data*/
        for (int i=0;i<5;i++){
            Item item = new Item();
            item.setHeader(getString(R.string.article_title));
            item.setSubHeader(getString(R.string.article_summary));
            item.setImg(R.drawable.dinasour);
            listItem.add(item);
        }
        adapter.notifyDataSetChanged();
        return v;
    }

}
