package com.drew_benham.listapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.drew_benham.listapp.R;

import java.util.List;

public class ListSelectGridAdapter extends RecyclerView.Adapter<ListSelectGridAdapter.ViewHolder> {
    private Context context;
    private List<Integer> listSrc;
    private List<String> listNames;

    public ListSelectGridAdapter(Context context, List<Integer> listSrc, List<String> listNames) {
        this.context = context;
        this.listSrc = listSrc;
        this.listNames = listNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_select_grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.gridViewItem.setImageResource(listSrc.get(position));
        holder.listNameText.setText(listNames.get(position));

        holder.gridViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.listNameText.getText(), Toast.LENGTH_SHORT).show();
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
        TextView listNameText;

        public ViewHolder(View itemView) {
            super(itemView);
            gridViewItem = itemView.findViewById(R.id.gridViewImageItem);
            listNameText = itemView.findViewById(R.id.listName);
        }
    }
}
