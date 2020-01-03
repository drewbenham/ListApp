package com.drew_benham.listapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TopLevelMediaListAdapter extends RecyclerView.Adapter<TopLevelMediaListAdapter.TopLevelViewHolder> implements Filterable {
    private List<Media> mediaList;
    private List<Media> mediaListFull;

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

    public Media getMediaAt(int position){
        return mediaList.get(position);
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
        mediaListFull = new ArrayList<>(mediaList);
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

    @Override
    public Filter getFilter() {
        return mediaFilter;
    }

    private Filter mediaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Media> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(mediaListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                String type = "";
                for (Media mediaItem : mediaListFull) {
                    if (mediaItem instanceof MusicMedia) {
                        type = ((MusicMedia) mediaItem).getMusicMedium().toLowerCase().trim();
                    }
                    if (mediaItem.getTitle().toLowerCase().contains(filterPattern) ||
                            type.equals(filterPattern)) {
                        filteredList.add(mediaItem);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mediaList.clear();
            mediaList.addAll((List) filterResults.values);

            notifyDataSetChanged();
        }
    };

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
