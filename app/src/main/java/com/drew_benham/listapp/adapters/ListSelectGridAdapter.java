package com.drew_benham.listapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.drew_benham.listapp.R;

import java.util.List;

public class ListSelectGridAdapter extends RecyclerView.Adapter<ListSelectGridAdapter.ViewHolder> {
    private Context context;
    private List<Integer> listSrc;

    public ListSelectGridAdapter(Context context, List<Integer> listSrc) {
        this.context = context;
        this.listSrc = listSrc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_select_grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gridViewItem.setImageResource(listSrc.get(position));

        holder.gridViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public Integer getItem(int id) {
        return listSrc.get(id);
    }

    @Override
    public int getItemCount() {
        return listSrc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton gridViewItem;

        public ViewHolder(View itemView) {
            super(itemView);
            gridViewItem = itemView.findViewById(R.id.gridViewImageItem);
        }
    }
}
