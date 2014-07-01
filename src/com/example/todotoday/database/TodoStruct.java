package com.example.todotoday.database;

import static com.example.todotoday.database.DataBaseConsts.*;
import android.database.Cursor;

public class TodoStruct {
    private String _content;
    private int _kind;
    private int _priority;
    private int _today;
    
    public TodoStruct(Cursor cursor) {
        _content = cursor.getString(cursor.getColumnIndex(COL_CONTENT));
        _kind = cursor.getInt(cursor.getColumnIndex(COL_KIND));
        _priority = cursor.getInt(cursor.getColumnIndex(COL_PRIORITY));
        _today = cursor.getInt(cursor.getColumnIndex(COL_TODAY));
    }

    public String getContent() {
        return _content;
    }

    public int getKind() {
        return _kind;
    }

    public int getPriority() {
        return _priority;
    }

    public int getToday() {
        return _today;
    }
}
