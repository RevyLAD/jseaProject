package com.jesaproject.jseaproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nell on 2018. 2. 21..
 */

public class About_Application extends Fragment {


    ArrayList<CustomListViewSwitch> arrayList = new ArrayList<CustomListViewSwitch>();
    CustomListViewSwitch CustomListViewSwitch;


    public About_Application(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.about_application, container, false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CustomListviewSwitchAdapter adapter = new CustomListviewSwitchAdapter(getActivity(), R.layout.custom_switch, arrayList);




        ListView list = (ListView) getActivity().findViewById(R.id.switch_listivew);
        Switch asdf = (Switch) getActivity().findViewById(R.id.switch_button);

        CustomListViewSwitch = new CustomListViewSwitch(asdf);
        arrayList.add(CustomListViewSwitch);
        list.setAdapter(adapter);


        ArrayList<HashMap<String, String>> MemberList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> item;

        item = new HashMap<String, String>();
        item.put("Item1","어플리케이션 라이센스");
        item.put("Item2","터치하면 어플리케이션 라이센스와 저작권을 보여줍니다.");
        MemberList.add(item);

        item = new HashMap<String, String>();
        item.put("Item1","개발팀");
        item.put("Item2","강원대학교 삼척캠퍼스 컴퓨터 공학과 팀 JSEA/3S");
        MemberList.add(item);

        item = new HashMap<String, String>();
        item.put("Item1","개발자");
        item.put("Item2","컴퓨터 공학과 서석빈\n컴퓨터 공학과 김정수\n컴퓨터 공학과 함성민");
        MemberList.add(item);
        setListViewText(MemberList);
    }

    public void setListViewText(ArrayList<HashMap<String, String>> Members)
    {
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), Members, R.layout.custom_list_2_line, new String[] {"Item1", "Item2"}, new int[] {R.id.text3, R.id.text4});
        ListView list = (ListView) getActivity().findViewById(R.id.listId);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


                    builder.setTitle("어플리케이션 라이센스 안내")
                            .setMessage("Application에 사용되는 모든 이미지는 Google Material Design임을 알립니다.\n\n\n\nCopyright (C) 2006 The Android Open Source Project\n" +
                                    "\n" +
                                    "     Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                                    "     you may not use this file except in compliance with the License.\n" +
                                    "     You may obtain a copy of the License at\n" +
                                    "  \n" +
                                    "          http://www.apache.org/licenses/LICENSE-2.0\n" +
                                    "  \n" +
                                    "     Unless required by applicable law or agreed to in writing, software\n" +
                                    "     distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                                    "     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                                    "     See the License for the specific language governing permissions and\n" +
                                    "     limitations under the License.")
                            .setCancelable(false)
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).create()
                            .show();
                }
            }
        });
        list.setAdapter(simpleAdapter);
    }
}
