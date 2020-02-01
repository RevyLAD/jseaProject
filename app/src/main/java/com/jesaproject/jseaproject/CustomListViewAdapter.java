package com.jesaproject.jseaproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nell on 2018. 1. 24..
 */

public class CustomListViewAdapter extends BaseAdapter {

    Context con;
    LayoutInflater inflater;
    ArrayList<CustomListView> arrayList;
    int layout;


    public CustomListViewAdapter(Context context, int alayout, ArrayList<CustomListView> arrayList1) {
        con = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = arrayList1;
        layout = alayout;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = inflater.inflate(layout, viewGroup, false);
        }

        TextView title = (TextView)view.findViewById(R.id.titleText);
        title.setTextSize(20);
        title.setText(arrayList.get(i).Title);

        TextView nTitle = (TextView)view.findViewById(R.id.nameText);
        nTitle.setText(arrayList.get(i).nameTitle);

        TextView dtitle = (TextView)view.findViewById(R.id.dateText);
        dtitle.setText(arrayList.get(i).dateTitle);

        return view;
    }
}

class CustomListView
{
    String Title;
    String nameTitle;
    String dateTitle;
    String token;

    CustomListView(String T, String n, String d, String t)
    {
        Title = T;
        nameTitle = n;
        dateTitle = d;
        token = t;
    }
}
