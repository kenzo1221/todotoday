package com.example.todotoday.database;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    private static final String COMMA = ", ";
    private static final String DESC = " desc";
    
    public static String getOrderString(List<String> columns, List<String> descColumns) {
        complementList(columns);
        complementList(descColumns);
        StringBuffer ordSb = new StringBuffer();
        for (String column : columns) {
            if (ordSb.length() != 0) {
                ordSb.append(COMMA);
            }
            ordSb.append(column);
            if (descColumns.indexOf(column) > -1) {
                ordSb.append(DESC);
            }
        }
        return ordSb.toString();
    }

    private static List<String> complementList(List<String> list) {
        if (list == null) {
            list = new ArrayList<String>();
        }
        return list;
    }
}
