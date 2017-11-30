package com.dc.panhu.ui.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dc.panhu.R;

import tv.xiaoka.play.fragment.ContributionFragment;

public class ContributionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        ContributionFragment fragment = new ContributionFragment();
        fragment.setMemberID(getIntent().getLongExtra("memberId",0));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.commitAllowingStateLoss();
    }
}
