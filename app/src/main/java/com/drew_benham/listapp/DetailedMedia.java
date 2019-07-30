package com.drew_benham.listapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.drew_benham.listapp.adapters.ExpandableDetailAdapter;
import com.drew_benham.listapp.models.Media;

public class DetailedMedia extends AppCompatActivity {
    private Media mediaDetail;
    private DetailViewHolder detailViewHolder;
    private ExpandableDetailAdapter expandableDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_layout);


        setupLayout();
    }

    private void setupLayout() {
        detailViewHolder = new DetailViewHolder();
        //TODO: Create lists for the adapter.
        //expandableDetailAdapter = new ExpandableDetailAdapter();
        detailViewHolder.expListView.setAdapter(expandableDetailAdapter);
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
