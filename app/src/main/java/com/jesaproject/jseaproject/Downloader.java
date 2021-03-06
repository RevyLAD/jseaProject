package com.jesaproject.jseaproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nell on 2018. 1. 25..
 */

public class Downloader extends AsyncTask<Void,Integer,String> {

    Context c;
    String address;
    ListView lv;
    int type = 0;
    int page = 0;
    String search;

    ProgressDialog pd;

    public Downloader(Context c, String address, ListView lv, int type, int page, String search) {
        this.c = c;
        this.address = address;
        this.lv = lv;
        this.type = type;
        this.page = page;
        this.search = search;
    }

    //B4 JOB STARTS
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("알림");
        pd.setCancelable(false);
        pd.setMessage("데이터를 불러오는 중입니다. 잠시만 기다려주세요.");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        String data = downloadData();
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();
        ;

        if (s != null) {
            Parser p = new Parser(c, s, lv, type, page, search);
            p.execute();

        } else {
            Toast.makeText(c, "데이터를 다운로드 하는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private String downloadData() {
        //connect and get a stream
        InputStream is = null;
        String line = null;

        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(con.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuffer sb = new StringBuffer();

            if (br != null) {

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

            } else {
                return null;
            }

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

