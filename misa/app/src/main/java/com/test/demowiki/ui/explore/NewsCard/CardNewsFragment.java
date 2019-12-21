package com.test.demowiki.ui.explore.NewsCard;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.test.demowiki.R;
import com.test.demowiki.ui.customize_feed.CustomizeActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardNewsFragment extends Fragment {

    RecyclerView horizontalRV;
    HorizontalRVAdapter adapter;
    ArrayList<ArticleCardNews> articleList;
    public CardNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.card_in_the_news, container, false);
        horizontalRV = v.findViewById(R.id.list_article);
        horizontalRV.setHasFixedSize(true);
        horizontalRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        articleList= new ArrayList<>();
        adapter = new HorizontalRVAdapter(getContext(),articleList);
        horizontalRV.setAdapter(adapter);
        setData();
        ImageButton button = v.findViewById(R.id.option_button_news_article);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.hide_card:
                                // do your code
                                return true;
                            case R.id.edit_card_lang:
                                return true;
                            // do your code
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
                popup.show();
            }
        });
        return v;
    }

    private void setData() {
        for (int i=0;i<10;i++){
            ArticleCardNews article = new ArticleCardNews();
            article.setDescription(getString(R.string.article_summary));
            article.setImageResId(R.drawable.dinasour);
            articleList.add(article);
        }
        adapter.notifyDataSetChanged();
    }

}
