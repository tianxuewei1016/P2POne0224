package com.p2pone0224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.p2pone0224.R;
import com.p2pone0224.base.ProductAdapter1;
import com.p2pone0224.bean.ProductBean;
import com.p2pone0224.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/25 17:34
 * QQ：93226539
 * 作用：
 */

public class ImpAdapter1 extends ProductAdapter1<ProductBean.DataBean> {
    public ImpAdapter1(Context context, List<ProductBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public View getChildView(int position, View convertView, ViewGroup parent, ProductBean.DataBean dataBean) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.product_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.pName.setText(dataBean.getName());
        return convertView;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
