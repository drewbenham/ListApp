package com.drew_benham.listapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.drew_benham.listapp.fragments.MusicFragment;
import com.drew_benham.listapp.models.Media;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    private List<Media> mediaList;

    public PageAdapter(FragmentManager fm, int numOfTabs, List<Media> mediaList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTabs = numOfTabs;
        this.mediaList = mediaList;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = MusicFragment.newInstance(mediaList);
                break;
            case 1:
                fragment = MusicFragment.newInstance(mediaList);
                break;
            case 2:
                fragment = MusicFragment.newInstance(mediaList);
                break;
            default:
                fragment = null;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
