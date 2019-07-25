package com.drew_benham.listapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.drew_benham.listapp.adapters.ListSelectGridAdapter;
import com.drew_benham.listapp.adapters.TopLevelMediaListAdapter;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TopLevelMediaList extends AppCompatActivity implements TopLevelMediaListAdapter.OnMediaListener {
    private ViewHolder viewHolder;
    private TopLevelMediaListAdapter topLevelMediaListAdapter;

    private List<Media> mediaList;
    private String listName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_level_media_list);
        initMediaList();

        setupLayout();

        Intent parentIntent = getIntent();
        listName = parentIntent.getStringExtra(ListSelectGridAdapter.LIST_NAME);

        Toast.makeText(this, listName, Toast.LENGTH_SHORT).show();
    }

    private void initMediaList() {
        mediaList = new ArrayList<>();

        Date date = new Date();
        List<String> songList = new ArrayList<>();
        Media mediaA = new Record("C Artist", "B Album", R.drawable.ic_launcher_background, date, songList);
        Media mediaB = new Record("B Artist", "A Album", R.drawable.ic_launcher_background, date, songList);

        mediaList.add(mediaA);
        mediaList.add(mediaB);


    }

    // TODO: 7/22/19 Change this to take in parameter for sorting. 
    private List<Media> sortMediaFilter(List<Media> list) {
        Collections.sort(list, new Comparator<Media>() {
            @Override
            public int compare(Media media1, Media media2) {
                return media1.getTitle().compareTo(media2.getTitle());
            }
        });

        return list;
    }

    private List<Media> addAlphabet(List<Media> list) {
        int i = 0;
        List<Media> customList = new ArrayList<>();
        Media letter = new Media(list.get(i).getTitle().charAt(0) + "");
        customList.add(letter);

        for (i = 0; i < list.size() - 1; i++) {
            char mediaFirst = list.get(i).getTitle().charAt(0);
            char mediaSecond = list.get(i + 1).getTitle().charAt(0);

            if (mediaFirst == mediaSecond) {
                list.get(i).setType(Media.TYPE_MEDIA);
                customList.add(list.get(i));
            }
            else {
                list.get(i).setType(Media.TYPE_MEDIA);
                customList.add(list.get(i));
                letter = new Media(mediaSecond + "");
                customList.add(letter);
            }
        }
        list.get(i).setType(Media.TYPE_MEDIA);
        customList.add(list.get(i));
        return customList;
    }

    private void setupLayout() {
        viewHolder = new ViewHolder();

        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topLevelMediaListAdapter = new TopLevelMediaListAdapter(this, addAlphabet(sortMediaFilter(mediaList)), this);
        viewHolder.recyclerView.setAdapter(topLevelMediaListAdapter);
    }

    @Override
    public void onMediaClick(int position) {
        Media media = mediaList.get(position);
        Intent detailsIntent = new Intent(this, DetailedMedia.class);

        if (media.getType() == Media.TYPE_MEDIA) {
            startActivity(detailsIntent);
        }
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
