package com.jesaproject.jseaproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nell on 2018. 1. 25..
 */

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    int type = 0;
    int page = 0;
    int maxPage;
    String search;
    int check = 0;

    ArrayList<CustomListView> arrayList = new ArrayList<CustomListView>();

    CustomListView customListView;
    ProgressDialog pd;

    public Parser(Context c, String data, ListView lv, int type, int page, String search) {
        this.c = c;
        this.data = data;
        this.lv = lv;
        this.type = type;
        this.page = page;
        this.search = search;
        maxPage = page+10;

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
            
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(c, boardActivity.class);
                    intent.putExtra("date", arrayList.get(i).dateTitle);
                    intent.putExtra("token", arrayList.get(i).token);
                    c.startActivity(intent);
                }
            });

        }
        else
        {
            Toast.makeText(c,"데이터 불러오기 실패",Toast.LENGTH_SHORT).show();
        }
        if(check == 2)
        {
            Toast.makeText(c,search+"에 대한 결과 값을 찾을 수 없습니다.",Toast.LENGTH_SHORT).show();
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
            for(page = 0; page<ja.length(); page++)
            {

                if(page >= ja.length())
                {
                    break;
                }
                jo=ja.getJSONObject(page);

                String title = jo.getString("title");
                if(!search.isEmpty() && title.toLowerCase().contains(search.toLowerCase()))
                {
                    String nickname = jo.getString("nickname");
                    String UpdateDate = jo.getString("updatedate");
                    String token = jo.getString("token");

                    customListView = new CustomListView(title, nickname, UpdateDate, token);
                    arrayList.add(customListView);
                    check = 1;

                }
                else if(!title.toLowerCase().contains(search.toLowerCase()) && check != 1)
                {
                    check = 2;
                }
                if(check == 0)
                {
                    String docType = jo.getString("type");
                    if(type == Integer.parseInt(docType))
                    {

                        String nickname = jo.getString("nickname");
                        String UpdateDate = jo.getString("update");
                        String token = jo.getString("token");

                        customListView = new CustomListView(title, nickname, UpdateDate, token);
                        arrayList.add(customListView);
                    }

                    else if(type == 0)
                    {
                        String nickname = jo.getString("nickname");
                        String UpdateDate = jo.getString("updatedate");
                        String token = jo.getString("token");

                        customListView = new CustomListView(title, nickname, UpdateDate, token);
                        arrayList.add(customListView);
                    }
                }

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}