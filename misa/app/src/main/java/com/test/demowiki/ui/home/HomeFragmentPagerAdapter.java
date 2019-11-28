package com.test.demowiki.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.test.demowiki.MainActivity;
import com.test.demowiki.ui.explore.ArticleFragment;

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private final int PAGE_COUNT = 3;
    private String title[] = new String[] {"Home", "My List", "History"};
    private MainActivity main;

    public HomeFragmentPagerAdapter(FragmentManager fm, MainActivity main)
    {
        super(fm);
        this.main = main;
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

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        Fragment frag = (Fragment) object;
//        if (frag instanceof ArticleFragment)
//            main.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        else if (frag instanceof TestFragment)
//            main.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        return super.getItemPosition(object);
//    }
}
