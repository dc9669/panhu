package com.dc.panhu.ui.fragment;

import android.content.Intent;

import com.dc.panhu.ui.demo.YizhibActivity;
import com.lqr.optionitemview.OptionItemView;
import com.dc.panhu.R;
import com.dc.panhu.app.AppConst;
import com.dc.panhu.ui.activity.MainActivity;
import com.dc.panhu.ui.activity.ScanActivity;
import com.dc.panhu.ui.base.BaseFragment;
import com.dc.panhu.ui.presenter.DiscoveryFgPresenter;
import com.dc.panhu.ui.view.IDiscoveryFgView;

import butterknife.Bind;

/**
 * @创建者 CSDN_LQR
 * @描述 探索界面
 */
public class DiscoveryFragment extends BaseFragment<IDiscoveryFgView, DiscoveryFgPresenter> implements IDiscoveryFgView {

    @Bind(R.id.oivScan)
    OptionItemView mOivScan;
    @Bind(R.id.oivShop)
    OptionItemView mOivShop;
    @Bind(R.id.oivGame)
    OptionItemView mOivGame;

    @Override
    public void initListener() {
        mOivScan.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(YizhibActivity.class));
        mOivShop.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToWebViewActivity(AppConst.WeChatUrl.JD));
        mOivGame.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToWebViewActivity(AppConst.WeChatUrl.GAME));
    }

    @Override
    protected DiscoveryFgPresenter createPresenter() {
        return new DiscoveryFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_discovery;
    }
}
