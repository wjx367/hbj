package com.doubanapp.hbj.douban.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.widget.RecyclerView.*;

/**
 * BaseFragment
 * Created by Administrator
 * time: 2017-04-04.
 */

public abstract class BaseFragment extends LazyFragment implements View.OnClickListener,
        MainActivity.FloatingClickedListener {

    private static final String TAG = "BaseFragment";
    @BindView(R.id.rc_base)
    RecyclerView rc_base;
    @BindView(R.id.pb_loading)
    ProgressBar pb_loading;
    @BindView(R.id.rl_error)
    RelativeLayout rl_error;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Floating监听
        mContext.setFloatingClickedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout relativeLayout = new RelativeLayout(MyUtils.getContext());
        View view = inflater.inflate(R.layout.fg_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        relativeLayout.addView(view);
        if (initChildView() != null)
            relativeLayout.addView(initChildView());
        return relativeLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_error.setOnClickListener(this);
        rc_base.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //获取到焦点就设置floating监听
                if (hasFocus)
                    mContext.setFloatingClickedListener(BaseFragment.this);
            }
        });

    }

    protected abstract View initChildView();//子类添加不同的view,没有则为null

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
