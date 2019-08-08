package com.drew_benham.listapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.activities.DetailedMedia;
import com.drew_benham.listapp.adapters.TopLevelMediaListAdapter;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VinylFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VinylFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VinylFragment extends Fragment implements TopLevelMediaListAdapter.OnMediaListener {
    private  ViewHolder viewHolder;
    private TopLevelMediaListAdapter topLevelMediaListAdapter;

    private List<Media> mediaList;

    public static final String DETAILS_ITEM = "detailsItem";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LIST_ARG = "mediaList";

    private OnFragmentInteractionListener mListener;

    public VinylFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VinylFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VinylFragment newInstance(List<Media> mediaList) {
        VinylFragment fragment = new VinylFragment();
        Bundle args = new Bundle();

        ArrayList<Media> mediaArrayList = new ArrayList<>(mediaList.size());
        mediaArrayList.addAll(mediaList);

        args.putSerializable(LIST_ARG, mediaArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_level_media_list, container, false);
        viewHolder = new ViewHolder(view);

        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mediaList = addAlphabet(sortMediaFilter(mediaList));
        topLevelMediaListAdapter = new TopLevelMediaListAdapter(getContext(), mediaList, this);
        viewHolder.recyclerView.setAdapter(topLevelMediaListAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mediaList = (List<Media>)getArguments().getSerializable(LIST_ARG);
        }
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

    @Override
    public void onMediaClick(int position) {
        Media media = mediaList.get(position);
        Intent detailsIntent = new Intent(getContext(), DetailedMedia.class);
        detailsIntent.putExtra(DETAILS_ITEM, media);
        if (media.getType() == Media.TYPE_MEDIA) {
            startActivity(detailsIntent);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class ViewHolder {
        private RecyclerView recyclerView;

        public ViewHolder(View v) {
            recyclerView = v.findViewById(R.id.topLevelRecyclerView);
        }
    }
}
