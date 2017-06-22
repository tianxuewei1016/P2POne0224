package com.p2pone0224.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 作者：田学伟 on 2017/6/22 13:18
 * QQ：93226539
 * 作用：
 */

public class HttpUtils {

    private AsyncHttpClient httpClient;

    private HttpUtils() {
        httpClient = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance() {
        return httpUtils;
    }

    public void get(String url, final OnHttpClickListener onHttpClickListener) {
        httpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                if (onHttpClickListener != null) {
                    onHttpClickListener.onSuccess(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (onHttpClickListener != null) {
                    onHttpClickListener.onFailure(content);
                }
            }
        });
    }

    public interface OnHttpClickListener {
        void onSuccess(String json);

        void onFailure(String message);
    }
}
