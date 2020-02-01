package com.jesaproject.jseaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2018-01-24.
 */

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<ListVO> listVO = new ArrayList<ListVO>();

    public ListViewAdapter() {
    }

    @Override
    public int getCount() {
        return listVO.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position = Listview 위치 / 첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView Context = (TextView) convertView.findViewById(R.id.context);

        ListVO listViewItem = listVO.get(position);

        title.setText(listViewItem.getTitle());
        Context.setText(listViewItem.getContext());

        return convertView;
    }

    @Override
    public Object getItem(int posirion) {
        return listVO.get(posirion);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addVO(String title, String desc) {
        ListVO item = new ListVO();

        item.setTitle(title);
        item.setContext(desc);

        listVO.add(item);
    }

}
