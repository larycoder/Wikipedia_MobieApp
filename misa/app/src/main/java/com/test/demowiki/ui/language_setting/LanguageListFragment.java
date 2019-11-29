package com.test.demowiki.ui.language_setting;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.demowiki.R;
import com.test.demowiki.ui.language_setting.help.ItemTouchHelperAdapter;
import com.test.demowiki.ui.language_setting.help.SimpleItemTouchHelperCallback;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageListFragment extends Fragment {

    ArrayList<String> mFeedTypeList;
    LanguageListAdapter adapter;
    private ItemTouchHelper mItemTouchHelper;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LanguageListAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.language_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public LanguageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language_list, container, false);
    }

}
