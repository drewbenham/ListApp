package com.drew_benham.listapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.activities.DetailedMedia;
import com.drew_benham.listapp.activities.TabActivity;
import com.drew_benham.listapp.adapters.TopLevelMediaListAdapter;
import com.drew_benham.listapp.database.DaoAsyncProcessor;
import com.drew_benham.listapp.interfaces.OnDataChangedListener;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;
import com.drew_benham.listapp.view_models.MusicViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment implements TopLevelMediaListAdapter.OnMediaListener, OnDataChangedListener, AdapterView.OnItemSelectedListener {
    private ViewHolder viewHolder;
    private TopLevelMediaListAdapter topLevelMediaListAdapter;
    private MusicViewModel musicViewModel;
    private ArrayList<String> spinnerOptions;

    private List<Media> mediaList;

    public static final String DETAILS_ITEM = "detailsItem";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LIST_ARG = "mediaList";

    private OnFragmentInteractionListener mListener;

    public MusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicFragment newInstance(List<Media> mediaList) {
        return new MusicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.top_level_media_list, container, false);
        viewHolder = new ViewHolder(view);

        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topLevelMediaListAdapter = new TopLevelMediaListAdapter(this);
        viewHolder.recyclerView.setAdapter(topLevelMediaListAdapter);
        TabActivity.dataChangedListener = this;

        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);

        musicViewModel.getAllMusic().observe(this, new Observer<List<MusicMedia>>() {
            @Override
            public void onChanged(List<MusicMedia> musicMedia) {
                List<Media> mediaGeneric = new ArrayList<>();
                mediaGeneric.addAll(musicMedia);
                topLevelMediaListAdapter.setMediaList(mediaGeneric);
            }
        });

        spinnerOptions = new ArrayList<>();
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.topLevelSpinner.setAdapter(spinnerAdapter);

        musicViewModel.getAllMusicTypes().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                spinnerOptions.clear();
                spinnerOptions.addAll(strings);
                spinnerAdapter.notifyDataSetChanged();
            }
        });

        viewHolder.topLevelSpinner.setOnItemSelectedListener(this);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                musicViewModel.delete((MusicMedia) topLevelMediaListAdapter.getMediaAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(viewHolder.recyclerView);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mediaList = (List<Media>)getArguments().getSerializable(LIST_ARG);
            mediaList = addAlphabet(sortMediaFilter(mediaList));
        }
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
    public void onMediaClick(Media media) {
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                topLevelMediaListAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onDataChanged(final MusicMedia music) {
        musicViewModel.insert(music);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String mediaType = parent.getItemAtPosition(position).toString();
        topLevelMediaListAdapter.getFilter().filter(mediaType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
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
        private Spinner topLevelSpinner;

        public ViewHolder(View v) {
            recyclerView = v.findViewById(R.id.topLevelRecyclerView);
            topLevelSpinner = v.findViewById(R.id.topLevelSpinner);
        }
    }
}
