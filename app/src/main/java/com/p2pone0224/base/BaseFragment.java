package com.p2pone0224.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.p2pone0224.utils.UIUtils;
import com.p2pone0224.view.LoadingPager;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/22 15:09
 * QQ：93226539
 * 作用：
 */

public abstract class BaseFragment extends Fragment {


    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutId() == 0) {
            TextView view = new TextView(getActivity());
            view.setText("你呀小白啊");
            return view;
        }
//
//        View view = View.inflate(
//                getActivity(),
//                getLayoutId(),
//                null
//        );
//        ButterKnife.bind(this,view);
//
//        initView();
//        initTitle();
//        initData();
//        initListener();

        /**
         *加载网络和无加载网络都会执行
         */
        loadingPager = new LoadingPager(getActivity()) {
            @Override
            public View getView() {
                View view = View.inflate(getActivity(),
                        BaseFragment.this.getLayoutId(), null);
                ButterKnife.bind(BaseFragment.this, view);
                return view;
            }

            /**
             * 加载网络的时候会调用
             * @param successView
             * @param json
             */
            @Override
            protected void setResult(View successView, String json) {
                //ButterKnife.bind(BaseFragment.this,successView);
                setContent(json);

                //保证在主线程中执行此方法
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                        initTitle();
                        initData();
                        initListener();
                    }
                });
            }

            /**
             *  加载网络的时候会调用
             * @return
             */
            @Override
            protected String getUrl() {
                return getChildUrl();
            }
        };

        return loadingPager;
    }

    public abstract String getChildUrl();

    /*
    * 连网的情况下 需要重写
    *
    * */
    public void setContent(String json) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getLayoutId() != 0) {
            //连网
            loadingPager.loadNet();
        }

        if (TextUtils.isEmpty(getChildUrl())){
            initTitle();
            initView();
            initListener();
            initData();
        }

    }

    public abstract void initTitle();

    /*
    * 重写
    * */
    public void initListener() {

    }

    public abstract void initData();

    /*
    * 可以重写
    *
    * */
    public void initView() {

    }

    public abstract int getLayoutId();
}
