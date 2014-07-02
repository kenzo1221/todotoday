package com.example.todotoday;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todotoday.database.TodoStruct;

public class TodoAdapter extends ArrayAdapter<TodoStruct> {
    private static final int VAL_URGENT = 0;
    private static final int VAL_HIGH = 1;
    private static final int VAL_MIDDLE = 2;
    private static final int VAL_LOW = 3;
    private LayoutInflater _inflater;
    
    public TodoAdapter(Context context, int resource, List<TodoStruct> list) {
        super(context, resource, list);
        _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = _inflater.inflate(R.layout.todo_item, parent, false);
        }
        TodoStruct struct = getItem(position);
        int priority = struct.getPriority();
        switch (priority) {
        case VAL_URGENT:
            view.setBackgroundColor(R.drawable.item_priority_urgent);
            break;
        case VAL_HIGH:
            view.setBackgroundColor(R.drawable.item_priority_high);
            break;
        case VAL_MIDDLE:
            view.setBackgroundColor(R.drawable.item_priority_middle);
            break;
        case VAL_LOW:
            view.setBackgroundColor(R.drawable.item_priority_low);
            break;
        default:
            break;
        }
        ((TextView) view.findViewById(R.id.text_content)).setText(struct.getContent());
        return view;
    }
}
