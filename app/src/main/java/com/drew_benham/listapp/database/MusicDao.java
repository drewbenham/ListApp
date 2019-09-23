package com.drew_benham.listapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.drew_benham.listapp.models.MusicMedia;

import java.util.List;

@Dao
public interface MusicDao {
    @Insert
    void insert(MusicMedia musicMedia);

    @Update
    void update(MusicMedia musicMedia);

    @Delete
    void delete(MusicMedia musicMedia);

    @Query("DELETE FROM media_table")
    void deleteAll();

    @Query("SELECT * from media_table")
    LiveData<List<MusicMedia>> getAllMusic();

    @Query("SELECT * from media_table WHERE id = :id")
    LiveData<MusicMedia> loadMusic(int id);
}
