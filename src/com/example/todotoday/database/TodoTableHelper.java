package com.example.todotoday.database;

import static com.example.todotoday.database.DataBaseConsts.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoTableHelper extends SQLiteOpenHelper {
    private static final String CREATE_TODO = "create table "
    		+ TBL_TODO
    		+ " ("
            + COL_ID
            + " integer primary key autoincrement not null, "
            + COL_CONTENT
            + " text not null, "
            + COL_KIND
            + " integer not null, "
            + COL_PRIORITY
            + " integer not null, "
            + COL_TODAY
            + " integer not null, "
            + COL_DATE
            + " text not null)";

    private TodoTableHelper(Context context) {
        super(context, "todo_table", null, 1);
    }

    public static SQLiteDatabase initDataBase(Context context) {
        return (new TodoTableHelper(context)).getWritableDatabase();
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
