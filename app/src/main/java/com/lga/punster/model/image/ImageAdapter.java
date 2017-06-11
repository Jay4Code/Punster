package com.lga.punster.model.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
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

public class ImageAdapter extends BaseAdapter<ImageAdapter.ImageViewHolder> {

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        private CircleNetworkImageView mIvAvatar;
        private TextView mTvAuthor;
        private GridView mGvImages;
        private NetworkImageView mIvImage;
        private TextView mTvDigg;
        private TextView mTvBury;
        private TextView mTvComment;
        private TextView mTvShare;

        public ImageViewHolder(View itemView) {
            super(itemView);

            mIvAvatar = (CircleNetworkImageView) itemView.findViewById(R.id.iv_avatar);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mGvImages = (GridView) itemView.findViewById(R.id.gv_images);
            mIvImage = (NetworkImageView) itemView.findViewById(R.id.iv_image);
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

    public ImageAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mDataList = new ArrayList<>();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ImageViewHolder holder = (ImageViewHolder) (viewHolder);

        PunsterBean bean = mDataList.get(position);
        holder.mIvAvatar.setDefaultImageResId(R.drawable.ic_default_round_avatar);
        holder.mIvAvatar.setErrorImageResId(R.drawable.ic_default_round_avatar);
        holder.mIvAvatar.setImageUrl(bean.data.group.user.avatar_url, mImageLoader);

        holder.mTvAuthor.setText(bean.data.group.user.name);
        
        try {
            holder.mIvImage.setDefaultImageResId(R.drawable.ic_loading);
            holder.mIvImage.setErrorImageResId(R.drawable.ic_no_connection);
            holder.mIvImage.setImageUrl(bean.data.group.middle_image.url_list.get(0).url, mImageLoader);
        } catch (NullPointerException e) {
            Log.e("kelly", "NullPointerException:" + holder.mTvAuthor.getText());
            holder.mIvImage.setBackgroundResource(R.drawable.ic_loading);
        }

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
