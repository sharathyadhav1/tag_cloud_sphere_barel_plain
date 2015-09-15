package com.winjit.myapp;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AmrutB on 29-05-2015.
 */
public class RVListAdapter  extends RecyclerView.Adapter<RVListAdapter.ViewHolder> {
    List<String> items;
    public RVListAdapter(List<String> items)
    {
        this.items=items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(items.get(i));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new ViewHolder(new TextView(viewGroup.getContext()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public ViewHolder(TextView view)
        {
            super(view);
            textView=view;
        }
    }
}
