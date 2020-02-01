package com.jesaproject.jseaproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by nell on 2018. 1. 24..
 */

public class ScheduleFragment extends Fragment{

    private ListView listView;
    private ListViewAdapter adapter;

    String[] Title1;
    String[] Context1;
    String[] Title2;
    String[] Context2;
    String[] Title3;
    String[] Context3;
    String[] Title4;
    String[] Context4;
    String[] Title5;
    String[] Context5;
    String[] Title6;
    String[] Context6;
    String[] Title7;
    String[] Context7;
    String[] Title8;
    String[] Context8;
    String[] Title9;
    String[] Context9;
    String[] Title10;
    String[] Context10;
    String[] Title11;
    String[] Context11;
    String[] Title12;
    String[] Context12;
    String[] Title13;
    String[] Context13;
    String[] Title14;
    String[] Context14;

    int date;
    int year;
    int year2=2018;
    TextView textView;
    Button button1;
    Button button2;
    Button button3;
    AlertDialog mDialog = null;
    long mNow;
    Date mDate;
    Date mDate2;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();
        Title1 = res.getStringArray(R.array.month_1);
        Context1 = res.getStringArray(R.array.content_1);

        Title2 = res.getStringArray(R.array.month_2);
        Context2 = res.getStringArray(R.array.content_2);

        Title3 = res.getStringArray(R.array.month_3);
        Context3 = res.getStringArray(R.array.content_3);

        Title4 = res.getStringArray(R.array.month_4);
        Context4 = res.getStringArray(R.array.content_4);

        Title5 = res.getStringArray(R.array.month_5);
        Context5 = res.getStringArray(R.array.content_5);

        Title6 = res.getStringArray(R.array.month_6);
        Context6 = res.getStringArray(R.array.content_6);

        Title7 = res.getStringArray(R.array.month_7);
        Context7 = res.getStringArray(R.array.content_7);

        Title8 = res.getStringArray(R.array.month_8);
        Context8 = res.getStringArray(R.array.content_8);

        Title9 = res.getStringArray(R.array.month_9);
        Context9 = res.getStringArray(R.array.content_9);

        Title10 = res.getStringArray(R.array.month_10);
        Context10 = res.getStringArray(R.array.content_10);

        Title11 = res.getStringArray(R.array.month_11);
        Context11 = res.getStringArray(R.array.content_11);

        Title12 = res.getStringArray(R.array.month_12);
        Context12 = res.getStringArray(R.array.content_12);

        Title13 = res.getStringArray(R.array.month_13);
        Context13 = res.getStringArray(R.array.content_13);

        Title14 = res.getStringArray(R.array.month_14);
        Context14 = res.getStringArray(R.array.content_14);



        SimpleDateFormat mFormat3 = new SimpleDateFormat("MM");
        SimpleDateFormat mFormat4 = new SimpleDateFormat("yyyy", Locale.US);

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);

        String strDate3 = mFormat3.format(mDate);
        String strDate4 = mFormat4.format(mDate);


        adapter = new ListViewAdapter();
        listView = (ListView) getActivity().findViewById(R.id.List_view);
        listView.setAdapter(adapter);

        button1 = (Button) getActivity().findViewById(R.id.button1);
        button2 = (Button) getActivity().findViewById(R.id.button2);
        textView = (TextView) getActivity().findViewById(R.id.textView2);
        textView.setText(Html.fromHtml("<u>" + "</u>"));
        textView.setText(strDate4 + "년" + " " + strDate3 + "월");


        date = Integer.parseInt(strDate3);
        year = Integer.parseInt(strDate4);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (date > 1) {
                    date = date - 1;
                } else {
                    date = 12;
                    year = year - 1;
                }
                switch (date) {
                    case 1:
                        if(year==year2) {
                            adapter.listVO.clear();
                            for (int i = 0; i < Title1.length; i++) {
                                adapter.addVO(Title1[i], Context1[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            adapter.listVO.clear();
                            for (int i = 0; i < Title13.length; i++) {
                                adapter.addVO(Title13[i], Context13[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case 2:
                        if(year==year2) {
                            adapter.listVO.clear();
                            for (int i = 0; i < Title2.length; i++) {
                                adapter.addVO(Title2[i], Context2[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            adapter.listVO.clear();
                            for (int i = 0; i < Title14.length; i++) {
                                adapter.addVO(Title14[i], Context14[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case 3:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title3.length; i++) {
                            adapter.addVO(Title3[i], Context3[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case 4:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title4.length; i++) {
                            adapter.addVO(Title4[i], Context4[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case 5:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title5.length; i++) {
                            adapter.addVO(Title5[i], Context5[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case 6:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title6.length; i++) {
                            adapter.addVO(Title6[i], Context6[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 7:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title7.length; i++) {
                            adapter.addVO(Title7[i], Context7[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 8:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title8.length; i++) {
                            adapter.addVO(Title8[i], Context8[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 9:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title9.length; i++) {
                            adapter.addVO(Title9[i], Context9[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 10:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title10.length; i++) {
                            adapter.addVO(Title10[i], Context10[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 11:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title11.length; i++) {
                            adapter.addVO(Title11[i], Context11[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 12:
                        if(year!=year2)
                        {
                            mDialog = createDialog();
                            mDialog.show();
                            button2.callOnClick();
                        }
                        else {
                            adapter.listVO.clear();
                            for (int i = 0; i < Title12.length; i++) {
                                adapter.addVO(Title12[i], Context12[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        break;

                    default:
                        break;

                }
                textView.setText(year + "년" + " " + date + "월");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date < 12) {
                    date = date + 1;

                } else {
                    date = 1;
                    year = year + 1;
                }
                switch (date) {
                    case 1:
                        if(year==year2) {
                            adapter.listVO.clear();
                            for (int i = 0; i < Title1.length; i++) {
                                adapter.addVO(Title1[i], Context1[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            adapter.listVO.clear();
                            for (int i = 0; i < Title13.length; i++) {
                                adapter.addVO(Title13[i], Context13[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    case 2:
                        if(year==year2) {
                            adapter.listVO.clear();
                            for (int i = 0; i < Title2.length; i++) {
                                adapter.addVO(Title2[i], Context2[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            adapter.listVO.clear();
                            for (int i = 0; i < Title14.length; i++) {
                                adapter.addVO(Title14[i], Context14[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        break;

                    case 3:
                        if(year!=year2)
                        {
                            mDialog = createDialog();
                            mDialog.show();
                            button1.callOnClick();
                        }
                        else {

                            adapter.listVO.clear();
                            for (int i = 0; i < Title3.length; i++) {
                                adapter.addVO(Title3[i], Context3[i]);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        break;

                    case 4:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title4.length; i++) {
                            adapter.addVO(Title4[i], Context4[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 5:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title5.length; i++) {
                            adapter.addVO(Title5[i], Context5[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 6:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title6.length; i++) {
                            adapter.addVO(Title6[i], Context6[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 7:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title7.length; i++) {
                            adapter.addVO(Title7[i], Context7[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 8:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title8.length; i++) {
                            adapter.addVO(Title8[i], Context8[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 9:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title9.length; i++) {
                            adapter.addVO(Title9[i], Context9[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 10:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title10.length; i++) {
                            adapter.addVO(Title10[i], Context10[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 11:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title11.length; i++) {
                            adapter.addVO(Title11[i], Context11[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    case 12:
                        adapter.listVO.clear();
                        for (int i = 0; i < Title12.length; i++) {
                            adapter.addVO(Title12[i], Context12[i]);
                        }
                        adapter.notifyDataSetChanged();
                        break;

                    default:
                        break;
                }
                textView.setText(year + "년" + " " + date + "월");
            }
        });

        if (strDate3.equals("01")) {
            if(year==year2) {
                for (int i = 0; i < Title1.length; i++) {
                    adapter.addVO(Title1[i], Context1[i]);
                }
            }
            else{
                for (int i = 0; i < Title13.length; i++) {
                    adapter.addVO(Title13[i], Context13[i]);
                }
            }
        }

        if (strDate3.equals("02")) {
            if(year==year2) {
                for (int i = 0; i < Title2.length; i++) {
                    adapter.addVO(Title2[i], Context2[i]);
                }
            }
            else
            {
                for (int i = 0; i < Title14.length; i++) {
                    adapter.addVO(Title14[i], Context14[i]);
                }
            }
        }

        if (strDate3.equals("03")) {
            for (int i = 0; i < Title3.length; i++) {
                adapter.addVO(Title3[i], Context3[i]);
            }
        }

        if (strDate3.equals("04")) {
            for (int i = 0; i < Title4.length; i++) {
                adapter.addVO(Title4[i], Context4[i]);
            }
        }

        if (strDate3.equals("05")) {
            for (int i = 0; i < Title5.length; i++) {
                adapter.addVO(Title5[i], Context5[i]);
            }
        }

        if (strDate3.equals("06")) {
            for (int i = 0; i < Title6.length; i++) {
                adapter.addVO(Title6[i], Context6[i]);
            }
        }

        if (strDate3.equals("07")) {
            for (int i = 0; i < Title7.length; i++) {
                adapter.addVO(Title7[i], Context7[i]);
            }
        }

        if (strDate3.equals("08")) {
            for (int i = 0; i < Title8.length; i++) {
                adapter.addVO(Title8[i], Context8[i]);
            }
        }

        if (strDate3.equals("09")) {
            for (int i = 0; i < Title9.length; i++) {
                adapter.addVO(Title9[i], Context9[i]);
            }
        }

        if (strDate3.equals("10")) {
            for (int i = 0; i < Title10.length; i++) {
                adapter.addVO(Title10[i], Context10[i]);
            }
        }

        if (strDate3.equals("11")) {
            for (int i = 0; i < Title11.length; i++) {
                adapter.addVO(Title11[i], Context11[i]);
            }
        }

        if (strDate3.equals("12")) {
            for (int i = 0; i < Title12.length; i++) {
                adapter.addVO(Title12[i], Context12[i]);
            }
        }
    }

    private AlertDialog createDialog(){
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        ab.setTitle("공지사항");
        ab.setMessage("업데이트 준비 중 입니다.");
        ab.setCancelable(false);
        ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface aro0, int arg1) {
                setDismiss(mDialog);
            }
        });
        return ab.create();
    }
    private void setDismiss(Dialog dialog){

        if(dialog != null && dialog.isShowing())

            dialog.dismiss();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.board_listview, container, false);

        return view;
    }
}
