package com.mdxx.qqbh.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mdxx.qqbh.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        mWebView.setWebViewClient(new CusClient());
        mWebView.loadUrl("http://pop.chinajszj.com/front/strategy/index/tid/9");
    }


    class CusClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }

}
