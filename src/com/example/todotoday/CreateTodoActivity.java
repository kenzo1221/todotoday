package com.example.todotoday;

import static com.example.todotoday.database.DataBaseConsts.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.todotoday.database.TodoTableHelper;

public class CreateTodoActivity extends ActionBarActivity {
    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);
        initDBHelper();
        createComponents();
        setButtonProcedure();
    }

    private void initDBHelper() {
        TodoTableHelper helper = new TodoTableHelper(this);
        _db = helper.getWritableDatabase();
    }
    
    private void createComponents() {
        createSpinner(R.id.kind, R.array.kind_values);
        createSpinner(R.id.priority, R.array.priority_values);
        createSpinner(R.id.today, R.array.today_values);
    }

    private void createSpinner(int spnId, int optId) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] options = getResources().getStringArray(optId);
        for (String option : options) {
            adapter.add(option);
        }
        Spinner spinner = (Spinner) findViewById(spnId);
        spinner.setAdapter(adapter);
    }
    
    private void setButtonProcedure() {
        Button newBtn = (Button) findViewById(R.id.btn_create);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewTodo();
            }
        });
    }

    private void saveNewTodo() {
        ContentValues vals = new ContentValues();
        vals.put(COL_CONTENT, "test");
        vals.put(COL_KIND, getSpinnerValue(R.id.kind));
        vals.put(COL_PRIORITY, getSpinnerValue(R.id.priority));
        vals.put(COL_TODAY, getSpinnerValue(R.id.today));
        vals.put(COL_DATE, getTodayString());
        _db.insert(TBL_TODO, null, vals);
    }

    private int getSpinnerValue(int id) {
        Spinner spinner = (Spinner)findViewById(id);
        return spinner.getSelectedItemPosition();
    }
    
    private String getTodayString() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        return sdf.format(cal.getTime());
    }
}
