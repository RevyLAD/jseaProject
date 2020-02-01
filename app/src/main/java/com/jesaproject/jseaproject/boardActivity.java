package com.jesaproject.jseaproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by nell on 2018. 1. 31..
 */

public class boardActivity extends AppCompatActivity {


    TextView title, content;
    EditText nameText, ripleText;
    ListView list;

    String regId;

    RequestQueue queue;

    String date;
    String token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.board_activity);


        Intent intent = getIntent();

        date = intent.getExtras().getString("date");
        token = intent.getExtras().getString("token");
        int i = loadBoard(intent, date);

        if(i == 1) {
            String url2 = "http://JSEA.myq-see.com/download/comment.php";
            list = (ListView) findViewById(R.id.board_list2);
            Download_comment c = new Download_comment(this, url2, list, date);
            c.execute();
        }

        Button insertBtn = (Button) findViewById(R.id.insertRipleBtn);
        nameText = (EditText) findViewById(R.id.reditName);
        ripleText = (EditText) findViewById(R.id.rContent);

        queue = Volley.newRequestQueue(getApplicationContext());

        getRegistrationId();






        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nameText.getText().toString().equals(""))
                {
                    Toast.makeText(boardActivity.this, "댓글의 닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    nameText.requestFocus();
                }
                else if(ripleText.getText().toString().equals(""))
                {
                    Toast.makeText(boardActivity.this, "댓글의 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    ripleText.requestFocus();
                }
                else
                {
                    CustomTask task = new CustomTask();

                    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = sdfNow.format(new Date(System.currentTimeMillis()));

                    try {
                        String result = task.execute(nameText.getText().toString(), date, ripleText.getText().toString(), time, "riple").get();

                        if(result.equals("asdf"))
                        {
                            nameText.setText("");
                            ripleText.setText("");
                            Toast.makeText(boardActivity.this, "댓글이 정상적으로 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                            String url2 = "http://JSEA.myq-see.com/download/comment.php";
                            list = (ListView) findViewById(R.id.board_list2);
                            Download_comment c = new Download_comment(view.getContext(), url2, list, date);
                            c.execute();
                            send("b");

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }



    public void getRegistrationId()
    {


        regId = token;
        Log.d("ASDFASDF", token);
    }

    public void send(String input)
    {
        JSONObject requestData = new JSONObject();

        try
        {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);


            JSONArray idArray = new JSONArray();
            idArray.put(0, regId);

            requestData.put("registration_ids", idArray);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendData(requestData, new SendResponseListener() {
            @Override
            public void onRequestStarted() {

            }

            @Override
            public void onRequestCompleted() {

            }

            @Override
            public void onRequestWithError(VolleyError error) {

            }
        });
    }


    public interface SendResponseListener{
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);

    }


    public void sendData(JSONObject requestData, final SendResponseListener listener)
    {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<String, String>();

                headers.put("Authorization", "key=AIzaSyBSp-Ht15T41ueKYCU605auCdGvHrpqcd8");
                return headers;
            }

            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        queue.add(request);
    }
    private int loadBoard(Intent intent, String date)
    {
        String url="http://JSEA.myq-see.com/download/board.php";

        title = (TextView) findViewById(R.id.bTitle);
        content = (TextView) findViewById(R.id.bContent);
        Download_board d = new Download_board(this,url,title,content, date);
        d.execute();
        return 1;
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
                sendMsg = "nickname="+strings[0]+"&docNum="+strings[1]+"&content="+strings[2]+"&updateDate="+strings[3]+"&select="+strings[4];

                osw.write(sendMsg);
                Log.i("보내진 값", strings[4]);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("###", receiveMsg);

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

}
