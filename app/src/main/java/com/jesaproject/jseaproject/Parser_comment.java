package com.jesaproject.jseaproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nell on 2018. 1. 31..
 */

public class Parser_comment extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    String date;
    int type = 0;

    ArrayList<CustomListView> arrayList = new ArrayList<CustomListView>();

    CustomListView customListView;
    ProgressDialog pd;

    public Parser_comment(Context c, String data, ListView lv, String date) {
        this.c = c;
        this.data = data;
        this.lv = lv;
        this.date = date;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("알림");
        pd.setCancelable(false);
        pd.setMessage("데이터와 통신중입니다. 잠시만 기다려 주십시오.");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {

        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {
            //ADAPTER
            final CustomListViewAdapter adapter = new CustomListViewAdapter(c, R.layout.list_view_item, arrayList);

            //ADAPT TO LISTVIEW
            lv.setAdapter(adapter);

            //LISTENET
            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(c, position, Toast.LENGTH_SHORT).show();
                }
            });*/

            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(c, boardActivity.class);
                    intent.putExtra("date", arrayList.get(i).dateTitle);
                    c.startActivity(intent);
                }
            });*/

        }else
        {
            Toast.makeText(c,"데이터 불러오기 실패",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    //PARSE RECEIVED DATA
    private int parse()
    {
        try
        {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja = new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;

            arrayList.clear();

            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                if(date.equals(jo.getString("docnum"))) {
                    String title = jo.getString("content");
                    String nickname = jo.getString("nickname");
                    String UpdateDate = jo.getString("updatedate");
                    String token = "Asdf";

                    customListView = new CustomListView(title, nickname, UpdateDate, token);
                    arrayList.add(customListView);
                }

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}