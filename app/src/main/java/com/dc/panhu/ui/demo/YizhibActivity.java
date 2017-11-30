package com.dc.panhu.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.dc.panhu.R;
import com.dc.panhu.ui.demo.net.RankLiveVideosRequest;

import tv.xiaoka.base.bean.LiveBean;
import tv.xiaoka.base.bean.MemberBean;
import tv.xiaoka.base.bean.ResponseDataBean;
import tv.xiaoka.base.recycler.OnItemClickListener;
import tv.xiaoka.base.view.UIToast;
import tv.xiaoka.play.activity.VideoPlayActivity;
import tv.xiaoka.user.AsyncListener;
import tv.xiaoka.user.OpenUserBean;
import tv.xiaoka.user.YZBHelper;

public class YizhibActivity extends AppCompatActivity {
    private LiveAdapter adapter;
    private RecyclerView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yizhibactivity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (RecyclerView) findViewById(android.R.id.list);
        adapter = new LiveAdapter();
        mListView.setAdapter(adapter);
        mListView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setOnItemClickListener(mListView, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                LiveBean bean = adapter.getItem(i);
                if (bean == null) {
                    return;
                }
                //进入直播间
                Intent intent = new Intent(YizhibActivity.this, VideoPlayActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });

        login();
    }


    /**
     * 登录
     */
    private void login() {
        final OpenUserBean bean = new OpenUserBean();
        bean.setXuid("1000");
        bean.setNickname("一直播DEMO测试");
        bean.setSex(OpenUserBean.Sex.UNKNOWN);
        bean.setWeiboOpenID("");
        bean.setXavatar("http://alcdn.img.xiaoka.tv/20160526/a2d/a2f/0/a2da2f445b156e42da5117f02f5d07c5.png");
        bean.setXphone("0");
        bean.setBirthday(0);
        bean.setSign("hWhXO/C2KT6rR0Ub2tVHlT70zkWvbwOxjkM03TOAKWddUW574IiLIcbvKIbD/G/hrKJYt4zNUF7AwWBYFfloLCnD4PEb2WxUfoQKr58rAQEsyXZq7p1olphIkqtNyPTZcMvq6+AfN/94T8mbyHmxfZbF9btfkNPbcwQUgDvEmVoHHf6L9JnYWGW3lkWtphp98rb0G21c6RgR2sW6IAU41N0hTCYuPEb5ShogTfExekdDPl5CX2Td7ttIxpuRh5j/8XF0FNWszN7twfEN56AJr7IP6xMflfD0Q+W5fYywpipJReCgcr1n26SslTaxzKgc8l3WDZIIqoYlV8iBxvBDkA==");

        new YZBHelper().login(bean, new AsyncListener<MemberBean>() {
            @Override
            public void onFinish(boolean isSuccess, String msg, MemberBean data) {
                if (isSuccess) {
                    System.out.println("登录成功");
                    getRankingLiveList();
                } else {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取直播列表
     */
    private void getRankingLiveList() {
        new RankLiveVideosRequest() {

            @Override
            public void onFinish(boolean isSuccess, String msg, ResponseDataBean<LiveBean> data) {
                if (isSuccess) {
                    adapter.addAll(data.getList());
                } else {
                    UIToast.show(YizhibActivity.this, msg);
                }

                adapter.notifyDataSetChanged();
                adapter.setCanLoading(false);
            }
        }.start();
    }
}
