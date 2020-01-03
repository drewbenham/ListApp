package com.drew_benham.listapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Database(entities = {MusicMedia.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class MediaRoomDatabase extends RoomDatabase {
    public abstract MusicDao musicDao();

    private static volatile MediaRoomDatabase INSTANCE;

    static MediaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MediaRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MediaRoomDatabase.class, "media_database").fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback =
    new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final MusicDao dao;

        PopulateDbAsync(MediaRoomDatabase db) {
            dao = db.musicDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            Date date = new Date();
//            List<String> songList = new ArrayList<>();
//            songList.add("song 1");
//            songList.add("song 2");
//
//            HashMap<String, List<String>> testHash = new HashMap<>();
//            testHash.put("Side A", songList);
//            testHash.put("Side B", songList);
//
//            MusicMedia mediaA = new MusicMedia("C Artist", "B Album", R.drawable.ic_launcher_background, date, testHash);
//            MusicMedia mediaB = new MusicMedia("B Artist", "A Album", R.drawable.ic_launcher_background, date, testHash);
//
//            dao.insert(mediaA);
//            dao.insert(mediaB);
            return null;
        }
    }
}
