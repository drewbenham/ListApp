package com.drew_benham.listapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.List;

public class TopLevelMediaListAdapter extends RecyclerView.Adapter<TopLevelMediaListAdapter.TopLevelViewHolder> {
    private List<Media> mediaList;

    private OnMediaListener onMediaListener;

    public TopLevelMediaListAdapter(OnMediaListener onMediaListener) {
        this.onMediaListener = onMediaListener;
    }

    @NonNull
    @Override
    public TopLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case Media.TYPE_MEDIA:
                View viewMedia = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_list_item, parent, false);
                TopLevelViewHolder viewHolder = new TopLevelViewHolder(viewMedia, onMediaListener);
                viewMedia.setOnClickListener(viewHolder);
                return viewHolder;

            case Media.TYPE_LETTER:
                View viewLetter = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_list_item, parent, false);
                return new TopLevelViewHolder(viewLetter, onMediaListener);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TopLevelViewHolder holder, int position) {
        Media media = mediaList.get(position);

        if (media.getType() == Media.TYPE_LETTER) {
            holder.letterText.setText(media.getTitle());
        }
        else {
            holder.thumbNail.setImageResource(media.getImageSrc());
            holder.mediaTitle.setText(media.getTitle());
            holder.subMediaTitle.setText(media.getSubTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (mediaList != null) {
            return mediaList.size();
        }
        else return 0;
    }

    public Media getItem(int id) { return mediaList.get(id); }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (mediaList.get(position).getType() == Media.TYPE_MEDIA) {
            viewType = Media.TYPE_MEDIA;
        }
        else {
            viewType = Media.TYPE_LETTER;
        }
        return viewType;
    }

    public class TopLevelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView thumbNail;
        TextView mediaTitle;
        TextView subMediaTitle;
        TextView letterText;
        OnMediaListener mediaListener;

        public TopLevelViewHolder(View itemView, OnMediaListener mediaListener) {
            super(itemView);
            this.mediaListener = mediaListener;
            thumbNail = itemView.findViewById(R.id.thumbNail);
            mediaTitle = itemView.findViewById(R.id.mediaTitleText);
            subMediaTitle = itemView.findViewById(R.id.subMediaTitleText);
            letterText = itemView.findViewById(R.id.letterText);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (mediaListener != null && (position != RecyclerView.NO_POSITION)) {
                mediaListener.onMediaClick(mediaList.get(position));
            }
        }
    }

    public interface OnMediaListener {
        void onMediaClick(Media musicMedia);
    }
}
