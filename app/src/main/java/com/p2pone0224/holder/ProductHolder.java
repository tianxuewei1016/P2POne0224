package com.p2pone0224.holder;

import android.view.View;
import android.widget.TextView;

import com.p2pone0224.R;
import com.p2pone0224.bean.ProductBean;
import com.p2pone0224.utils.UIUtils;
import com.p2pone0224.view.ProgressView;

import butterknife.Bind;

/**
 * 作者：田学伟 on 2017/6/25 17:17
 * QQ：93226539
 * 作用：
 */

public class ProductHolder extends BaseHolder<ProductBean.DataBean> {
    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    ProgressView pProgresss;

    @Override
    public void setContent(ProductBean.DataBean dataBean) {

    }

    @Override
    public View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.product_item, null);
    }
}
