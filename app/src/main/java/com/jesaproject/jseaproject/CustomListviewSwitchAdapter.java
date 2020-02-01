package com.jesaproject.jseaproject;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class CustomListviewSwitchAdapter extends BaseAdapter {

    Context con;
    LayoutInflater inflater;
    ArrayList<CustomListViewSwitch> arrayList;
    int check = 0;
    int layout;
    String FILENAME = "check.txt";
    String filecheck;

    public Switch title;

    public CustomListviewSwitchAdapter(Context context, int alayout, ArrayList<CustomListViewSwitch> arrayList1) {
        con = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = arrayList1;
        layout = alayout;


    }

    public CustomListviewSwitchAdapter() {

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

        fileRead();

        title = (Switch)view.findViewById(R.id.switch_button);

        if(filecheck.equals("1"))
        {
            title.setChecked(true);
        }
        else
        {
            title.setChecked(false);
        }


        title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    fileWrite("1");
                }
                else
                {
                    fileWrite("0");
                }
            }
        });

        return view;
    }

    private void fileRead()
    {
        try {
            FileInputStream fileInputStream = con.openFileInput(FILENAME);
            byte[] buffer = new byte[fileInputStream.available()];

            fileInputStream.read(buffer);
            filecheck = new String(buffer);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void fileWrite(String str)
    {

        try {
            FileOutputStream fos = con.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CustomListViewSwitch
{
    Switch asdf;
    CustomListViewSwitch(Switch asad)
    {
       asdf = asad;
    }

}

