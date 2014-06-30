package com.example.todotoday.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoTableHelper extends SQLiteOpenHelper {
    private static final String CREATE_TODO = "create table todo ("
            + "_id integer primary key autoincrement not null, "
            + "content text not null, "
            + "kind integer not null, "
            + "priority integer not null, "
            + "today integer not null, "
            + "date text not null)";

    public TodoTableHelper(Context context) {
        super(context, "todo_table", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }

}
