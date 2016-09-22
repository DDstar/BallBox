package com.mdxx.qqbh.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mdxx.qqbh.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initWebView();
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadUrl("http://pop.chinajszj.com/front/strategy/index/tid/9");
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
    }


}
