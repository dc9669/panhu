package com.dc.panhu.ui.radar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dc.panhu.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by Speedy on 2017/3/7.
 */

public class RadarPopupWindow extends PopupWindow implements ScanView{

    private Activity mActivity;

    private View contentView;

    private ImageView ivWave;
    private ImageView ivCirle;
    private ImageView ivRadarScan;
    private ObjectAnimator radarScanAnim;

    private FrameLayout mFrameLayout;

    private int width;

    private int height;

    private Random random = new Random();

    private LayoutInflater mLayoutInflater;

    public RadarPopupWindow(Activity mActivity) {
        this.mActivity = mActivity;
        initView();
    }


    private void initView() {
        mLayoutInflater = LayoutInflater.from(mActivity.getApplicationContext());
        contentView = mLayoutInflater.inflate(R.layout.radar_popup_window,null);
        setContentView(contentView);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        ivWave = (ImageView) contentView.findViewById(R.id.ivWave);
        ivCirle = (ImageView) contentView.findViewById(R.id.ivCirle);
        ivRadarScan = (ImageView) contentView.findViewById(R.id.ivRadarScan);

        int height  = mActivity.getWindow().getDecorView().getHeight();
        int width  = mActivity.getWindow().getDecorView().getWidth();
        int centerX = height/2;
        int centerY = width/2;
        int diagonal= (int) Math.sqrt(Math.pow(centerX,2)+ Math.pow(centerY,2))*2+10;//增加10个像素的延伸
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(diagonal,diagonal);
        ivRadarScan.setLayoutParams(layoutParams);
        ivRadarScan.setX((width-diagonal)/2);
        ivRadarScan.setY((height-diagonal)/2);

        radarScanAnim = ObjectAnimator.ofFloat(ivRadarScan,"rotation",0f,360f);
        radarScanAnim.setDuration(2000);
        radarScanAnim.setInterpolator(new LinearInterpolator());//not stop
        radarScanAnim.setRepeatCount(ObjectAnimator.INFINITE);//循环

        mFrameLayout = (FrameLayout) contentView.findViewById(R.id.match_container);
    }

    public void startScan() {
        ivRadarScan.setVisibility(View.VISIBLE);
        radarScanAnim.start();
    }





    @Override
    public void connectServerFailed(String msg) {

    }

    public void scanFinished(){
        radarScanAnim.end();
        ivRadarScan.setVisibility(View.GONE);
        final int count = mFrameLayout.getChildCount();
        if(0 == 0){
            Toast.makeText(mActivity,"未扫描到盘友",Toast.LENGTH_SHORT).show();
            dismiss();
            return;
        }
        for (int i = 0; i < count; i++) {
            final View view = mFrameLayout.getChildAt(i);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view,"alpha",1f,0f);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view,"translationX",view.getX(),width/2-view.getWidth()/2);
            ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view,"translationY",view.getY(),height/2 - view.getHeight()/2);
            ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(view,"scaleX",1f,0f);
            ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(view,"scaleY",1f,0f);
            ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(ivWave,"scaleX",1f,0f);
            ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(ivWave,"scaleY",1f,0f);

            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.playTogether(objectAnimator1,objectAnimator2,objectAnimator3,objectAnimator4,objectAnimator5,objectAnimator6,objectAnimator7);
            animatorSet.start();
        }

//        Observable.intervalRange(0L,1L,500L,1000L, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(@NonNull Long aLong) throws Exception {
//                startCirleAnim();
//            }
       //});
    }

    private void startCirleAnim() {
        ObjectAnimator shrinkXAnim = ObjectAnimator.ofFloat(ivCirle,"scaleX",1f,0.5f,30f);
        ObjectAnimator shrinkYAnim = ObjectAnimator.ofFloat(ivCirle,"scaleY",1f,0.5f,30f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.play(shrinkXAnim).with(shrinkYAnim);
        animatorSet.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        animatorSet.start();
    }













    @Override
    public void dismiss() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(ivCirle,"scaleX",1f,0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(ivCirle,"scaleY",1f,0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(contentView,"alpha",1f,0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1,animator2,animator3);
        animatorSet.setDuration(500);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
               RadarPopupWindow.super.dismiss();
            }
        });
        animatorSet.start();
    }


}
