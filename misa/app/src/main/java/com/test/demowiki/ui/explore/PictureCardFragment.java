package com.test.demowiki.ui.explore;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.R;
import com.test.demowiki.ui.customize_feed.CustomizeActivity;
import com.test.demowiki.wikiAPI.*;

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
        return v;
    }
    private void hideOption(int id) {
        MenuItem item = this.popup.getMenu().findItem(id);
        item.setVisible(false);
    }
    public void updateImage(wikipediaAPI wikiAPI, RequestQueue queue){
        StringRequest imageOfDay

    }
    public ImageRequest VolleyRequest(String url){
        // create a listener for response
        Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                ImageView cardImage = getView().findViewById(R.id.picture_card_main);
                cardImage.setImageBitmap(bitmap);
            }
        };

        // create request
        return new ImageRequest(url, listener, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null);
    }
}
