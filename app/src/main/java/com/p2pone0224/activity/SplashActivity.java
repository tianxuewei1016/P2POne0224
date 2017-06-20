package com.p2pone0224.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p2pone0224.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isLogin()) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                //清除动画
                ivWelcomeIcon.clearAnimation();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivWelcomeIcon.startAnimation(animation);
    }

    public boolean isLogin() {

        return true;
    }

    private void initData() {

    }

    private void initView() {
        splashTvVersion.setText(getVersionCode());
    }

    /**
     * 获取版本号
     *
     * @return
     */
    private String getVersionCode() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            //versionCode应用市场用来
            int versionCode = info.versionCode;
            //当前的版本号
            String versionName = info.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "3";
    }
}
