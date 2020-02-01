package com.jesaproject.jseaproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by nell on 2018. 1. 24..
 */

public class boardFragment extends Fragment{


    ListView list;
    boolean lastitemVisibleFlag = false;
    private String url;

    private int page = 0;

    EditText searchEdit;
    Button searchBtn;



    int currentPage = 0; //전체
    public SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        final TextView AllTxt = (TextView)getActivity().findViewById(R.id.txtAll);

        final TextView NoticeTxt = (TextView)getActivity().findViewById(R.id.txtNotice);

        final TextView BoardTxt = (TextView)getActivity().findViewById(R.id.txtBoard);

        final TextView JobTxt = (TextView)getActivity().findViewById(R.id.txtJob);

        final TextView SellTxt = (TextView)getActivity().findViewById(R.id.txtSell);
        url="http://jsea.myq-see.com/download/board.php";


        searchEdit = (EditText) getActivity().findViewById(R.id.searchEdit);

        searchBtn = (Button) getActivity().findViewById(R.id.searchButton);

        list = (ListView)getActivity().findViewById(R.id.board_list);
        Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
        AllTxt.setTextColor(getResources().getColor(R.color.colorBasic));


        d.execute();


        mSwipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Downloader c = new Downloader(getActivity(),url,list,currentPage,page,"");
                c.execute();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

        AllTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 0;
                String url="http://jsea.myq-see.com/download/board.php";
                Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
                d.execute();
                AllTxt.setTextColor(getResources().getColor(R.color.colorBasic));
                NoticeTxt.setTextColor(getResources().getColor(R.color.colorFont));
                BoardTxt.setTextColor(getResources().getColor(R.color.colorFont));
                JobTxt.setTextColor(getResources().getColor(R.color.colorFont));
                SellTxt.setTextColor(getResources().getColor(R.color.colorFont));
            }
        });

        NoticeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 1;
                String url="http://jsea.myq-see.com/download/board.php";
                Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
                d.execute();
                AllTxt.setTextColor(getResources().getColor(R.color.colorFont));
                NoticeTxt.setTextColor(getResources().getColor(R.color.colorBasic));
                BoardTxt.setTextColor(getResources().getColor(R.color.colorFont));
                JobTxt.setTextColor(getResources().getColor(R.color.colorFont));
                SellTxt.setTextColor(getResources().getColor(R.color.colorFont));
            }
        });

        BoardTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 2;
                String url="http://jsea.myq-see.com/download/board.php";
                Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
                d.execute();
                AllTxt.setTextColor(getResources().getColor(R.color.colorFont));
                NoticeTxt.setTextColor(getResources().getColor(R.color.colorFont));
                BoardTxt.setTextColor(getResources().getColor(R.color.colorBasic));
                JobTxt.setTextColor(getResources().getColor(R.color.colorFont));
                SellTxt.setTextColor(getResources().getColor(R.color.colorFont));
            }
        });

        JobTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 3;
                String url="http://jsea.myq-see.com/download/board.php";
                Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
                d.execute();
                AllTxt.setTextColor(getResources().getColor(R.color.colorFont));
                NoticeTxt.setTextColor(getResources().getColor(R.color.colorFont));
                BoardTxt.setTextColor(getResources().getColor(R.color.colorFont));
                JobTxt.setTextColor(getResources().getColor(R.color.colorBasic));
                SellTxt.setTextColor(getResources().getColor(R.color.colorFont));
            }
        });

        SellTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 4;
                String url="http://jsea.myq-see.com/download/board.php";
                Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
                d.execute();
                AllTxt.setTextColor(getResources().getColor(R.color.colorFont));
                NoticeTxt.setTextColor(getResources().getColor(R.color.colorFont));
                BoardTxt.setTextColor(getResources().getColor(R.color.colorFont));
                JobTxt.setTextColor(getResources().getColor(R.color.colorFont));
                SellTxt.setTextColor(getResources().getColor(R.color.colorBasic));
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchEdit.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    searchEdit.requestFocus();
                }
                else
                {
                    Downloader d = new Downloader(getActivity(), url, list, currentPage, page, searchEdit.getText().toString());
                    d.execute();
                }
            }
        });

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다.
                //즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                    //TODO 화면이 바닦에 닿을때 처리
                    /*page +=10;
                    Downloader d = new Downloader(getActivity(),url,list,currentPage,page,"");
                    d.execute();*/
                }
            }

        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_fragment, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.boardfragment, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.writeDoc) {

            Intent i = new Intent(getActivity(), writeActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
