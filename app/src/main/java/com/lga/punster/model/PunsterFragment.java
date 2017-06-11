package com.lga.punster.model;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lga.punster.FastJsonRequest;
import com.lga.punster.R;
import com.lga.punster.model.bean.Data;
import com.lga.punster.model.bean.Joke;
import com.lga.punster.model.bean.PunsterBean;
import com.lga.punster.model.image.ImageAdapter;
import com.lga.punster.model.joke.JokeAdapter;
import com.lga.punster.model.video.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;

/**
 * Created by Jay on 2017/5/19.
 */

public class PunsterFragment extends BaseFragment {

    public static final String KEY_RECO = "reco";
    public static final String KEY_JOKE = "joke";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_SHOW = "show";
    public static final String KEY_VIDEO = "video";

    private static final String KEY_TAG = "tag";
    private static final String KEY_URL = "url";

    private SwipeRefreshLayout mSrlLoad;
    private RecyclerView mRv;
    private List<PunsterBean> mDataList;
    private BaseAdapter mAdapter;
    private String mTag;
    private String mUrl;

    public static PunsterFragment newInstance(String tag, String url) {
        PunsterFragment fragment = new PunsterFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TAG, tag);
        bundle.putString(KEY_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(KEY_VIDEO.equals(mTag) && !isVisibleToUser) {
            JCMediaManager.instance().pauseMediaPlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(KEY_VIDEO.equals(mTag)) {
            JCMediaManager.instance().pauseMediaPlayer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(KEY_VIDEO.equals(mTag)) {
            JCMediaManager.instance().releaseMediaPlayer();
        }
    }

    protected void initView(View v) {
        mTag = getArguments().getString(KEY_TAG);
        mUrl = getArguments().getString(KEY_URL);

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

        if (KEY_RECO.equals(mTag)) {
//            mAdapter = new RecoAdapter(mContext, R.layout.item_reco);
        } else if (KEY_JOKE.equals(mTag)) {
            mAdapter = new JokeAdapter(mContext, R.layout.item_joke);
        } else if (KEY_IMAGE.equals(mTag)) {
            mAdapter = new ImageAdapter(mContext, R.layout.item_image);
        } else if (KEY_SHOW.equals(mTag)) {
//            mAdapter = new ShowAdapter(mContext, R.layout.item_show);
        } else if (KEY_VIDEO.equals(mTag)) {
            mAdapter = new VideoAdapter(mContext, R.layout.item_video);
        }

        mAdapter.setRequestQueue(mRequestQueue);
        mRv.setAdapter(mAdapter);

        mDataList = new ArrayList<>();
    }

    protected void loadData(boolean isVisibleToUser) {
        if (isVisibleToUser && mContext != null && !hasData) {
            mSrlLoad.setRefreshing(true);
            loadData();
        }
    }

    private void loadData() {
        FastJsonRequest<Joke> fjr = new FastJsonRequest<>(
                mUrl,
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
                        if (mSrlLoad.isShown()) mSrlLoad.setRefreshing(false);
                    }
                }
        );
        fjr.setTag(mTag);
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
                        PunsterBean bean = new PunsterBean();
                        bean.data = data;
                        mDataList.add(bean);
                    }
                } catch (NullPointerException e) {
                }
            }
        }

        mAdapter.addAllData(mDataList);
        mAdapter.notifyDataSetChanged();
        mRv.scrollToPosition(mAdapter.getItemCount() - 1);

        if (mSrlLoad.isShown()) mSrlLoad.setRefreshing(false);
        Log.e("kellyy", jokeCount + "条段子，" + adCount + "条广告, " + hotData + "条热门");
    }

    @Override
    protected void cancel() {
        if (mRequestQueue != null) mRequestQueue.cancelAll(TAG);
    }
}


























