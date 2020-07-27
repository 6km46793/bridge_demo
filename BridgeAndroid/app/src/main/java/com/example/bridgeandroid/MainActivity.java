package com.example.bridgeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.example.bridgeandroid.utils.SelfUtils;
import com.example.bridgeandroid.utils.WebViewUtils;


public class MainActivity extends AppCompatActivity {
    public WebViewUtils webViewUtils = null;
    private Context mContext = null;
    private MainActivity mActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();
        mActivity = this;

        // webView 相关
        webViewUtils = new WebViewUtils();
        webViewUtils.init(this, this);
    }

    public void loadWebsiteOver() {
        SelfUtils.log("mainActivity", "loadWebsiteOver");
    }

    // ****** js接口 start ******
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void playRewardedAd(String msg) {
        SelfUtils.log("jsAd", msg);
    }
}
