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

public class S_MenuFragment extends Fragment {

    private FragmentTabHost TabHost;

    public static S_MenuFragment newInstance() {
        S_MenuFragment fragment = new S_MenuFragment();
        return fragment;
    }

    public S_MenuFragment() { }

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
        TabHost.addTab(TabHost.newTabSpec("TAB1").setIndicator("기숙사"), S_Menu_Tab_One.class, null);
        TabHost.addTab(TabHost.newTabSpec("TAB2").setIndicator("5공학관"), S_Menu_Tab_Two.class, null);
        TabHost.addTab(TabHost.newTabSpec("TAB3").setIndicator("학생식당"), S_Menu_Tab_Three.class, null);
        return TabHost;

    }
}
