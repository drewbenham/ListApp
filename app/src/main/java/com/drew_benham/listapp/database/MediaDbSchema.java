package com.drew_benham.listapp.database;

public class MediaDbSchema {
    private MediaDbSchema() {}

    public static final String MUSIC_TABLE_NAME = "musicTable";

    public static final class MusicTable {
        //column names
        public static final class Cols {
            static final String ID = "id";
            static final String TITLE = "title";
            static final String SUBTITLE = "subTitle";
            static final String IMAGE_SRC = "imageSrc";
            static final String RELEASE_DATE = "releaseDate";
            static final String TYPE = "type";
        }

        public static String getCreateTableSql(String tableName) {
            return "CREATE TABLE " + tableName + " (" +
                    Cols.ID + " TEXT NOT NULL, " +
                    Cols.TITLE + " TEXT NOT NULL, " +
                    Cols.SUBTITLE + " TEXT NOT NULL, " +
                    Cols.IMAGE_SRC + " NUMERIC NOT NULL, " +
                    Cols.RELEASE_DATE + " TEXT NOT NULL, " +
                    Cols.TYPE + " NUMERIC NOT NULL" +
                    ");";
        }

        public static DbObjectConverter<>
    }
}
