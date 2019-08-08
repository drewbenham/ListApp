package com.drew_benham.listapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.adapters.PageAdapter;
import com.drew_benham.listapp.fragments.VinylFragment;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TabActivity extends AppCompatActivity implements VinylFragment.OnFragmentInteractionListener {
    private TabViewHolder tabViewholder;
    private PagerAdapter pagerAdapter;
    private List<Media> mediaList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        initMediaList();
        setupLayout();
    }

    private void initMediaList() {
        mediaList = new ArrayList<>();

        Date date = new Date();
        List<String> songList = new ArrayList<>();
        songList.add("song 1");
        songList.add("song 2");

        HashMap<String, List<String>> testHash = new HashMap<>();
        testHash.put("Side A", songList);
        testHash.put("Side B", songList);

        Media mediaA = new MusicMedia("C Artist", "B Album", R.drawable.ic_launcher_background, date, testHash);
        Media mediaB = new MusicMedia("B Artist", "A Album", R.drawable.ic_launcher_background, date, testHash);

        mediaList.add(mediaA);
        mediaList.add(mediaB);
    }

    private void setupLayout() {
        tabViewholder = new TabViewHolder();
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabViewholder.tabLayout.getTabCount(), mediaList);
        tabViewholder.viewPager.setAdapter(pagerAdapter);
        tabViewholder.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabViewholder.tabLayout));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //blank on purpose. unless i can find a use for it.
    }

    private class TabViewHolder {
        TabLayout tabLayout;
        ImageButton addItem;
        ViewPager viewPager;
        TabItem vinylTab;
        TabItem cdTab;
        TabItem cassetteTab;

        public TabViewHolder() {
            tabLayout = findViewById(R.id.tabLayout);
            addItem = findViewById(R.id.addEditItem);
            viewPager = findViewById(R.id.viewPager);
            vinylTab = findViewById(R.id.vinylTabItem);
            cdTab = findViewById(R.id.cdTabItem);
            cassetteTab = findViewById(R.id.cassetteTabItem);
        }
    }
}
