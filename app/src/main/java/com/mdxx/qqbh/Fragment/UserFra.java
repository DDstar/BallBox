package com.mdxx.qqbh.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mdxx.qqbh.Activity.AboutUsActivity;
import com.mdxx.qqbh.Activity.ChargeRecodActivity;
import com.mdxx.qqbh.Activity.CreditActivity;
import com.mdxx.qqbh.Activity.VideoActivity;
import com.mdxx.qqbh.Base.Contants;
import com.mdxx.qqbh.DataBean.ChargeBean;
import com.mdxx.qqbh.DataBean.MainUserMsgBean;
import com.mdxx.qqbh.DataBean.SignBean;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.SPControl;
import com.mdxx.qqbh.Utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFra extends Fragment {


    @BindView(com.mdxx.qqbh.R.id.tv_userid)
    TextView tvUserid;
    @BindView(com.mdxx.qqbh.R.id.tv_score)
    TextView tvScore;
    @BindView(com.mdxx.qqbh.R.id.btn_sign)
    Button btnSign;

    Map<String, Object> parmap = new HashMap<>();

    public UserFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(com.mdxx.qqbh.R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        registUser();
        return view;
    }

    private void registUser() {
        String Imei = ((TelephonyManager) getActivity().getSystemService(getActivity().TELEPHONY_SERVICE))
                .getDeviceId();
        parmap.clear();
        parmap.put("imei", Imei);
        BaseRequest.xutilsPostData("userstatus", parmap, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
                MainUserMsgBean.FflistBean fflistBean = new Gson().fromJson(s, MainUserMsgBean.class).getFflist();
                SPControl.saveUserID(getActivity(), fflistBean.getUserid());
                tvUserid.setText(fflistBean.getUserid());
                tvScore.setText(fflistBean.getMoney());
                int isqd = fflistBean.getIsqd();
                if (isqd == 1) {
                    btnSign.setEnabled(false);
                    btnSign.setText("已签到");
                }
            }

            @Override
            public void onError(String s) {

            }
        });
    }

    @OnClick({com.mdxx.qqbh.R.id.charge, com.mdxx.qqbh.R.id.record, com.mdxx.qqbh.R.id.video,
            com.mdxx.qqbh.R.id.share, com.mdxx.qqbh.R.id.qqgroup, com.mdxx.qqbh.R.id.btn_sign
            , R.id.tv_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case com.mdxx.qqbh.R.id.charge:
                parmap.clear();
                parmap.put("userid", SPControl.getString(getActivity(), Contants.USER_ID_KEY));
                BaseRequest.xutilsPostData("duibaurl", parmap, new ResultCallback() {
                    @Override
                    public void onSuccess(String s) {
                        ChargeBean chargeBean = new Gson().fromJson(s, ChargeBean.class);
                        if (chargeBean.getCode() == 1) {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), CreditActivity.class);
                            intent.putExtra("navColor", "#ff0000");    //配置导航条的背景颜色，请用#ffffff长格式。
                            intent.putExtra("titleColor", "#ffffff");    //配置导航条标题的颜色，请用#ffffff长格式。
                            intent.putExtra("url", chargeBean.getFflist());    //配置自动登陆地址，每次需服务端动态生成。
                            startActivity(intent);
                        } else {
                            ToastUtil.showMessage(getActivity(), chargeBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(String s) {

                    }
                });
                break;
            case com.mdxx.qqbh.R.id.record:
                startActivity(new Intent(getActivity(), ChargeRecodActivity.class));
                break;
            case com.mdxx.qqbh.R.id.video:
                startActivity(new Intent(getActivity(), VideoActivity.class));
                break;
            case com.mdxx.qqbh.R.id.share:
                break;
            case R.id.tv_about_us:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case com.mdxx.qqbh.R.id.qqgroup:
                break;
            case com.mdxx.qqbh.R.id.btn_sign:
                parmap.clear();
                parmap.put("userid", SPControl.getString(getActivity(), Contants.USER_ID_KEY));
                BaseRequest.xutilsPostData("qian", parmap, new ResultCallback() {
                    @Override
                    public void onSuccess(String s) {
                        SignBean signBean = new Gson().fromJson(s, SignBean.class);
                        if (signBean.getCode() == 1) {
                            tvScore.setText(signBean.getFflist());
                        }
                        ToastUtil.showMessage(getActivity(), signBean.getMsg());
                    }

                    @Override
                    public void onError(String s) {

                    }
                });
                break;
        }
    }

}