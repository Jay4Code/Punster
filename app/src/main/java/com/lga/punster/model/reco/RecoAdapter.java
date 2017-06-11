package com.lga.punster.model.reco;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2017/5/19.
 */

public class RecoAdapter  extends BaseAdapter<RecoAdapter.RecoViewHolder> {

    public static class RecoViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvAvatar;
        private TextView mTvAuthor;
        private SurfaceView mSfvVideo;
        private TextView mTvDigg;
        private TextView mTvBury;
        private TextView mTvComment;
        private TextView mTvShare;

        public RecoViewHolder(View itemView) {
            super(itemView);

            mIvAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mSfvVideo = (SurfaceView) itemView.findViewById(R.id.sfv_video);
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

    public RecoAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mDataList = new ArrayList<>();
    }

    @Override
    public RecoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reco, parent, false);
        return new RecoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecoViewHolder holder = (RecoViewHolder) (viewHolder);

        PunsterBean bean = mDataList.get(position);
        /*holder.mIvAvatar.setImageResource(R.drawable.ic_default_round_avatar);
        holder.mTvAuthor.setText(bean.getUserBean().getAuthor());
//        holder.mSfvVideo
        holder.mTvDigg.setText(bean.getInteractionBean().getDigg());
        holder.mTvBury.setText(bean.getInteractionBean().getBury());
        holder.mTvComment.setText(bean.getInteractionBean().getComment());
        holder.mTvShare.setText(bean.getInteractionBean().getShare());*/
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
