package com.test.demowiki.ui.explore.trending_card;

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

import com.test.demowiki.R;
import com.test.demowiki.ui.article_detail.ScrollingActivity;

import java.util.ArrayList;


public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemHolder> {
    Context context;
    ArrayList<Item> itemList;

    public ItemListAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.header.setText(item.getHeader());
        holder.subHeader.setText(item.getSubHeader());
        holder.img.setImageResource(item.getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getHeader(),Toast.LENGTH_LONG).show();
                Intent startScroll = new Intent(context, ScrollingActivity.class);
                //Intent startScroll = new Intent(context, WebViewActivity.class);
                context.startActivity(startScroll);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView subHeader;
        ImageView img;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            header= itemView.findViewById(R.id.item_header);
            subHeader =itemView.findViewById(R.id.item_sub_header);
            img=itemView.findViewById(R.id.item_img);
        }
    }
}
