package com.dc.panhu.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dc.panhu.api.ApiRetrofit;
import com.dc.panhu.app.AppConst;
import com.dc.panhu.db.DBManager;
import com.dc.panhu.db.model.Groups;
import com.dc.panhu.manager.BroadcastManager;
import com.dc.panhu.ui.base.BaseActivity;
import com.dc.panhu.ui.base.BasePresenter;
import com.dc.panhu.util.LogUtils;
import com.dc.panhu.util.UIUtils;
import com.dc.panhu.R;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @创建者 CSDN_LQR
 * @描述 设置群名称界面
 */
public class SetGroupNameActivity extends BaseActivity {

    private String mGroupId;

    @Bind(R.id.btnToolbarSend)
    Button mBtnToolbarSend;
    @Bind(R.id.etName)
    EditText mEtName;

    @Override
    public void init() {
        mGroupId = getIntent().getStringExtra("groupId");
    }

    @Override
    public void initView() {
        if (TextUtils.isEmpty(mGroupId)) {
            finish();
            return;
        }
        mBtnToolbarSend.setVisibility(View.VISIBLE);
        mBtnToolbarSend.setText(UIUtils.getString(R.string.save));
    }

    @Override
    public void initData() {
        Observable.just(DBManager.getInstance().getGroupsById(mGroupId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(groups -> {
                    if (groups != null) {
                        mEtName.setText(groups.getName());
                        mEtName.setSelection(groups.getName().length());
                        mBtnToolbarSend.setEnabled(groups.getName().length() > 0);
                    }
                });
    }

    @Override
    public void initListener() {
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBtnToolbarSend.setEnabled(mEtName.getText().toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBtnToolbarSend.setOnClickListener(v -> {
            String groupName = mEtName.getText().toString().trim();
            if (!TextUtils.isEmpty(groupName)) {
                showWaitingDialog(UIUtils.getString(R.string.please_wait));
                ApiRetrofit.getInstance().setGroupName(mGroupId, groupName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(setGroupNameResponse -> {
                            if (setGroupNameResponse != null && setGroupNameResponse.getCode() == 200) {
                                Groups groups = DBManager.getInstance().getGroupsById(mGroupId);
                                if (groups != null) {
                                    groups.setName(groupName);
                                    groups.saveOrUpdate("groupid=?", groups.getGroupId());
                                }
                                BroadcastManager.getInstance(SetGroupNameActivity.this).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
                                BroadcastManager.getInstance(SetGroupNameActivity.this).sendBroadcast(AppConst.UPDATE_CURRENT_SESSION_NAME);
                                UIUtils.showToast(UIUtils.getString(R.string.set_success));
                                Intent data = new Intent();
                                data.putExtra("group_name", groupName);
                                setResult(RESULT_OK, data);
                                hideWaitingDialog();
                                finish();
                            } else {
                                hideWaitingDialog();
                                UIUtils.showToast(UIUtils.getString(R.string.set_fail));
                            }
                        }, this::loadError);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_group_name_set;
    }

    private void loadError(Throwable throwable) {
        LogUtils.sf(throwable.getLocalizedMessage());
        hideWaitingDialog();
        UIUtils.showToast(UIUtils.getString(R.string.set_fail));
    }
}
