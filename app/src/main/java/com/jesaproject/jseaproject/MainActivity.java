/*
작성자 : 함성민
수정기록 : 학사일정 추가(ScheduleFragment, ListViewAdapter, ListVO, createNotification, board_listview.xml, custom_listview.xml,dialog.xml)
작성날짜 : 2018.02-06

 */

/*
작성자 : 김정수
수정기록 : 버스 노선도(AdptMain,bus)(bus.xml,listviewitem_child.xml,listviewitem_group.xml)
           drawable21(bus1,2,3.PNG, _01~,_07.PNG);
날짜 : 2018.02.06
 */

/*
작성자 : 서석빈
수정기록 : CustomListViewAdapter, Dowload_board, Dowload_Comment, Downloader...
날짜 : 2018.02.06
 */

/*
작성자 : 함성민
수정기록 : 일정에 맞춰 알람오기, 학사일정 누를시 현재 달에 속한내용 출력 (ScheduleFragment)
날짜 : 2018.02.06

/*
작성자 : 서석빈
수정기록 : 일정에 맞춰 알람오기, 백그라운드 프로세스로 어플리케이션 돌아가도록 수정, 월 / 일 String.xml 로 통합 수정
날자 : 2018.02.20
 */

/*
작성자 : 서석빈
수정기록 : 식단표 웹 뷰 수정, 설정 프래그먼트 수정.
날짜 : 2018.02.21
 */

/*
작성자 : 함성민
수정기록 : 학사일정 내년 2월까지 추가.(ScheduleFragment , values(strings.xml) --->수정완료)
-----!! ScheduleServices 수정 미완 !!------ 수정 완료 - 서석빈 2018.02.27
날짜 : 2018.02.22
 */



/*


 */


package com.jesaproject.jseaproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BackPressCloseHandler backPressCloseHandler;
    String FILENAME = "check.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        backPressCloseHandler = new BackPressCloseHandler(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = new Intent(MainActivity.this,ScheduleServices.class);
        startService(intent);

        String path = getFilesDir().getAbsolutePath()+"/"+FILENAME;

        File files = new File(path);
        if(!files.exists()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("사용자 약관 동의서")
                    .setMessage(" 우리 어플리케이션은 이용하는 기간동안 최선의 서비스를 제공할 것을 약속합니다.\n" +
                            "\n" +
                            "사용자의 의무\n" +
                            " 1. 사용자는 어플리케이션이 방해받지 않는 내에서 최선의 서비스를 받아야할 의무가 있으며, 즉각적인 오류 사항에 대해서 수정 혹은 개발등을 정당히 요청할 수 있는 권한이 있습니다.\n" +
                            "\n" +
                            " 2. 사용자는 어플리케이션 이용 중 다음과같은 사항에 대해서 제재를 받을 수 있습니다.\n" +
                            " \ta) 타인을 비난 혹은 비판 하는 행위.\n" +
                            " \tb) 허위 게시글 알림 \n" +
                            "  \t\t- 허위 사실이라 함은 실제로 일어나지 않은 일 또는 타인에게 해를 끼치기 위해 임의적으로 설정한 글 또는 관리자 혹은 개발자가 판단하기에 \n" +
                            "\t\t   우리 어플리케이션의 비방목적을 가진 글이나 그 외 부적절한 게시글 등.\n" +
                            " \tc) 정치적 성향이 짙은 글 혹은 타인에게 정치 성향 강요 등.\n" +
                            " \td) 종교 강요에 대한 선전 글 등.\n" +
                            "\n" +
                            "개발자의 의무\n" +
                            " 다음 강원대학교 삼척캠퍼스 학사 커뮤니티 어플리케이션(이하 '어플리케이션'이라 함)은 사용자가 올바른 정보를 전달받고 또 올바른 정보를 전송하기 위해 우리가 정의한 임의의 데이터 베이스에 게시글에 대한 정보를 안전하게 저장하고있습니다.\n" +
                            "\n" +
                            "  1.개발자 혹은 관리자는 사용자의 데이터베이스 정보를 저장하고 관리할 수 있습니다.\n" +
                            "\n" +
                            " [우리가 수집하고 저장하는 사용자의 정보는 다음과 같습니다.\n" +
                            "저장정보 1. 사용자의 기기 알림 (이하 ‘푸시 알림’이라 함)을 위한 어플리케이션에서 부여하는 Firebase 시스템에서 수집하는 기기 식별 번호.\n" +
                            "저장정보 2. 사용자가 실제 게시글에 게시하는 게시글 ‘제목’, 게시글 ‘닉네임’, 게시글 ‘비밀번호’ 등.\n" +
                            "저장정보 3. 게시자가 타인의 게시글에 남길 수 있는 댓글 기능의 ‘닉네임’ 등]\n" +
                            "\n" +
                            "2. 개발자 혹은 관리자는 사용자에게 최선의 서비스를 제공해야하며 정당한 요청에 대해서는 충분히 검토하고 수정할 수 있습니다\n" +
                            "3. 개발자 혹은 관리자는 사용자에게 통보 없이 사용자가 어플리케이션에 등록한 글 들에 대해서 삭제할 수 있는 권한이 있습니다.\n" +
                            "4. 개발자 혹은 관리자는 사용자에게 통보 없이 임의로 어플리케이션을 수정 할 수 있는 권한이 있습니다.\n" +
                            "\n" +
                            "\n" +
                            " 우리는 위에 명세한대로 사용자의 정보를 수집하고 식별 하고 있으며, 이 의미는 우리의 어플리케이션이 지향하는 ‘익명성’시스템에 따라 데이터 베이스 시스템 관리자 혹은 어플리케이션 관리자 마지막으로 어플리케이션 개발자(이하  모두 ‘관리자’라 함) 등은 이에 따라 사용자가 게시한 글이 사실이라고 해도 해당 게시글의 ‘당위성’ 과 ‘진위성’ 마지막으로 실제 데이터의 ‘무결성’을 판단할 수 없음 을 알립니다.")
                    .setCancelable(false)
                    .setPositiveButton("네, 동의 합니다.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            fileCheck();
                            CustomTask task = new CustomTask();
                            String token = FirebaseInstanceId.getInstance().getToken();
                            task.execute(token,"check");
                        }
                    })
                    .setNegativeButton("아니오, 동의하지 않습니다.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                        }
                    }).create();
            builder.show();
        }
        else
        {
            CustomTask task = new CustomTask();
            String token = FirebaseInstanceId.getInstance().getToken();
            task.execute(token,"check");
        }

        if (savedInstanceState == null) {
            displayView(R.id.nav_board);
            navigationView.setCheckedItem(R.id.nav_board);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backPressCloseHandler.onBackPressd();
            //super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displayView(item.getItemId()); //추후에 작성 할 것이니 걱정 X
        return true;
    }
    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_board:
                fragment = new boardFragment();
                title  = "게시판";
                break;

            case R.id.nav_calendar:
                fragment = new ScheduleFragment();
                title  = "학사일정";
                break;

            case R.id.nav_table:
                fragment = new busFragment();
                title  = "통학 버스 시간표";
                break;
            case R.id.nav_menu:
                fragment = new S_MenuFragment();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
                navigationView.setCheckedItem(R.id.nav_samcheok);
                title  = "삼척캠퍼스 식단표";
                break;
            case R.id.nav_samcheok:
                fragment = new S_MenuFragment();
                title  = "삼척캠퍼스 식단표";
                break;
            case R.id.nav_dogye:
                fragment = new D_MenuFragment();
                title  = "도계캠퍼스 식단표";
                break;
            case R.id.nav_setting:
                title  = "설정";
                fragment = new About_Application();
                break;
            default:
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    private void checkUpdate()
    {
        if (isNetworkAvailable()) {
        } else {
            Toast.makeText(getApplicationContext(), "네트워크가 불안정 하거나 접속이 되지 않습니다. 다시 시도해 주십시오", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isNetworkAvailable() {
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            available = true;
        }
        return available;
    }

    private class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://jsea.myq-see.com:8080/JSEA_APP/data.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "token="+strings[0]+"&select="+strings[1];

                osw.write(sendMsg);
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

    private void fileCheck()
    {
        String path = getFilesDir().getAbsolutePath()+"/"+FILENAME;

        File files = new File(path);
        if(!files.exists())
        {
            try {
                FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                fos.write("1".getBytes());
                fos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "파일 생성 완료.", Toast.LENGTH_SHORT).show();
        }
    }
}
