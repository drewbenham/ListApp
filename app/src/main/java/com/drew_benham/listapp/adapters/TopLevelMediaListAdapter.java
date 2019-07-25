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

import java.util.List;

public class TopLevelMediaListAdapter extends RecyclerView.Adapter<TopLevelMediaListAdapter.TopLevelViewHolder> {
    private Context context;
    private List<Media> mediaList;

    private OnMediaListener onMediaListener;

    public TopLevelMediaListAdapter(Context context, List<Media> mediaList, OnMediaListener onMediaListener) {
        this.context = context;
        this.mediaList = mediaList;
        this.onMediaListener = onMediaListener;
    }

    @NonNull
    @Override
    public TopLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case Media.TYPE_MEDIA:
                View viewMedia = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_list_item, parent, false);
                return new TopLevelViewHolder(viewMedia, onMediaListener);

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
        return mediaList.size();
    }

    public Media getItem(int id) { return mediaList.get(id); }

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
            mediaListener.onMediaClick(getAdapterPosition());
        }
    }

    public interface OnMediaListener {
        void onMediaClick(int position);
    }
}
