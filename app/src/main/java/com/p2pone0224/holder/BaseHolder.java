package com.p2pone0224.holder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/25 17:17
 * QQ：93226539
 * 作用：
 */

public abstract class BaseHolder<T> {
    private final View rootView;
    private T t;

    public BaseHolder() {
        rootView = initView();
        ButterKnife.bind(this, rootView);
        rootView.setTag(this);
    }

    public void setData(T t) {
        this.t = t;
        setContent(t);
    }

    public View getView() {
        return rootView;
    }

    /**
     * 创建布局,设置数据
     *
     * @param t
     */
    public abstract void setContent(T t);

    public abstract View initView();
}
