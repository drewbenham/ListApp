package com.drew_benham.listapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.drew_benham.listapp.adapters.TopLevelMediaListAdapter;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopLevelMediaList extends AppCompatActivity {
    private ViewHolder viewHolder;
    private TopLevelMediaListAdapter topLevelMediaListAdapter;

    private List<Media> mediaList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_level_media_list);
        initMediaList();

        setupLayout();
    }

    private void initMediaList() {
        Date date = new Date();
        List<String> songList = new ArrayList<>();
        Media media = new Record("Example Artist", "Example Album", R.drawable.ic_launcher_background, date, songList);

        for (int i = 0; i < 10; i++) {
            mediaList.add(media);
        }
    }

    private void setupLayout() {
        viewHolder = new ViewHolder();

        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topLevelMediaListAdapter = new TopLevelMediaListAdapter(this, mediaList);
        viewHolder.recyclerView.setAdapter(topLevelMediaListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class ViewHolder {
        private RecyclerView recyclerView;

        public ViewHolder() {
            recyclerView = findViewById(R.id.topLevelRecyclerView);
        }
    }
}
