package com.dc.panhu.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dc.panhu.ui.base.BaseActivity;
import com.dc.panhu.ui.view.IPostScriptAtView;
import com.dc.panhu.R;
import com.dc.panhu.ui.presenter.PostScriptAtPresenter;

import butterknife.Bind;

/**
 * @创建者 CSDN_LQR
 * @描述 附言界面
 */
public class PostScriptActivity extends BaseActivity<IPostScriptAtView, PostScriptAtPresenter> implements IPostScriptAtView {

    @Bind(R.id.btnToolbarSend)
    Button mBtnToolbarSend;

    @Bind(R.id.etMsg)
    EditText mEtMsg;
    @Bind(R.id.ibClear)
    ImageButton mIbClear;
    private String mUserId;

    @Override
    public void init() {
        mUserId = getIntent().getStringExtra("userId");
    }

    @Override
    public void initView() {
        mBtnToolbarSend.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(mUserId)) {
            finish();
        }
    }

    @Override
    public void initListener() {
        mIbClear.setOnClickListener(v -> mEtMsg.setText(""));
        mBtnToolbarSend.setOnClickListener(v -> mPresenter.addFriend(mUserId));
    }

    @Override
    protected PostScriptAtPresenter createPresenter() {
        return new PostScriptAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_postscript;
    }

    @Override
    public EditText getEtMsg() {
        return mEtMsg;
    }
}
