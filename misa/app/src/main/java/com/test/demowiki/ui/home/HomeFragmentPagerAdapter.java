package com.test.demowiki.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.test.demowiki.ui.explore.ArticleFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String title[] = new String[] {"Home", "My List", "History"};
    private Toolbar toolbar;

    public HomeFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int page) {
        switch (page){
            case 0:
                return new ArticleFragment();
            case 1:
                return new TestFragment();
            case 2:
                return new TestFragment();
        }
        return new Fragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int page) {
        return title[page];
    }
}
