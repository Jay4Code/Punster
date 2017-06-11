package com.lga.punster.model;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lga.punster.R;

public abstract class BaseFragment extends Fragment {

    public static final String TAG = "punster";

    public Context mContext;
    public boolean hasData;
    public RequestQueue mRequestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preprocess();

        View rootView = inflater.inflate(getLayoutId(), container, false);

        initView(rootView);

        loadData(getUserVisibleHint());

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cancel();
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

    protected int getLayoutId() {
        return R.layout.fragment_punster;
    }

    protected abstract void initView(View view);

    protected abstract void loadData(boolean isVisibleToUser);

    protected abstract void cancel();
}
