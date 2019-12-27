package com.test.demowiki.ui.explore;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.R;
import com.test.demowiki.VolleySingleton;
import com.test.demowiki.ui.article_detail.ScrollingActivity;
import com.test.demowiki.wikiAPI.wikipediaAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {


    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        updateInfo();
        Button showArticle = view.findViewById(R.id.article_option);

        showArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.hide_article:
                                // do your code
                                return true;
                            case R.id.hide_article_type:
                                return true;
                            // do your code
                            case R.id.cancel_article:
                                // do your code
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.article_menu);
                popup.show();
            }
        });
        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(getActivity().getApplicationContext(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.hide_article:
                // do your code
                return true;
            case R.id.hide_article_type:
                return true;
            // do your code
            case R.id.cancel_article:
                // do your code
                return true;
            default:
                return false;
        }
    }

    public void updateInfo(){
        final wikipediaAPI api = new wikipediaAPI();
        VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, api.getFeatureFeedUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                final List<String> featureFeed = api.parseFeatureFeed(response);
                ((TextView) getView().findViewById(R.id.article_featuredfeed_title)).setText(featureFeed.get(0));
                ((TextView) getView().findViewById(R.id.article_featuredfeed_description)).setText(featureFeed.get(1));

                CardView card = getView().findViewById(R.id.featured_card);
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startScroll = new Intent(getContext(), ScrollingActivity.class);
                        startScroll.putExtra("featuredFeedTitle", featureFeed.get(0));
                        startScroll.putExtra("featuredFeedDescription", featureFeed.get(1));
                        startScroll.putExtra("INTERNET", "NO");
                        startActivity(startScroll);
                    }
                });
            }
        }, null));
    }
}
