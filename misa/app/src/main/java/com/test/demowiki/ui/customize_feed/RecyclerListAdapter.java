package com.test.demowiki.ui.customize_feed;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.demowiki.R;
import com.test.demowiki.ui.customize_feed.help.ItemTouchHelperAdapter;
import com.test.demowiki.ui.customize_feed.help.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {
    Context context;
    ArrayList<FeedSetting> feedTypeList;

    public RecyclerListAdapter(Context context, ArrayList<FeedSetting> feedTypeList) {
        this.context = context;
        this.feedTypeList = feedTypeList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_setting, parent, false);
        return new ItemViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        FeedSetting f = feedTypeList.get(position);
        String mfeedType = f.getFeedType();
        String mfeedDesc = f.getFeedTypeDesc();
        boolean status= f.isStatus();
        holder.feedType.setText(mfeedType);
        holder.feedTypeDesc.setText(mfeedDesc);
        holder.feedSwitch.setChecked(status);
    }

    @Override
    public int getItemCount() {
        return feedTypeList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        FeedSetting prev = feedTypeList.remove(fromPosition);
        feedTypeList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        feedTypeList.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        TextView feedType;
        TextView feedTypeDesc;
        Switch feedSwitch;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            feedType=itemView.findViewById(R.id.feed_type);
            feedTypeDesc=itemView.findViewById(R.id.feed_type_desc);
            feedSwitch=itemView.findViewById(R.id.feed_switch);
        }

        @Override
        public void onItemSelected() {
//            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
//            itemView.setBackgroundColor(Color.BLUE);
        }
    }
    }