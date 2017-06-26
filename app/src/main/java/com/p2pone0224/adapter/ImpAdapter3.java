package com.p2pone0224.adapter;

import android.content.Context;

import com.p2pone0224.base.ProductAdapter3;
import com.p2pone0224.holder.BaseHolder;
import com.p2pone0224.holder.ProductHolder;

import java.util.List;

/**
 * 作者：田学伟 on 2017/6/25 17:25
 * QQ：93226539
 * 作用：
 */

public class ImpAdapter3 extends ProductAdapter3{

    public ImpAdapter3(Context context, List list) {
        super(context, list);
    }

    @Override
    protected BaseHolder getViewHolder() {
        return new ProductHolder();
    }
}
