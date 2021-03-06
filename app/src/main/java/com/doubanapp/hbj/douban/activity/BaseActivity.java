package com.doubanapp.hbj.douban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.doubanapp.hbj.douban.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class BaseActivity extends SwipeBackActivity {

    /*
    开启新activity的动画
   */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_open_enter_anim, 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //设置滑动关闭
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

    }

    /*
    关闭activity的动画
    */
    @Override
    public void finish() {
        super.finish();
        if (!(this instanceof MainActivity)) {
            //不是主页,设置动画
            overridePendingTransition(0, R.anim.activity_close_exit_anim);
        }
    }
}
