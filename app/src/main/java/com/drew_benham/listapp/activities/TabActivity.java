package com.drew_benham.listapp.activities;

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

public class TabActivity extends AppCompatActivity {
    private TabViewHolder tabViewholder;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        setupLayout();
    }

    private void setupLayout() {
        tabViewholder = new TabViewHolder();
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabViewholder.tabLayout.getTabCount());
        tabViewholder.viewPager.setAdapter(pagerAdapter);
        tabViewholder.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabViewholder.tabLayout));
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
