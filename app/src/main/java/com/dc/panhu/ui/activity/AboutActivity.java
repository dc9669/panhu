package com.dc.panhu.ui.activity;

import com.dc.panhu.R;
import com.dc.panhu.ui.base.BaseActivity;
import com.dc.panhu.ui.base.BasePresenter;

/**
 * @创建者 CSDN_LQR
 * @描述 关于界面
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }
}
