package com.test.demowiki.ui.history;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.test.demowiki.R;
import com.test.demowiki.ui.explore.trending_card.Item;

import java.util.ArrayList;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

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
        enableSwipeToDeleteAndUndo();//This is or swipe to delete method
        return v;
    }

    /*These following method is for swipe to delete*/
    private void enableSwipeToDeleteAndUndo(){
        SwipeToDeleteCallback swipeToDeleteCallback= new SwipeToDeleteCallback(LEFT,RIGHT,getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position=viewHolder.getAdapterPosition();
                final Item item = adapter.getData().get(position);
                adapter.removeItem(position);
                Snackbar snackbar = Snackbar.make(getView(),"Removed from History",4000);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.restoreItem(item,position);
                        recyclerView.scrollToPosition(position);
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.snackBarColor));
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                        snackbar.getView().getLayoutParams();
                params.setMargins(10, 10, 10, 100);
                snackbar.getView().setLayoutParams(params);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkGrey));
                snackbar.show();


            }
        };
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
