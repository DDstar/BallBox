package com.mdxx.qqbh.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.mdxx.qqbh.Base.BaseActivity;
import com.mdxx.qqbh.Base.Contants;
import com.mdxx.qqbh.DataBean.ChargeBean;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.Fragment.MainFra;
import com.mdxx.qqbh.Fragment.UserFra;
import com.mdxx.qqbh.Fragment.WorkFra;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.SPControl;
import com.mdxx.qqbh.Utils.ToastUtil;
import com.pgyersdk.update.PgyUpdateManager;

import net.youmi.android.AdManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rb_work)
    RadioButton rbWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContainer();
        initTab();
        initYMSDK();
        PgyUpdateManager.register(this);//开启版本检查
//        openUMPush();
        int flag = getIntent().getIntExtra("flag", -1);
        if (flag == 2) {
            go2work();
        }
    }


    public void go2work() {
        rbWork.setChecked(true);
    }

    private void initYMSDK() {
        AdManager.getInstance(this).init("63294aa964ffa1a9", "c0bb43b0cee2cdaf", false, true);
    }


    private void initContainer() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFra()).commit();
    }

    private void initTab() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main:
                        fragment = new MainFra();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        break;
                    case R.id.rb_work:
                        fragment = new WorkFra();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        break;
                    case R.id.rb_charge:
                        parmap.clear();
                        parmap.put("userid", SPControl.getString(MainActivity.this, Contants.USER_ID_KEY));
                        BaseRequest.xutilsPostData("duibaurl", parmap, new ResultCallback() {
                            @Override
                            public void onSuccess(String s) {
                                ChargeBean chargeBean = new Gson().fromJson(s, ChargeBean.class);
                                if (chargeBean.getCode() == 1) {
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this, CreditActivity.class);
                                    intent.putExtra("navColor", "#ff0000");    //配置导航条的背景颜色，请用#ffffff长格式。
                                    intent.putExtra("titleColor", "#ffffff");    //配置导航条标题的颜色，请用#ffffff长格式。
                                    intent.putExtra("url", chargeBean.getFflist());    //配置自动登陆地址，每次需服务端动态生成。
                                    startActivity(intent);
                                } else {
                                    ToastUtil.showMessage(MainActivity.this, chargeBean.getMsg());
                                }
                            }

                            @Override
                            public void onError(String s) {

                            }
                        });

                        break;
                    case R.id.rb_user:
                        fragment = new UserFra();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        break;
                }

            }
        });
    }
}
