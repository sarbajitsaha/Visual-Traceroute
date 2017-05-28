package com.saha.arun.visualtraceroute.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saha.arun.visualtraceroute.R;

import java.util.List;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

/**
 * Created by Sarbajit Saha on 28/5/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemViewHolder> implements RVHAdapter {
    private final List<String> dataList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public HistoryAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.prev_search_list_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txt.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position, int direction) {
        remove(position);
    }

    private void remove(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements RVHViewHolder {
        final TextView txt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }

        @Override
        public void onItemSelected(int actionstate) {
            System.out.println("Item is selected");
        }

        @Override
        public void onItemClear() {
            System.out.println("Item is unselected");
        }
    }
}
