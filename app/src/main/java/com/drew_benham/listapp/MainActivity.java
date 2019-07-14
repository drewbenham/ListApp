package com.drew_benham.listapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.drew_benham.listapp.adapters.ListSelectGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewHolder viewHolder;
    private List<Integer> listSrc;
    private ListSelectGridAdapter listSelectGridAdapter;

    public static final int NUM_COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLayout();
    }

    private void setupLayout() {
        initSrcList();

        viewHolder = new ViewHolder();
        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMNS));
        listSelectGridAdapter = new ListSelectGridAdapter(this, listSrc);
        // TODO: 7/12/2019 might need click listener interface
        viewHolder.recyclerView.setAdapter(listSelectGridAdapter);
    }

    private void initSrcList() {
        listSrc = new ArrayList<>();
        listSrc.add(R.drawable.ic_launcher_background);
        listSrc.add(R.drawable.ic_launcher_background);
        listSrc.add(R.drawable.ic_launcher_background);
    }

    private class ViewHolder {
        RecyclerView recyclerView;

        public ViewHolder() {
            recyclerView = findViewById(R.id.selectListRecyclerGrid);
        }
    }
}
