package com.jesaproject.jseaproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.sql.DriverManager.println;

/**
 * Created by nell on 2018. 1. 30..
 */

public class writeActivity extends AppCompatActivity {

    private CheckBox btn_notice, btn_board, btn_job, btn_sell;
    EditText titleTxt, contentTxt, nameTxt, pwdTxt;
    private int Type = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        titleTxt = (EditText) findViewById(R.id.titleText);
        contentTxt = (EditText) findViewById(R.id.contentText);
        nameTxt = (EditText) findViewById(R.id.nameText);
        pwdTxt = (EditText) findViewById(R.id.pwdText);
        Button updateBtn = (Button) findViewById(R.id.updateButton);
        btn_notice = (CheckBox) findViewById(R.id.checkBox);
        btn_board = (CheckBox) findViewById(R.id.checkBox2);
        btn_job = (CheckBox) findViewById(R.id.checkBox3);
        btn_sell = (CheckBox) findViewById(R.id.checkBox4);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdfNow.format(new Date(System.currentTimeMillis()));

                String result;
                CustomTask task = new CustomTask();
                String asdf = null;
                if (titleTxt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "제목이 비었습니다.", Toast.LENGTH_SHORT).show();
                    titleTxt.requestFocus();
                    return;
                } else if (contentTxt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "내용이 비었습니다.", Toast.LENGTH_SHORT).show();
                    contentTxt.requestFocus();
                    return;
                } else if (nameTxt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "닉네임이 비었습니다.", Toast.LENGTH_SHORT).show();
                    nameTxt.requestFocus();
                    return;
                } else if (pwdTxt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 비었습니다.", Toast.LENGTH_SHORT).show();
                    pwdTxt.requestFocus();
                    return;
                } else if (Type == -1) {
                    Toast.makeText(getApplicationContext(), "카테고리는 반드시 설정해야됩니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Type == 1) {
                    if (pwdTxt.getText().toString().equals("999888777")) {
                        // 실행
                    } else {
                        Toast.makeText(getApplicationContext(), "공지사항 비밀번호가 틀립니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        pwdTxt.requestFocus();
                        return;
                    }
                }
                try {
                    String token = FirebaseInstanceId.getInstance().getToken();
                    result = task.execute(asdf.valueOf(Type), titleTxt.getText().toString(),
                            contentTxt.getText().toString(), token,
                            nameTxt.getText().toString(),
                            pwdTxt.getText().toString(),
                            time,
                            "add").get();
                    new sendNotification().execute("http://jsea.myq-see.com/download/send_Noti.php");
                    if (result.equals("asdf")) {
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("글쓰기 도우미")
                .setMessage("글쓰기가 진행중입니다.\n글쓰기를 종료 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                .setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create()
                .show();
        return super.onKeyDown(keyCode, event);
    }


    public void onCheckboxClicked(View view) {

        switch (view.getId()) {

            case R.id.checkBox: {

                Type = 1;
                btn_board.setChecked(false);
                btn_job.setChecked(false);
                btn_sell.setChecked(false);

                break;
            }
            case R.id.checkBox2: {
                Type = 2;
                btn_notice.setChecked(false);
                btn_job.setChecked(false);
                btn_sell.setChecked(false);

                break;
            }
            case R.id.checkBox3: {
                Type = 3;
                btn_notice.setChecked(false);
                btn_board.setChecked(false);
                btn_sell.setChecked(false);

                break;
            }
            case R.id.checkBox4: {
                Type = 4;
                btn_notice.setChecked(false);
                btn_board.setChecked(false);
                btn_job.setChecked(false);
                break;
            }
        }
    }

    private class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://JSEA.myq-see.com:8080/JSEA_APP/data.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "type=" + strings[0] + "&title=" + strings[1] + "&content=" + strings[2] + "&token=" + strings[3] + "&nickname=" + strings[4] + "&password=" + strings[5] + "&updateDate=" + strings[6] + "&select=" + strings[7];

                osw.write(sendMsg);
                Log.i("보내진 값", strings[4]);
                osw.flush();
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("###", receiveMsg);

                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    private String downloadURL(String strUrl) throws IOException {
        String s = null;
        byte[] buffer = new byte[12];

        InputStream iStream = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            iStream.read(buffer);
            s = new String(buffer);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            iStream.close();
        }
        return s;
    }

    private class sendNotification extends AsyncTask<String, Integer, String> {
        String s = null;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... url) {
            try {
                s = downloadURL(url[0]);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

            Toast.makeText(getApplicationContext(), "새 게시글을 등록 하였습니다. 확인 하시려면 아래로 슬라이드를 내려 새로고침 하십시오.", Toast.LENGTH_SHORT).show();

        }
    }
}
