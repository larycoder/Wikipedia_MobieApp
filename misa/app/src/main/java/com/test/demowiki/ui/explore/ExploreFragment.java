package com.test.demowiki.ui.explore;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.demowiki.R;
import com.test.demowiki.ui.explore.NewsCard.CardNewsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {


    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        LinearLayout ll = view.findViewById(R.id.ArticleFragment);
        for(int i = 0; i < 4; i++) {
            // add fragment for article
            getFragmentManager().beginTransaction().add(R.id.ArticleFragment, new ArticleFragment()).commit();
            getFragmentManager().beginTransaction().add(R.id.ArticleFragment, new CardNewsFragment()).commit();

        }

        return view;
    }

}
