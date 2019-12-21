package com.test.demowiki.ui.customize_feed;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.demowiki.R;
import com.test.demowiki.ui.customize_feed.help.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerListFragment extends Fragment {
//    ArrayList<String> mFeedTypeList;
    ArrayList<FeedSetting> mFeedTypeList;
    RecyclerListAdapter adapter;
    private ItemTouchHelper mItemTouchHelper;
    String[] feedTypeArr;
    String[] feedTypeDescArr;

    public RecyclerListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_customize_main, container, false);

        mFeedTypeList = new ArrayList<>();
        adapter = new RecyclerListAdapter(getContext(),mFeedTypeList);
        feedTypeArr=getContext().getResources().getStringArray(R.array.feed_type);
        feedTypeDescArr=getContext().getResources().getStringArray(R.array.feed_type_desc);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        setDefaultData();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Drawable mDivider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(mDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        return v;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if(savedInstanceState!=null){}
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
    //    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mFeedTypeList = new ArrayList<>();
//        adapter = new RecyclerListAdapter(getContext(),mFeedTypeList);
//
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//        setDefaultData();
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                LinearLayoutManager.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(recyclerView);
//    }
//    private int check;
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("check",check);
//    }

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


}
