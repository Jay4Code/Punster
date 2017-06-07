package com.lga.punster.image;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lga.punster.FastJsonRequest;
import com.lga.punster.R;
import com.lga.punster.bean.Data;
import com.lga.punster.bean.Joke;
import com.lga.punster.constant.Constant;
import com.lga.punster.image.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2017/5/19.
 */

public class ImageFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private Context mContext;
    private boolean hasData;
    private RequestQueue mRequestQueue;

    private SwipeRefreshLayout mSrlLoad;
    private RecyclerView mRv;
    private List<ImageBean> mImageBeanList;
    private ImageAdapter mAdapter;

    public ImageFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ImageFragment newInstance(int sectionNumber) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preprocess();

        View rootView = inflater.inflate(R.layout.fragment_punster, container, false);
        initView(rootView);

        loadData(getUserVisibleHint());

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        loadData(isVisibleToUser);
    }

    private void preprocess() {
        mContext = getContext();

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
    }

    private void initView(View v) {
        mSrlLoad = (SwipeRefreshLayout) v.findViewById(R.id.srl_load);
        mSrlLoad.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        mRv = (RecyclerView) v.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);
        mRv.setLayoutManager(manager);
        mImageBeanList = new ArrayList<>();
        mAdapter = new ImageAdapter(mContext, R.layout.item_image, mImageBeanList, mRequestQueue);
        mRv.setAdapter(mAdapter);
    }

    private void loadData(boolean isVisibleToUser) {
        if (isVisibleToUser && mContext != null && !hasData) {
            mSrlLoad.setRefreshing(true);
            loadData();
        }
    }

    private void loadData() {
        FastJsonRequest<Joke> fjr = new FastJsonRequest<>(
                Request.Method.GET,
                Constant.IMAGE_URL,
                Joke.class,
                new Response.Listener<Joke>() {
                    @Override
                    public void onResponse(Joke joke) {
                        if (joke == null) {
                            Log.e("kelly", "parse error, no bean maybe.");
                        } else if (joke.jokeInfo != null && joke.jokeInfo.data != null) {
                            hasData = true;
                            parseResult(joke.jokeInfo.data);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("kelly", "onErrorResponse");
                        if(mSrlLoad.isShown()) mSrlLoad.setRefreshing(false);
                    }
                }
        );
        mRequestQueue.add(fjr);
    }

    private void parseResult(List<Data> dataList) {
        int jokeCount = 0;
        int adCount = 0;
        int hotData = 0;

        for (int i = 0; i < dataList.size(); i++) {
            Data data = dataList.get(i);
            if (data.ad != null) {
                adCount++;
            } else if (data.hotData != null) {
                hotData++;
            } else if (data.group != null) {
                jokeCount++;

                try {
                    if (data.group.is_multi_image != 1) {
                        ImageBean bean = new ImageBean();
                        bean.data = data;
                        mImageBeanList.add(bean);
                    }
                } catch (NullPointerException e) {
                }
            }
        }

        mAdapter.notifyDataSetChanged();
        mRv.scrollToPosition(mImageBeanList.size() - 1);

        if(mSrlLoad.isShown()) mSrlLoad.setRefreshing(false);

        Log.e("kellyy", jokeCount + "条图片，" + adCount + "条广告, " + hotData + "条热门");
    }
}
