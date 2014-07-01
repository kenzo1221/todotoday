package com.example.todotoday;

import static com.example.todotoday.database.DataBaseConsts.*;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.todotoday.database.TodoTableHelper;
import com.example.todotoday.util.TodayStringGetter;

public class CreateTodoActivity extends ActionBarActivity {
    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);
        _db = TodoTableHelper.initDataBase(this);
        createComponents();
        setButtonProcedure();
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
        vals.put(COL_CONTENT, getEditTextValue(R.id.content));
        vals.put(COL_KIND, getSpinnerValue(R.id.kind));
        vals.put(COL_PRIORITY, getSpinnerValue(R.id.priority));
        vals.put(COL_TODAY, getSpinnerValue(R.id.today));
        vals.put(COL_DATE, TodayStringGetter.execute());
        _db.insert(TBL_TODO, null, vals);
    }

    private int getSpinnerValue(int id) {
        Spinner spinner = (Spinner) findViewById(id);
        return spinner.getSelectedItemPosition();
    }

    private String getEditTextValue(int id) {
        EditText editText = (EditText) findViewById(id);
        SpannableStringBuilder sb = (SpannableStringBuilder) editText.getText();
        return sb.toString();
    }
}
