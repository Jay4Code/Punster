package com.lga.punster.reco;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lga.punster.R;
import com.lga.punster.reco.bean.RecoBean;

import java.util.List;

/**
 * Created by Jay on 2017/5/19.
 */

public class RecoAdapter extends RecyclerView.Adapter<RecoAdapter.RecoViewHolder> {

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
    private List<RecoBean> mRecoBeanList;

    public RecoAdapter(Context context, List<RecoBean> recoBeanList) {
        mContext = context;
        mRecoBeanList = recoBeanList;
    }

    @Override
    public RecoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reco, parent, false);
        return new RecoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecoViewHolder holder, int position) {
        /*RecoBean bean = mRecoBeanList.get(position);
        holder.mIvAvatar.setImageResource(R.drawable.ic_default_round_avatar);
        holder.mTvAuthor.setText(bean.getUserBean().getAuthor());
//        holder.mSfvVideo
        holder.mTvDigg.setText(bean.getInteractionBean().getDigg());
        holder.mTvBury.setText(bean.getInteractionBean().getBury());
        holder.mTvComment.setText(bean.getInteractionBean().getComment());
        holder.mTvShare.setText(bean.getInteractionBean().getShare());*/
    }

    @Override
    public int getItemCount() {
        return mRecoBeanList.size();
    }
}
