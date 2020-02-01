package com.jesaproject.jseaproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class busFragment extends android.support.v4.app.Fragment {
    ExpandableListView expandableListView;
    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String,ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bus, null);
        Toast.makeText(getActivity(),"실제 버스 시간은 ±5분의 차이가 있을 수 있습니다.",Toast.LENGTH_SHORT).show();
        expandableListView = (ExpandableListView) view.findViewById(R.id.buslist);
        setArrayDate();
        expandableListView.setAdapter(new AdptMain(getActivity(), arrayGroup, arrayChild));

        imageView = (ImageView)view.findViewById(R.id.bus1);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String menu = arrayChild.get( arrayGroup.get(groupPosition)).get(childPosition);
                if(groupPosition == 0 && childPosition == 0) {
                    imageView.setImageResource(R.drawable.bus1);
                }
                else if (groupPosition == 0 && childPosition == 1) {
                    imageView.setImageResource(R.drawable.bus2);
                }
                else if (groupPosition == 0 && childPosition == 2) {
                    imageView.setImageResource(R.drawable.bus3);
                }
                else if (groupPosition == 1 && childPosition == 0) {
                    imageView.setImageResource(R.drawable.bus_01);
                }
                else if (groupPosition == 1 && childPosition == 1) {
                    imageView.setImageResource(R.drawable.bus_02);
                }
                else if (groupPosition == 1 && childPosition == 2) {
                    imageView.setImageResource(R.drawable.bus_03);
                }
                else if (groupPosition == 1 && childPosition == 3) {
                    imageView.setImageResource(R.drawable.bus_04);
                }
                else if (groupPosition == 1 && childPosition == 4) {
                    imageView.setImageResource(R.drawable.bus_05);
                }
                else if (groupPosition == 1 && childPosition == 5) {
                    imageView.setImageResource(R.drawable.bus_06);
                }
                else if (groupPosition == 1 && childPosition == 6) {
                    imageView.setImageResource(R.drawable.bus_07);
                }
                return false;
            }
        });
        return view;
    }

    private void setArrayDate() {
        arrayGroup.add("시내 순환");
        arrayGroup.add("삼척~도계");

        ArrayList<String> arraycycle = new ArrayList<String>();
        arraycycle.add("1회차");
        arraycycle.add("2회차");
        arraycycle.add("3회차");

        ArrayList<String> arraymove = new ArrayList<String>();
        arraymove.add("1회차");
        arraymove.add("2회차");
        arraymove.add("3회차");
        arraymove.add("4회차");
        arraymove.add("5회차");
        arraymove.add("6회차");
        arraymove.add("7회차");

        arrayChild.put(arrayGroup.get(0), arraycycle);
        arrayChild.put(arrayGroup.get(1), arraymove);

    }
}