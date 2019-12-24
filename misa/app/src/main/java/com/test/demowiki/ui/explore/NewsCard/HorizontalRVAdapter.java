package com.test.demowiki.ui.explore.NewsCard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.demowiki.MainActivity;
import com.test.demowiki.R;
import com.test.demowiki.ui.article_detail.ScrollingActivity;

import java.util.ArrayList;

public class HorizontalRVAdapter extends RecyclerView.Adapter<HorizontalRVAdapter.HorizontalRVHolder> {
    Context context;
    ArrayList<ArticleCardNews> articleList;

    public HorizontalRVAdapter(Context context, ArrayList<ArticleCardNews> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public HorizontalRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_article,parent,false);
        return new HorizontalRVHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRVHolder holder, int position) {
        final ArticleCardNews article = articleList.get(position);
        holder.articleDescription.setText(article.getDescription());
        holder.articleImage.setImageResource(article.getImageResId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, article.getDescription(),Toast.LENGTH_LONG).show();
                Intent startScroll = new Intent(context, ScrollingActivity.class);
                context.startActivity(startScroll);

            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class HorizontalRVHolder extends RecyclerView.ViewHolder {

        TextView articleDescription;
        ImageView articleImage;
        public HorizontalRVHolder(@NonNull View itemView) {
            super(itemView);
            articleDescription=itemView.findViewById(R.id.article_description);
            articleImage=itemView.findViewById(R.id.article_image);
        }
    }
}
