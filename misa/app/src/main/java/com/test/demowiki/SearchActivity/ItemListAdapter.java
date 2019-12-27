package com.test.demowiki.SearchActivity;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.test.demowiki.R;
import com.test.demowiki.VolleySingleton;
import com.test.demowiki.ui.article_detail.ScrollingActivity;
import com.test.demowiki.SearchActivity.ItemSearch;
import com.test.demowiki.wikiAPI.wikipediaAPI;

import java.util.ArrayList;


public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemHolder> {
	Context context;
	ArrayList<ItemSearch> itemList;
	wikipediaAPI api;

	public ItemListAdapter(Context context, ArrayList<ItemSearch> itemList) {
		this.context = context;
		this.itemList = itemList;
		api = new wikipediaAPI();
	}

	@NonNull
	@Override
	public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);
		return new ItemHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
		final ItemSearch item = itemList.get(position);
		holder.header.setText(item.getHeader());
		holder.subHeader.setText(item.getSubHeader());
		holder.img.setImageBitmap(item.getImg());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				VolleySingleton.getQueue().add(new StringRequest(Request.Method.GET, api.getOriginalImageArticleUrl(item.getHeader()), new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Intent startScroll = new Intent(context, ScrollingActivity.class);
						startScroll.putExtra("articleImageUrl", api.getOriginalImageUrl(response).get(0));
						startScroll.putExtra("articleDescriptionUrl", api.getOriginalImageUrl(response).get(1));
						startScroll.putExtra("INTERNET", "YES");
						context.startActivity(startScroll);
					}
				}, null));

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
	public void removeItem(int position){
		itemList.remove(position);
		notifyItemRemoved(position);
	}
	public void restoreItem(ItemSearch item,int position){
		itemList.add(position,item);
		notifyItemInserted(position);
	}
	public ArrayList<ItemSearch> getData(){
		return itemList;
	}
}
