package com.lga.punster.model;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.lga.punster.model.bean.PunsterBean;

import java.util.List;

/**
 * Created by Jay on 2017/6/9.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {}

    @Override
    public int getItemCount() {
        return 0;
    }

    public abstract void setRequestQueue(RequestQueue requestQueue);

    public abstract void addAllData(List<PunsterBean> dataList);
}
