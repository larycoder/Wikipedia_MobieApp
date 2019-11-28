package com.test.demowiki.ui.home;

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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), (MainActivity) getActivity());

        ViewPager pager = view.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(adapter);

        TabLayout tab = view.findViewById(R.id.tab);
        tab.setupWithViewPager(pager);

        final MainActivity main = (MainActivity) getActivity();

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        main.getSupportActionBar().setTitle("test 1");
                        main.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        break;
                    case 1:
                        main.getSupportActionBar().setTitle("test 2");
                        main.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        break;
                    case 2:
                        main.getSupportActionBar().setTitle("test 3");
                        main.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });



        return view;
    }


}