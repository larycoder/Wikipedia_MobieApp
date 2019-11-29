package com.test.demowiki.ui.language_setting;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.test.demowiki.R;
import com.test.demowiki.ui.language_setting.help.ItemTouchHelperAdapter;
import com.test.demowiki.ui.language_setting.help.ItemTouchHelperViewHolder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ItemViewHolder>implements ItemTouchHelperAdapter {
    private static final String[] STRINGS = new String[]{
            "English","Tiếng Việt"
    };

    private final List<String> mItems = new ArrayList<>();

    public LanguageListAdapter() {
        mItems.addAll(Arrays.asList(STRINGS));
    }

    @Override
    public LanguageListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_language, parent, false);
        LanguageListAdapter.ItemViewHolder itemViewHolder = new LanguageListAdapter.ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final LanguageListAdapter.ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position));

    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        String prev = mItems.remove(fromPosition);
        mItems.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_language);

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
