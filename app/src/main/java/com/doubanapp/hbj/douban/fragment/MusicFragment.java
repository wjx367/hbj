package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.doubanapp.hbj.douban.IView.IMusicFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.presenter.MusicFragmentPresenter;
import com.doubanapp.hbj.douban.utils.MyUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 音乐
 * Created by Administrator on 2017/3/17 0017.
 */
public class MusicFragment extends BaseFragment implements IMusicFragmentView {

    private static final String TAG = "MusicFragment";
    private MultiTypeAdapter adapter;
    private MusicFragmentPresenter musicFragmentPresenter;


    public static MusicFragment newsInstance(int pos) {
        MusicFragment fragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initChildView() {
        //Presenter
        musicFragmentPresenter = new MusicFragmentPresenter(mContext, this);
        musicFragmentPresenter.doRegisterMultitypeItem();
        musicFragmentPresenter.doInitLayoutManager();
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        musicFragmentPresenter.doConnectHttp();
    }

    @Override
    protected synchronized void lazyLoad() {
    }

    /*
    * multitype*/
    @Override
    public void onRegisterMultitypeItem(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    /*
    * set layoutmanager*/
    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_base.setLayoutManager(manager);
    }

    /*
    * 开始请求*/
    @Override
    public void onStartVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    /*
    * 请求失败*/
    @Override
    public void onErrorVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    /*
    * 请求结束*/
    @Override
    public void onCompletedVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onSetAdapter() {
        rc_base.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_error:
                musicFragmentPresenter.doConnectHttp();
                break;
            default:
        }
    }

    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
    }
}
