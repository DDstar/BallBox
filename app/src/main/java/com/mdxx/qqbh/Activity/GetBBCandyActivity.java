package com.mdxx.qqbh.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.socks.library.KLog;

import com.mdxx.qqbh.Base.BaseActivity;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetBBCandyActivity extends BaseActivity {

    @BindView(R.id.et_candy_url)
    EditText etCandyUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bbcandy);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_take_candy, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_take_candy:
                if (TextUtils.isEmpty(etCandyUrl.getText().toString())) {
                    ToastUtil.showMessage(this, "先输入链接");
                    return;
                }
                BaseRequest.xutilsGetData(etCandyUrl.getText().toString(), null, new ResultCallback() {
                    @Override
                    public void onSuccess(String s) {
                        KLog.e(s);
                    }

                    @Override
                    public void onError(String s) {
                        KLog.e(s);
                    }
                });

                break;
            case R.id.btn_more:
                removeAllActivity();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("flag",2);
                startActivity(intent);

                break;
        }
    }
}
