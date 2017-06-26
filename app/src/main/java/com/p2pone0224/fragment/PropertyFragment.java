package com.p2pone0224.fragment;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p2pone0224.R;
import com.p2pone0224.base.BaseFragment;
import com.p2pone0224.common.AppNetConfig;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * 作者：田学伟 on 2017/6/20 17:56
 * QQ：93226539
 * 作用：
 */

public class PropertyFragment extends BaseFragment {

    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL + "images/tx.png")
                .transform(new CropCircleTransformation())
                .into(ivMeIcon);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }
}

