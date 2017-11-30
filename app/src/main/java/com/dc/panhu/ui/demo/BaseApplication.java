package com.dc.panhu.ui.demo;


import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.dc.panhu.ui.demo.util.FrescoConfig;

import tv.xiaoka.base.network.BaseDateRequest;
import tv.xiaoka.base.network.BaseHttpRequest;
import tv.xiaoka.base.util.UserDefaults;

/**
 * Created by lvmeng on 2016/11/21.
 */

public class BaseApplication extends MultiDexApplication {

    static {
        System.loadLibrary("xiaoka");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UserDefaults.init(getApplicationContext());
        BaseDateRequest.init(this);
        initFresco(getApplicationContext());
        BaseHttpRequest.BASE_PROTOCOL = "http://test.";
    }

    protected void initFresco(Context context) {
        //TODO: Fresco根据自己的情况初始化，如果之前没有使用，可以参考以下示例代码
        new FrescoConfig().initFrescoConfig(context);
    }
}
