package com.p2pone0224.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.p2pone0224.holder.BaseHolder;

import java.util.List;

/**
 * 作者：田学伟 on 2017/6/25 17:23
 * QQ：93226539
 * 作用：
 */

public abstract class ProductAdapter3<T> extends BaseAdapter {

    public Context context;
    public List<T> list;

    public ProductAdapter3(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 优化三步
         * 第一步 抽出initView
         * 第二步 抽出setTag
         * 第三步 抽出设置数据
         */
        BaseHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = getViewHolder();
        } else {
            viewHolder = (BaseHolder) convertView.getTag();
        }
        viewHolder.setData(list.get(position));
        return viewHolder.getView();
    }

    protected abstract BaseHolder getViewHolder();
}

