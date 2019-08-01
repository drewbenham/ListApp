package com.drew_benham.listapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.adapters.ExpandableDetailAdapter;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailedMedia extends AppCompatActivity {
    private Media mediaDetail;
    private DetailViewHolder detailViewHolder;
    private ExpandableDetailAdapter expandableDetailAdapter;

    private List<String> listHeaderData;
    private HashMap<String, List<String>> hashMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_layout);

        Intent parentIntent = getIntent();
        mediaDetail = (Media) parentIntent.getSerializableExtra(TopLevelMediaList.DETAILS_ITEM);

        initLists();

        setupLayout();
    }

    private void initLists() {
        if (mediaDetail instanceof MusicMedia) {
            MusicMedia recordMedia = (MusicMedia) mediaDetail;

            hashMap = recordMedia.getSonglist();
            listHeaderData = new ArrayList<>();
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                listHeaderData.add(entry.getKey().toString());
            }
        }

    }

    private void setupLayout() {
        detailViewHolder = new DetailViewHolder();

        expandableDetailAdapter = new ExpandableDetailAdapter(this, listHeaderData, hashMap);
        detailViewHolder.expListView.setAdapter(expandableDetailAdapter);

        detailViewHolder.thumbnail.setImageResource(mediaDetail.getImageSrc());
        detailViewHolder.titleDetail.setText(mediaDetail.getTitle());
        detailViewHolder.subTitleDetail.setText(mediaDetail.getSubTitle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private class DetailViewHolder {
        ImageView thumbnail;
        TextView titleDetail;
        TextView subTitleDetail;
        ExpandableListView expListView;

        public DetailViewHolder() {
            thumbnail = findViewById(R.id.thumbNailDetail);
            titleDetail = findViewById(R.id.titleDetail);
            subTitleDetail = findViewById(R.id.subTitleDetail);
            expListView = findViewById(R.id.expandableList);
        }
    }
}
