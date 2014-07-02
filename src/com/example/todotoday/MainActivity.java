package com.example.todotoday;

import static com.example.todotoday.database.DataBaseConsts.*;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.todotoday.database.OrderConverter;
import com.example.todotoday.database.TodoStruct;
import com.example.todotoday.database.TodoTableHelper;
import com.example.todotoday.util.TodayStringGetter;

public class MainActivity extends ActionBarActivity {
    private SQLiteDatabase _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _db = TodoTableHelper.initDataBase(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.create_todo) {
            Intent intent = new Intent(this, CreateTodoActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.test) {
            Intent intent = new Intent(this, TestSettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Override
    protected void onResume() {
        createTodoList();
        super.onResume();
    }

    private void createTodoList() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        TodoAdapter adapter = new TodoAdapter(this, android.R.layout.simple_list_item_1, doLoad());
        listView.setAdapter(adapter);
    }

    private List<TodoStruct> doLoad() {
        String order = getOrderString();
        Cursor cursor = null;
        try {
            cursor = _db.query(TBL_TODO,
                    new String[] { COL_CONTENT, COL_KIND, COL_PRIORITY, COL_TODAY },
                    "today = ?", new String[] { TodayStringGetter.execute() },
                    null, null, order, null);
            List<TodoStruct> list = new ArrayList<TodoStruct>();
            while (cursor.moveToNext()) {
                list.add(new TodoStruct(cursor));
            }
            System.out.println("count:" + String.valueOf(list.size()));
            return list;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String getOrderString() {
        //TODO ボタンの状態から順序を取得
        return OrderConverter.getOrderString(new ArrayList<String>(), new ArrayList<String>());
    }
}
