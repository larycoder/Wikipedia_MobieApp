package com.test.demowiki.ui.history;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.demowiki.R;
import com.test.demowiki.ui.explore.trending_card.Item;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Item> listItem;
    ItemListAdapter adapter;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView=v.findViewById(R.id.history_list_item);
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
