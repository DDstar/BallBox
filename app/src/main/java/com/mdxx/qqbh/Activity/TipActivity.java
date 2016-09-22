package com.mdxx.qqbh.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mdxx.qqbh.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TipActivity extends AppCompatActivity {

    @BindView(R.id.imageView8)
    ImageView imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView8)
    public void onClick() {
        finish();
    }
}
