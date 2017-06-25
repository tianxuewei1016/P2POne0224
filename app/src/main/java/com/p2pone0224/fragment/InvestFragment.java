package com.p2pone0224.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.p2pone0224.R;
import com.p2pone0224.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：田学伟 on 2017/6/20 17:55
 * QQ：93226539
 * 作用：
 */

public class InvestFragment extends BaseFragment {

    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @Bind(R.id.vp_invest)
    ViewPager vpInvest;
    private List<BaseFragment> listFragment;

    @Override
    public String getChildUrl() {
        return "";
    }

    /**
     * json 需要注意 不连网的情况下 json是没有数据的
     *
     * @param json
     */
    @Override
    public void setContent(String json) {
        super.setContent(json);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        initViewPager();
        //设置默认选中的TextView
        setSelectTv(tvInvestAll);
    }

    /**
     * 设置选中的TextView的属性
     *
     * @param view
     */
    private void setSelectTv(View view) {
        setDefaultAll(tvInvestHot);
        setDefaultAll(tvInvestRecommend);
        setDefaultAll(tvInvestAll);
        TextView tv = (TextView) view;
        tv.setTextColor(Color.YELLOW);
        tv.setBackgroundColor(Color.BLUE);
    }

    /**
     * 恢复默认的TextView的属性
     *
     * @para view
     */
    private void setDefaultAll(View view) {
        TextView tv = (TextView) view;
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void initListener() {
        super.initListener();
        //ViewPager的监听
        vpInvest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 左右滑动调用这个方法
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                selectTV(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 根据位置切换不同的页面
     *
     * @param position
     */
    private void selectTV(int position) {
        switch (position) {
            case 0:
                setSelectTv(tvInvestAll);
                break;
            case 1:
                setSelectTv(tvInvestRecommend);
                break;
            case 2:
                setSelectTv(tvInvestHot);
                break;
        }
    }

    /**
     * 初始化fragment
     */
    private void initViewPager() {
        listFragment = new ArrayList<>();
        listFragment.add(new InvestAllFragment());
        listFragment.add(new InvestReFragment());
        listFragment.add(new InvesthotFragment());
        vpInvest.setAdapter(new Myadapter(getFragmentManager()));
    }

    /**
     * FragmentPagerAdapter
     * FragmentStatePagerAdapter
     * 面试题这两个有什么区别？
     * * FragmentPagerAdapter:该类内的每一个生成的 Fragment 都将保存在内存之中，
     * 因此适用于那些`相对静态的页，
     * 数量也比较少`的那种；如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，
     * 应该使用FragmentStatePagerAdapter
     * <p>
     * FragmentStatePagerAdapter:该 PagerAdapter 的实现将只保留当前页面，
     * 当页面离开视线后，就会被消除，释放其资源；而在页面需要显示时，
     * 生成新的页面(就像 ListView 的实现一样)。这么实现的好处就是当拥有大量的页面时，
     * 不必在内存中占用大量的内存。
     */
    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @OnClick({R.id.tv_invest_all, R.id.tv_invest_recommend, R.id.tv_invest_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_invest_all:
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_invest_recommend:
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot:
                vpInvest.setCurrentItem(2);
                break;
        }
    }
}
