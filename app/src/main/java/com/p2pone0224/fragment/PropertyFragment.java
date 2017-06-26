package com.p2pone0224.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p2pone0224.R;
import com.p2pone0224.activity.IconSettingsActivity;
import com.p2pone0224.activity.MainActivity;
import com.p2pone0224.base.BaseFragment;
import com.p2pone0224.bean.LoginBean;
import com.p2pone0224.common.AppNetConfig;
import com.p2pone0224.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;

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
    public void initListener() {
        super.initListener();
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), IconSettingsActivity.class));
            }
        });
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL + "images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {

                        return BitmapUtils.getBitmap(bitmap);
                    }

                    @Override
                    public String key() {
                        return "CropCircleTransformation()";
                    }
                })
                .into(ivMeIcon);
        MainActivity mainActivity = (MainActivity) getActivity();
        LoginBean user = mainActivity.getUser();
        //设置用户名
        tvMeName.setText("周杰伦");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }
}

