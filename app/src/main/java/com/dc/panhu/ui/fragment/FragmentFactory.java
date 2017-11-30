package com.dc.panhu.ui.fragment;

import com.dc.panhu.ui.demo.YizhibFragment;

/**
 * @创建者 CSDN_LQR
 * @描述 主界面4个Fragment工厂
 */
public class FragmentFactory {

    static FragmentFactory mInstance;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private RecentMessageFragment mRecentMessageFragment;
    private ContactsFragment mContactsFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private MeFragment mMeFragment;
    private YizhibFragment MYizhibFragment;

    public RecentMessageFragment getRecentMessageFragment() {
        if (mRecentMessageFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mRecentMessageFragment == null) {
                    mRecentMessageFragment = new RecentMessageFragment();
                }
            }
        }
        return mRecentMessageFragment;
    }

    public ContactsFragment getContactsFragment() {
        if (mContactsFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mContactsFragment == null) {
                    mContactsFragment = new ContactsFragment();
                }
            }
        }
        return mContactsFragment;
    }

    public YizhibFragment getDiscoveryFragment() {//探索跳转主页
        if (MYizhibFragment == null) {
            synchronized (FragmentFactory.class) {
                if (MYizhibFragment == null) {
                    MYizhibFragment = new YizhibFragment();
                }
            }
        }
        return MYizhibFragment;
    }

    public MeFragment getMeFragment() {
        if (mMeFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                }
            }
        }
        return mMeFragment;
    }
}
