package com.dc.panhu.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.dc.panhu.ui.demo.ContributionActivity;
import com.dc.panhu.ui.demo.PayActivity;

import tv.xiaoka.base.bean.LiveBean;
import tv.xiaoka.base.bean.LiveShareBean;
import tv.xiaoka.base.bean.MemberBean;

/**
 * 跳转工具类
 * Created by lvmeng on 2016/11/22.
 */

public class JumpAction implements tv.xiaoka.base.protocol.JumpAction {
    @Override
    public void gotoPersonalInfoActivity(Context context, MemberBean memberBean) {

    }

    /**
     * 跳支付
     * @param context
     */
    @Override
    public void gotoRecharge(Context context) {
        Intent intent = new Intent(context, PayActivity.class);
        context.startActivity(intent);
    }

    /**
     * 分享
     * @param liveShareBean 分享出去的连接、图片信息在这里面
     */
    @Override
    public void share(Context context, LiveBean liveBean, LiveShareBean liveShareBean) {
        System.out.println(liveShareBean.getTitle());
        System.out.println(liveShareBean.getTargetUrl());
    }

    /**
     * 跳守护排行榜
     */
    @Override
    public void gotoContributionFragment(Context context, long memberId, String name, String scid) {
        Intent intent = new Intent(context, ContributionActivity.class);
        intent.putExtra("memberId", memberId);
        intent.putExtra("name", name);
        intent.putExtra("scid", scid);
        context.startActivity(intent);
    }

    @Override
    public void gotoLogin(Activity activity, int i) {

    }
}
