package com.google.davidsuzukinaturechallenge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jenna on 15-11-15.
 */
public class UserDatabase extends SQLiteOpenHelper {

    public UserDatabase(Context context) {
        super(context, "profile.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
