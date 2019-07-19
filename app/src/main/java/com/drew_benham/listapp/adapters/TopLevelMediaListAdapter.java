package com.drew_benham.listapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.Record;

import java.util.List;

public class TopLevelMediaListAdapter extends RecyclerView.Adapter<TopLevelMediaListAdapter.TopLevelViewHolder> {
    private Context context;
    private List<Media> mediaList;

    public TopLevelMediaListAdapter(Context context, List<Media> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @NonNull
    @Override
    public TopLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_list_item, parent,false);
        return new TopLevelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopLevelViewHolder holder, int position) {
        Media media = mediaList.get(position);

        holder.thumbNail.setImageResource(media.getImageSrc());
        holder.mediaTitle.setText(media.getTitle());
        holder.subMediaTitle.setText(media.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public Media getItem(int id) { return mediaList.get(id); }

    public class TopLevelViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbNail;
        TextView mediaTitle;
        TextView subMediaTitle;

        public TopLevelViewHolder(View itemView) {
            super(itemView);
            thumbNail = itemView.findViewById(R.id.thumbNail);
            mediaTitle = itemView.findViewById(R.id.mediaTitleText);
            subMediaTitle = itemView.findViewById(R.id.subMediaTitleText);
        }
    }
}
