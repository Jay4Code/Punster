package com.lga.punster.model.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.lga.punster.BitmapCache;
import com.lga.punster.R;
import com.lga.punster.model.BaseAdapter;
import com.lga.punster.model.bean.PunsterBean;
import com.lga.view.CircleNetworkImageView;
import com.lga.view.EasyJCVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Jay on 2017/5/19.
 */

public class VideoAdapter extends BaseAdapter<VideoAdapter.VideoViewHolder> {

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        private CircleNetworkImageView mIvAvatar;
        private TextView mTvAuthor;
        private EasyJCVideoPlayer mVideoPlayer;
        private TextView mTvDigg;
        private TextView mTvBury;
        private TextView mTvComment;
        private TextView mTvShare;

        public VideoViewHolder(View itemView) {
            super(itemView);

            mIvAvatar = (CircleNetworkImageView) itemView.findViewById(R.id.iv_avatar);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mVideoPlayer = (EasyJCVideoPlayer) itemView.findViewById(R.id.videoPlayer);
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

    public VideoAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mDataList = new ArrayList<>();
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        VideoViewHolder holder = (VideoViewHolder) (viewHolder);

        PunsterBean bean = mDataList.get(position);
        holder.mIvAvatar.setDefaultImageResId(R.drawable.ic_default_round_avatar);
        holder.mIvAvatar.setErrorImageResId(R.drawable.ic_default_round_avatar);
        holder.mIvAvatar.setImageUrl(bean.data.group.user.avatar_url, mImageLoader);

        holder.mTvAuthor.setText(bean.data.group.user.name);

        // 封面
        holder.mVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.ImageListener listener = mImageLoader.getImageListener(
                holder.mVideoPlayer.thumbImageView,
                R.drawable.ic_loading,
                R.drawable.ic_no_connection
        );
        mImageLoader.get(bean.data.group.medium_cover.url_list.get(0).url, listener);
        // 标题
        String title = bean.data.group.text;
        holder.mVideoPlayer.setDurationText(bean.data.group.duration);
        holder.mVideoPlayer.setUp(bean.data.group.p360.url_list.get(0).url, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);

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
