package com.test.demowiki.ui.explore;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.R;
import com.test.demowiki.VolleySingleton;
import com.test.demowiki.ui.article_detail.ScrollingActivity;
import com.test.demowiki.ui.customize_feed.CustomizeActivity;
import com.test.demowiki.wikiAPI.*;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureCardFragment extends Fragment {
    PopupMenu popup;

    public PictureCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_picture_card, container, false);
        ImageButton button = v.findViewById(R.id.option_btn_picture_article);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup = new PopupMenu(getActivity(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.hide_card:
                                // do your code
                                return true;
                            case R.id.customize_feed:
                                // do your code
                                Intent startCustomize = new Intent(getContext(), CustomizeActivity.class);
                                startActivity(startCustomize);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.news_card_option_menu);
                hideOption(R.id.edit_card_lang);
                popup.show();
            }
        });
        updatePOD();
        return v;
    }
    private void hideOption(int id) {
        MenuItem item = this.popup.getMenu().findItem(id);
        item.setVisible(false);
    }
    public void updatePOD(){
        final wikipediaAPI wikiAPI = new wikipediaAPI();
        VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, wikiAPI.getImageOfDayPageTitleUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String imagePageRequest = wikiAPI.getImageOfDayPageURL(response);
                VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, imagePageRequest, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final List<String> PODInfo = wikiAPI.getImageOfDayInfoUrl(response);
                        ((TextView) getView().findViewById(R.id.descriptionshort_pod)).setText(PODInfo.get(3));
                        VolleySingleton.getQueue().add(new ImageRequest(PODInfo.get(0), new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                ((ImageView) getView().findViewById(R.id.picture_card_main)).setImageBitmap(response);
                                // add article detail for article
                                CardView card = getView().findViewById(R.id.picture_card);
                                card.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent startScroll = new Intent(getActivity().getApplicationContext(), ScrollingActivity.class);
                                        startScroll.putExtra("articleImageUrl", PODInfo.get(0));
                                        startScroll.putExtra("articleDescriptionUrl", PODInfo.get(2));
                                        startScroll.putExtra("INTERNET", "YES");
                                        startActivity(startScroll);
                                    }
                                });
                            }
                        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null));
                    }
                }, null));
            }
        }, null));
    }
}
