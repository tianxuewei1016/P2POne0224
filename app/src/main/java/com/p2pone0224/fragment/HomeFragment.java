package com.p2pone0224.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.p2pone0224.R;
import com.p2pone0224.base.BaseFragment;
import com.p2pone0224.bean.IndexBean;
import com.p2pone0224.common.AppNetConfig;
import com.p2pone0224.utils.HttpUtils;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/20 17:55
 * QQ：93226539
 * 作用：
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;


    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    public void initTitle() {
        baseTitle.setText("首页");
    }

    public void initListener() {

    }

    private List<String> list = new ArrayList<>();

    public void initData() {
        loadNet();
    }

    private void loadNet() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//                //解析数据
//                IndexBean indexBean = JSON.parseObject(content,IndexBean.class);
//
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//            }
//        });

        HttpUtils.getInstance().get(AppNetConfig.INDEX, new HttpUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String json) {
                IndexBean indexBean = JSON.parseObject(json, IndexBean.class);
                initBanner(indexBean);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void initBanner(IndexBean indexBean) {
        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();
        for (int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            list.add(AppNetConfig.BASE_URL + imaurl);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

