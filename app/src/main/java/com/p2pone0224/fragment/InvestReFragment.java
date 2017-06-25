package com.p2pone0224.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.p2pone0224.R;
import com.p2pone0224.base.BaseFragment;
import com.p2pone0224.utils.UIUtils;
import com.p2pone0224.view.randomLayout.StellarMap;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/25 16:10
 * QQ：93226539
 * 作用：
 */

public class InvestReFragment extends BaseFragment {


    @Bind(R.id.stellar_map)
    StellarMap stellarMap;
    private String[] datas = new String[]{
            "新手福利计划", "财神道90天计划", "硅谷钱包计划",
            "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划",
            "大学老师购买车辆", "屌丝下海经商计划"
    };

    @Override
    public void initView() {
        super.initListener();

        stellarMap.setAdapter(new MyAdapter());
        //必须调用如下的两个方法，否则stellarMap不能显示数据
        //设置显示的数据在x轴、y轴方向上的稀疏度
        stellarMap.setRegularity(5, 7);
        //设置初始化显示的组别，以及是否需要使用动画
        stellarMap.setGroup(0, true);
        stellarMap.setInnerPadding(UIUtils.dp2px(10), UIUtils.dp2px(10),
                UIUtils.dp2px(10), UIUtils.dp2px(10));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyAdapter implements StellarMap.Adapter {
        /**
         * 有几个组
         *
         * @return
         */
        @Override
        public int getGroupCount() {
            //1.获取有多少个组
            //2.先判断有没有余数 如果有余数就加1 这样组数就对了
            int num = datas.length / 7;
            if (num % 7 != 0) {
                num += 1;
            }
            return num;
        }

        /**
         * 每组有多少数量  根据不同的组返回不同的数量
         *
         * @param group
         * @return
         */
        @Override
        public int getCount(int group) {
            if (datas.length % 7 == 0) {
                return 7;
            } else {
                if (group == datas.length / 7) {
                    return datas.length % 7;
                } else {
                    return 7;
                }
            }
        }

        /**
         * 返回view 根据不同的组返回不同的view
         *
         * @param group
         * @param position
         * @param convertView
         * @return
         */
        @Override
        public View getView(int group, int position, View convertView) {
            TextView textView = new TextView(getActivity());
            textView.setText(datas[group * 7 + position]);
            //产生随机颜色
            Random random = new Random();
            int red = random.nextInt(100) + 50;
            int green = random.nextInt(100) + 50;
            int blue = random.nextInt(100) + 50;

            textView.setTextColor(Color.rgb(red, green, blue));
            return textView;
        }

        /**
         * 预留方法不用实现
         *
         * @param group
         * @param degree
         * @return
         */
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        /**
         * 返回下一组的组号
         *
         * @param group
         * @param isZoomIn
         * @return
         */
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommand;
    }
}
