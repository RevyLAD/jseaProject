package com.jesaproject.jseaproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nell on 2018. 2. 20..
 */

public class D_MenuFragment extends Fragment {
    private FragmentTabHost TabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TabHost = new FragmentTabHost(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TabHost = new FragmentTabHost(getActivity());
        TabHost.setup(getActivity(), getChildFragmentManager(), R.id.real_container);
        TabHost.addTab(TabHost.newTabSpec("TAB1").setIndicator("기숙사"), D_Menu_Tab_One.class, null);
        TabHost.addTab(TabHost.newTabSpec("TAB").setIndicator("학생회관"), D_Menu_Tab_Two.class, null);
        return TabHost;

    }
}