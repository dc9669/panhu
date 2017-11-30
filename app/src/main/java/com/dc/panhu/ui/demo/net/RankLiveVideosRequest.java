package com.dc.panhu.ui.demo.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import tv.xiaoka.base.bean.LiveBean;
import tv.xiaoka.base.bean.ResponseBean;
import tv.xiaoka.base.bean.ResponseDataBean;
import tv.xiaoka.base.network.BaseHttp;

/**
 *
 * Created by lvmeng on 2016/11/22.
 */
public abstract class RankLiveVideosRequest extends BaseHttp<ResponseDataBean<LiveBean>> {
    @Override
    public String getPath() {
        return "/live/api/get_rank_live_videos";
    }

    @Override
    public void onRequestResult(String result) {
        System.out.println(result);
        Type type = new TypeToken<ResponseBean<ResponseDataBean<LiveBean>>>() {
        }.getType();
        responseBean = new Gson().fromJson(result, type);
    }

    public RankLiveVideosRequest start() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("limit", "10");
        startRequest(map);
        return this;
    }
}
