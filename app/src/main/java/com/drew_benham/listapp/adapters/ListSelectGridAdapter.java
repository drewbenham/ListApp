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
import com.drew_benham.listapp.models.MediaListType;

import java.util.List;

public class ListSelectGridAdapter extends RecyclerView.Adapter<ListSelectGridAdapter.ListSelectViewHolder> {
    private Context context;
    private List<MediaListType> mediaLists;

    public ListSelectGridAdapter(Context context, List<MediaListType> mediaLists) {
        this.context = context;
        this.mediaLists = mediaLists;
    }

    @NonNull
    @Override
    public ListSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_select_grid_item, parent, false);
        ListSelectViewHolder viewHolder = new ListSelectViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListSelectViewHolder holder, int position) {
        MediaListType mediaListType = mediaLists.get(position);
        holder.gridViewItem.setImageResource(mediaListType.getImageSrc());
        holder.listNameText.setText(mediaListType.getListName());

        holder.gridViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.listNameText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MediaListType getItem(int id) {
        return mediaLists.get(id);
    }

    @Override
    public int getItemCount() {
        return mediaLists.size();
    }

    public class ListSelectViewHolder extends RecyclerView.ViewHolder {
        ImageButton gridViewItem;
        TextView listNameText;

        public ListSelectViewHolder(View itemView) {
            super(itemView);
            gridViewItem = itemView.findViewById(R.id.gridViewImageItem);
            listNameText = itemView.findViewById(R.id.listName);
        }
    }
}
