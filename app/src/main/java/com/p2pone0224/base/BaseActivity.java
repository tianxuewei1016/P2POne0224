package com.p2pone0224.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.p2pone0224.bean.LoginBean;
import com.p2pone0224.common.AppManager;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/26 14:13
 * QQ：93226539
 * 作用：Activity的公共类
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String LOGIN_BNEN = "login_bnen";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);

        //初始化控件
        initView();
        //初始化数据
        initData();
        //事件监听
        initListener();
        //初始化标题
        initTitle();
    }

    public void initTitle() {

    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initView();

    public abstract int getLayoutId();

    //初始化对象
    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 存储用户信息
     *
     * @return
     */
    private String spName = "loginbean";

    public void saveUser(LoginBean bean) {
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name", bean.getName());
        edit.putString("imageurl", bean.getImageurl());
        edit.putString("iscredit", bean.getIscredit());
        edit.putString("phone", bean.getPhone());
        edit.commit();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public LoginBean getUser() {
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        LoginBean bean = new LoginBean();
        bean.setName(sp.getString("name", "admin"));
        bean.setImageurl(sp.getString("imageurl", ""));
        bean.setIscredit(sp.getString("iscredit", ""));
        bean.setPhone(sp.getString("phone", ""));
        return bean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
