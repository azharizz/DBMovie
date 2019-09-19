package com.example.mymoviecataloguenew.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_MOVIEID="movieid";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_USERRATING="userrating";
        public static final String COLUMN_POSTER_PATH="posterpath";
        public static final String COLUMN_PLOT_SYNOPSIS="favorite";

        public static final String AUTHORITY = "com.d3mstudio.catalogmovie";
        public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
        public static String getColumnString(Cursor cursor, String columnName) {
            return cursor.getString( cursor.getColumnIndex(columnName) );
        }
        public static int getColumnInt(Cursor cursor, String columnName) {
            return cursor.getInt( cursor.getColumnIndex(columnName) );
        }
        public static long getColumnLong(Cursor cursor, String columnName) {
            return cursor.getLong( cursor.getColumnIndex(columnName) );
        }

    }
}
