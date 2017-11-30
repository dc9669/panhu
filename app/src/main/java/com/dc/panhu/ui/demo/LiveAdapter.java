package com.dc.panhu.ui.demo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dc.panhu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import tv.xiaoka.base.bean.LiveBean;
import tv.xiaoka.base.recycler.BaseAdapter;
import tv.xiaoka.base.util.FrescoUtil;

/**
 * Created by lvmeng on 2016/11/22.
 */

public class LiveAdapter extends BaseAdapter<LiveBean, LiveAdapter.LiveHolder> {

    class LiveHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView photoIV;


        public LiveHolder(final View itemView) {
            super(itemView);
            photoIV = (SimpleDraweeView) itemView.findViewById(R.id.photo_iv);
            photoIV.setHierarchy(new FrescoUtil().createVideoCoverBuilder(itemView.getResources()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(LiveHolder.this, v);
                }
            });
        }
    }

    @Override
    public LiveHolder onCreateItemViewHolder(ViewGroup parent) {
        View v = View.inflate(parent.getContext(), R.layout.item_main, null);
        return new LiveHolder(v);
    }

    @Override
    public void onBindItemViewHolder(LiveHolder liveHolder, int i) {
        liveHolder.photoIV.setImageURI(getItem(i).getCovers().getB());
    }


}
