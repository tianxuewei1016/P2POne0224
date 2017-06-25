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

    private HttpUtils(){
        httpClient = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance(){
        return httpUtils;
    }


    private OnHttpClientListener onHttpClientListener;
    public void get(String url, final OnHttpClientListener onHttpClientListener){

        this.onHttpClientListener = onHttpClientListener;
        httpClient.get(url,handler);
    }


    AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, String content) {
            super.onSuccess(statusCode, content);

            if (onHttpClientListener != null){
                onHttpClientListener.onSuccess(content);
            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);
            if (onHttpClientListener != null){
                onHttpClientListener.onFailure(content);
            }

        }
    };

//    public void post(String url, Map<String,String> map,
//                     final OnHttpClientListener onHttpClientListener){
//
//        RequestParams params = new RequestParams();
//
//        httpClient.post(url,params,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//
//                if (onHttpClientListener != null){
//                    onHttpClientListener.onSuccess(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                if (onHttpClientListener != null){
//                    onHttpClientListener.onFailure(content);
//                }
//            }
//        });
//    }


    public interface OnHttpClientListener{
        void onSuccess(String json);
        void onFailure(String message);
    }


}