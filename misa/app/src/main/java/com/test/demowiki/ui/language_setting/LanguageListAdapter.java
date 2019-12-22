package com.test.demowiki.ui.language_setting;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.demowiki.R;
import com.test.demowiki.ui.language_setting.help.ItemTouchHelperAdapter;
import com.test.demowiki.ui.language_setting.help.ItemTouchHelperViewHolder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ItemViewHolder>implements ItemTouchHelperAdapter {
//    private static final String[] STRINGS = new String[]{
//            "English","Tiếng Việt"
//    };
//
//    private final List<String> mItems = new ArrayList<>();
//
//    public LanguageListAdapter() {
//        mItems.addAll(Arrays.asList(STRINGS));
//    }
//
//    @Override
//    public LanguageListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_language, parent, false);
//        LanguageListAdapter.ItemViewHolder itemViewHolder = new LanguageListAdapter.ItemViewHolder(view);
//        return itemViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final LanguageListAdapter.ItemViewHolder holder, int position) {
//        holder.textView.setText(mItems.get(position));
//
//    }
//
//    @Override
//    public void onItemDismiss(int position) {
//        mItems.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    @Override
//    public void onItemMove(int fromPosition, int toPosition) {
//        String prev = mItems.remove(fromPosition);
//        mItems.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
//        notifyItemMoved(fromPosition, toPosition);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItems.size();
//    }
//
//    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
//            ItemTouchHelperViewHolder {
//
//        public final TextView textView;
//
//
//        public ItemViewHolder(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.text_language);
//
//        }
//
//        @Override
//        public void onItemSelected() {
//            itemView.setBackgroundColor(Color.LTGRAY);
//        }
//
//        @Override
//        public void onItemClear() {
//            itemView.setBackgroundColor(0);
//        }
//    }
//}
public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ItemViewHolder>implements ItemTouchHelperAdapter{
    Context context;
    ArrayList<Language> languageList;

    public LanguageListAdapter(Context context, ArrayList<Language> languageList) {
        this.context = context;
        this.languageList = languageList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.language_setting,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Language f = languageList.get(position);
        String lang_name = f.getLanguage();
        int lang_num=f.getLanguageNum();
        holder.language_name.setText(lang_name);
        holder.language_num.setText(String.valueOf(lang_num));
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Language prev = languageList.get(fromPosition);
        languageList.remove(fromPosition);
        languageList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        languageList.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder{
        TextView language_name;
        TextView language_num;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            language_name = itemView.findViewById(R.id.language_name);
            language_num=itemView.findViewById(R.id.language_num);
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }
}