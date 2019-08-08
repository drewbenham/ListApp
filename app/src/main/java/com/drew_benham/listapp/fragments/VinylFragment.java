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
import android.widget.TextView;
import android.widget.Toast;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.activities.DetailedMedia;
import com.drew_benham.listapp.activities.TopLevelMediaList;
import com.drew_benham.listapp.adapters.ListSelectGridAdapter;
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
public class VinylFragment extends Fragment {
    private TopLevelMediaList.ViewHolder viewHolder;
    private TopLevelMediaListAdapter topLevelMediaListAdapter;

    private List<Media> mediaList;
    private String listName;

    public static final String DETAILS_ITEM = "detailsItem";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "context";
    private static final String ARG_PARAM2 = "mediaList";

    private OnFragmentInteractionListener mListener;

    public VinylFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VinylFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VinylFragment newInstance(String param1, String param2) {
        VinylFragment fragment = new VinylFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

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

    private void setupLayout() {
        viewHolder = new TopLevelMediaList.ViewHolder();

        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mediaList = addAlphabet(sortMediaFilter(mediaList));
        topLevelMediaListAdapter = new TopLevelMediaListAdapter(this, mediaList, this);
        viewHolder.recyclerView.setAdapter(topLevelMediaListAdapter);

        viewHolder.addEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: need to add edit activity. pictures and stuff.
            }
        });
    }

    @Override
    public void onMediaClick(int position) {
        Media media = mediaList.get(position);
        Intent detailsIntent = new Intent(this, DetailedMedia.class);
        detailsIntent.putExtra(DETAILS_ITEM, media);
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
        private ImageButton addEdit;
        private RecyclerView recyclerView;

        public ViewHolder() {
            addEdit = findViewById(R.id.addEditItem);
            recyclerView = findViewById(R.id.topLevelRecyclerView);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
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
}
