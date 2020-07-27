package com.example.bridgeandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

import com.example.bridgeandroid.MainActivity;
import com.example.bridgeandroid.R;

public class WebViewUtils {
    private Context mContext = null;
    private MainActivity mainActivity = null;

    public WebView webView = null;
    public WebSettings webSettings = null;

    public void init(final Context context, final MainActivity mainActivity) {
        this.mContext = context;
        this.mainActivity = mainActivity;

        webView = (WebView) this.mainActivity.findViewById(R.id.webview);
        // webView.clearCache(true);
        // webView.clearHistory();
        // webView.clearFormData();

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 缓存模式：
        // LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        // LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        // LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        // LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // 开启 DOM storage 功能
        webSettings.setDomStorageEnabled(true);


        // 如果访问的页面中要与Javascript交互，则 webview 必须设置支持 Javascript
        // 若加载的 html 里有 JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //加载网页链接
        webView.loadUrl("http://192.168.2.66:8080");


        // 重写此方法表明点击网页里面的链接还是在当前的 webview 里跳转，不跳到浏览器那边
        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                SelfUtils.log("onPageStarted", url);
                // webView.loadUrl("javascript:window.env='androidApp'");
                // webView.loadUrl("javascript:env='androidApp'");
                // webView.loadUrl("javascript:(function(){window.runenv='androidApp';})()");
            }
            public void onPageFinished(WebView view, String url) {
                SelfUtils.log("onPageFinished", url);
//                mainActivity.loadWebsiteOver();
            }
            public void onLoadResource(WebView view, String url) {
                // SelfUtils.log("onLoadResource", url);
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                SelfUtils.log("overrideUrlLoading", url);
                if (url == null) {
                    return false;
                }

                if (URLUtil.isNetworkUrl(url)) {
                    view.loadUrl(url);
                } else {
                    try {
                        mainActivity.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                SelfUtils.log("overrideUrlLoading", url);
                if (url == null) {
                    return false;
                }

                if (URLUtil.isNetworkUrl(url)) {
                    view.loadUrl(url);
                } else {
                    try {
                        mainActivity.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                return true;
            }
        });

        // 开启硬件加速
        this.mainActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // webSettings.setSupportMultipleWindows(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        // webView.setWebContentsDebuggingEnabled(true); // 允许 chrome 调试

        // 用于处理 js 被执行后的回调
//        webView.addJavascriptInterface(mainActivity, "androidJsInterface");
    }
}

