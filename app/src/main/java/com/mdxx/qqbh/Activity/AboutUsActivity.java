package com.mdxx.qqbh.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mdxx.qqbh.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.tv_url)
    public void onClick() {
        String url = "http://www.taoqiuqiu.com";
        Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(urlIntent);
    }
}
