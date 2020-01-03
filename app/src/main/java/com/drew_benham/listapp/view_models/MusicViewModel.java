package com.drew_benham.listapp.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.drew_benham.listapp.database.MediaRepository;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.List;

public class MusicViewModel extends AndroidViewModel {
    private MediaRepository repository;
    private LiveData<List<MusicMedia>> allMusic;
    private LiveData<List<String>> allMusicTypes;
    private LiveData<MusicMedia> singleMusic;

    public MusicViewModel(@NonNull Application application) {
        super(application);
        repository = new MediaRepository(application);
        allMusic = repository.getAllMusic();
        allMusicTypes = repository.getAllMusicTypes();
    }

    public LiveData<List<MusicMedia>> getAllMusic() {
        return allMusic;
    }

    public LiveData<MusicMedia> getSingleMusic(int id) {
        return repository.loadMusic(id);
    }

    public LiveData<List<String>> getAllMusicTypes() { return allMusicTypes; }

    public void insert(MusicMedia musicMedia) {
        repository.insert(musicMedia);
    }

    public void update(MusicMedia musicMedia) {
        repository.update(musicMedia);
    }

    public void delete(MusicMedia musicMedia) {
        repository.delete(musicMedia);
    }
}
