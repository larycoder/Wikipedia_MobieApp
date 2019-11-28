package com.test.demowiki.ui.home;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.test.demowiki.MainActivity;
import com.test.demowiki.R;

public class HomeFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tab;
    private ViewPager pager;

    private int[] tabIcons = {
            R.drawable.ic_history,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_manage
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager());

        pager = view.findViewById(R.id.pager);
        pager.setAdapter(adapter);


        tab = view.findViewById(R.id.tab);
        tab.setupWithViewPager(pager);
        setUpTabIcons();
        return view;
    }

    private void setUpTabIcons() {

        TextView tab_explore = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_explore.setText("Explore");
        tab_explore.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_explore, 0, 0);
        tab.getTabAt(0).setCustomView(tab_explore);

        TextView tab_mylist = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_mylist.setText("My lists");
        tab_mylist.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mylists, 0, 0);
        tab.getTabAt(1).setCustomView(tab_mylist);

        TextView tab_history = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tab_history.setText("History");
        tab_history.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_history, 0, 0);
        tab.getTabAt(2).setCustomView(tab_history);

    }


}