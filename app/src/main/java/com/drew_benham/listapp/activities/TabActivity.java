package com.drew_benham.listapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.fragments.MusicFragment;
import com.drew_benham.listapp.interfaces.OnDataChangedListener;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TabActivity extends AppCompatActivity implements MusicFragment.OnFragmentInteractionListener {
    public static final int NEW_ITEM_REQUEST_CODE = 1;
    public static final String ADD = "add";
    public static final String EDIT = "edit";

    private TabViewHolder tabViewholder;
    private List<Media> mediaList;

    public static OnDataChangedListener dataChangedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        setupLayout();
    }

    private void setupLayout() {
        tabViewholder = new TabViewHolder();

        if(tabViewholder.frameLayout != null) {
            //TODO: add a runtime conditional for movies. based on (preferences or db?).
            MusicFragment musicFragment = MusicFragment.newInstance(mediaList);

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, musicFragment).commit();
        }

        tabViewholder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEditItemIntent = new Intent(TabActivity.this, AddEditActivity.class);
                addEditItemIntent.putExtra(ADD, true);
                startActivityForResult(addEditItemIntent, NEW_ITEM_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                MusicMedia musicMedia = (MusicMedia) data.getSerializableExtra(AddEditActivity.RESULT_MEDIA);
                dataChangedListener.onDataChanged(musicMedia);
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //blank on purpose. unless i can find a use for it.
    }

    private class TabViewHolder {
        FrameLayout frameLayout;
        FloatingActionButton floatingActionButton;

        public TabViewHolder() {
            frameLayout = findViewById(R.id.fragment_container);
            floatingActionButton = findViewById(R.id.floatingAddBtn);
        }
    }
}
