package com.p2pone0224.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.p2pone0224.R;
import com.p2pone0224.adapter.ImpAdapter3;
import com.p2pone0224.base.BaseFragment;
import com.p2pone0224.bean.ProductBean;
import com.p2pone0224.common.AppNetConfig;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/6/25 16:10
 * QQ：93226539
 * 作用：
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.lv)
    ListView lv;

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    public void setContent(String json) {
        super.setContent(json);
        ProductBean productBean = JSON.parseObject(json, ProductBean.class);
        lv.setAdapter(new ImpAdapter3(getActivity(),productBean.getData()));
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }
}
