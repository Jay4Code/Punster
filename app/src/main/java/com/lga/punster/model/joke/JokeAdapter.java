package com.lga.punster.model.joke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.lga.punster.model.BaseAdapter;
import com.lga.punster.BitmapCache;
import com.lga.punster.model.bean.PunsterBean;
import com.lga.punster.R;
import com.lga.view.CircleNetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2017/5/19.
 */

public class JokeAdapter extends BaseAdapter<JokeAdapter.JokeViewHolder> {

    public static class JokeViewHolder extends RecyclerView.ViewHolder {

        private CircleNetworkImageView mIvAvatar;
        private TextView mTvAuthor;
        private TextView mTvJoke;
        private TextView mTvDigg;
        private TextView mTvBury;
        private TextView mTvComment;
        private TextView mTvShare;

        public JokeViewHolder(View itemView) {
            super(itemView);

            mIvAvatar = (CircleNetworkImageView) itemView.findViewById(R.id.iv_avatar);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mTvJoke = (TextView) itemView.findViewById(R.id.tv_joke);
            mTvDigg = (TextView) itemView.findViewById(R.id.tv_digg);
            mTvBury = (TextView) itemView.findViewById(R.id.tv_bury);
            mTvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            mTvShare = (TextView) itemView.findViewById(R.id.tv_share);
        }
    }

    private Context mContext;
    private int mLayoutId;
    private List<PunsterBean> mDataList;

    private ImageLoader mImageLoader;

    public JokeAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mDataList = new ArrayList<>();
    }

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        JokeViewHolder holder = (JokeViewHolder) (viewHolder);

        PunsterBean bean = mDataList.get(position);
        holder.mIvAvatar.setDefaultImageResId(R.drawable.ic_default_round_avatar);
        holder.mIvAvatar.setErrorImageResId(R.drawable.ic_default_round_avatar);
        holder.mIvAvatar.setImageUrl(bean.data.group.user.avatar_url, mImageLoader);

        holder.mTvAuthor.setText(bean.data.group.user.name);

        holder.mTvJoke.setText(bean.data.group.text);

        holder.mTvDigg.setText(bean.data.group.digg_count);
        holder.mTvBury.setText(bean.data.group.bury_count);
        holder.mTvComment.setText(bean.data.group.comment_count);
        holder.mTvShare.setText(bean.data.group.share_count);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        mImageLoader = new ImageLoader(requestQueue, new BitmapCache());
    }

    @Override
    public void addAllData(List<PunsterBean> dataList) {
        mDataList.addAll(dataList);
    }
}
