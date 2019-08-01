package com.drew_benham.listapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.adapters.ListSelectGridAdapter;
import com.drew_benham.listapp.models.MediaListType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewHolder viewHolder;
    private ListSelectGridAdapter listSelectGridAdapter;

    private List<MediaListType> mediaLists;

    public static final int NUM_COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSrcList();

        setupLayout();
    }

    private void setupLayout() {
        viewHolder = new ViewHolder();
        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMNS));
        listSelectGridAdapter = new ListSelectGridAdapter(this, mediaLists);
        // TODO: 7/12/2019 might need click listener interface
        viewHolder.recyclerView.setAdapter(listSelectGridAdapter);
    }

    private void initSrcList() {
        mediaLists = new ArrayList<>();
        MediaListType records = new MediaListType("Records", R.drawable.ic_launcher_background);

        mediaLists.add(records);
    }

    private class ViewHolder {
        RecyclerView recyclerView;

        public ViewHolder() {
            recyclerView = findViewById(R.id.selectListRecyclerGrid);
        }
    }
}
