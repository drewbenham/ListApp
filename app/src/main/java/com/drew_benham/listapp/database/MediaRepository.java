package com.drew_benham.listapp.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.drew_benham.listapp.models.MusicMedia;

import java.util.List;

public class MediaRepository {
    private final MediaRoomDatabase db;

    private MusicDao musicDao;
    private LiveData<List<MusicMedia>> allMusic;
    private LiveData<List<String>> allMusicTypes;

    public MediaRepository(Application application) {
        db = MediaRoomDatabase.getDatabase(application);
        this.musicDao = db.musicDao();
        this.allMusic = musicDao.getAllMusic();
        this.allMusicTypes = musicDao.getMusicTypes();
    }

    public LiveData<List<MusicMedia>> getAllMusic() {
        return allMusic;
    }

    public LiveData<MusicMedia> loadMusic(final int musicId) {
        return db.musicDao().loadMusic(musicId);
    }

    public LiveData<List<String>> getAllMusicTypes() { return allMusicTypes; }

    public void insert(final MusicMedia musicMedia) {
        new InsertAsyncTask(musicDao).execute(musicMedia);
    }

    public void update(final MusicMedia musicMedia) {
        new UpdateAsyncTask(musicDao).execute(musicMedia);
    }

    public void delete(final MusicMedia musicMedia) {
        new DeleteAsyncTask(musicDao).execute(musicMedia);
    }

    private static class InsertAsyncTask extends AsyncTask<MusicMedia, Void, Void> {
        private MusicDao asyncTaskDao;

        InsertAsyncTask(MusicDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MusicMedia... musicMedia) {
            asyncTaskDao.insert(musicMedia[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<MusicMedia, Void, Void> {
        private MusicDao asyncTaskDao;

        UpdateAsyncTask(MusicDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MusicMedia... musicMedia) {
            asyncTaskDao.update(musicMedia[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<MusicMedia, Void, Void> {
        private MusicDao asyncTaskDao;

        DeleteAsyncTask(MusicDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MusicMedia... musicMedia) {
            asyncTaskDao.delete(musicMedia[0]);
            return null;
        }
    }
}
