package com.lga.punster.reco;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lga.punster.BaseFragment;
import com.lga.punster.R;
import com.lga.punster.reco.bean.RecoBean;

import java.util.List;

/**
 * Created by Jay on 2017/5/19.
 */

public class RecoFragment extends BaseFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private SwipeRefreshLayout mSrlLoad;
    private RecyclerView mRv;
    private List<RecoBean> mRecoBeanList;
    private RecoAdapter mAdapter;

    public RecoFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecoFragment newInstance(int sectionNumber) {
        RecoFragment fragment = new RecoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("kelly", "RecoFragment onCreate " + getUserVisibleHint());
        View rootView = inflater.inflate(R.layout.fragment_punster, container, false);
//        initView(rootView);
        return rootView;
    }

    /*private void initView(View v) {
        mSrlLoad = (SwipeRefreshLayout) v.findViewById(R.id.srl_load);
        mSrlLoad.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i = 10; i < 20; i++) {
                    RecoBean bean = new RecoBean();
                    UserBean userBean = new UserBean();
                    userBean.setAvatar(null);
                    userBean.setAuthor(String.valueOf(i));
                    bean.setUserBean(userBean);
                    bean.setVideoUrl(null);
                    InteractionBean interactionBean = new InteractionBean();
                    interactionBean.setDigg(String.valueOf(2000 + i));
                    interactionBean.setBury(String.valueOf(1000 - i));
                    interactionBean.setComment(String.valueOf(100 + i));
                    interactionBean.setShare(String.valueOf(100 - i));
                    bean.setInteractionBean(interactionBean);
                    mRecoBeanList.add(bean);
                }
                mSrlLoad.setRefreshing(false);
                mRv.scrollToPosition(mRecoBeanList.size() - 1);
            }
        });

        mRv = (RecyclerView) v.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);
        mRv.setLayoutManager(manager);
        mRecoBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RecoBean bean = new RecoBean();
            UserBean userBean = new UserBean();
            userBean.setAvatar(null);
            userBean.setAuthor(String.valueOf(i));
            bean.setUserBean(userBean);
            bean.setVideoUrl(null);
            InteractionBean interactionBean = new InteractionBean();
            interactionBean.setDigg(String.valueOf(2000 + i));
            interactionBean.setBury(String.valueOf(1000 - i));
            interactionBean.setComment(String.valueOf(100 + i));
            interactionBean.setShare(String.valueOf(100 - i));
            bean.setInteractionBean(interactionBean);
            mRecoBeanList.add(bean);
        }
        mAdapter = new RecoAdapter(getContext(), mRecoBeanList);
        mRv.setAdapter(mAdapter);
    }*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("kelly", "RecoFragment " + isVisibleToUser);
    }
}
