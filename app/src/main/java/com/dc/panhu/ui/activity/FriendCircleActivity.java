package com.dc.panhu.ui.activity;

import com.dc.panhu.ui.base.BaseActivity;
import com.dc.panhu.ui.presenter.FriendCircleAtPresenter;
import com.dc.panhu.ui.view.IFriendCircleAtView;

/**
 * @创建者 CSDN_LQR
 * @描述 朋友圈
 */
public class FriendCircleActivity extends BaseActivity<IFriendCircleAtView, FriendCircleAtPresenter> implements IFriendCircleAtView {

    @Override
    protected FriendCircleAtPresenter createPresenter() {
        return new FriendCircleAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }
}
