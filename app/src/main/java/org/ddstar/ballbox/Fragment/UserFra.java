package org.ddstar.ballbox.Fragment;


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

import org.ddstar.ballbox.Activity.ChargeRecodActivity;
import org.ddstar.ballbox.Activity.CreditActivity;
import org.ddstar.ballbox.Base.Contants;
import org.ddstar.ballbox.DataBean.ChargeBean;
import org.ddstar.ballbox.DataBean.MainUserMsgBean;
import org.ddstar.ballbox.DataBean.SignBean;
import org.ddstar.ballbox.DataRequest.BaseRequest;
import org.ddstar.ballbox.DataRequest.ResultCallback;
import org.ddstar.ballbox.R;
import org.ddstar.ballbox.Utils.SPControl;
import org.ddstar.ballbox.Utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFra extends Fragment {


    @BindView(R.id.tv_userid)
    TextView tvUserid;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.btn_sign)
    Button btnSign;

    Map<String,Object> parmap = new HashMap<>();
    public UserFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
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

    @OnClick({R.id.charge, R.id.record, R.id.video, R.id.share, R.id.qqgroup, R.id.btn_sign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.charge:
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
            case R.id.record:
                startActivity(new Intent(getActivity(), ChargeRecodActivity.class));
                break;
            case R.id.video:
                break;
            case R.id.share:
                break;
            case R.id.qqgroup:
                break;
            case R.id.btn_sign:
                parmap.clear();
                parmap.put("userid",SPControl.getString(getActivity(), Contants.USER_ID_KEY));
            BaseRequest.xutilsPostData("qian", parmap, new ResultCallback() {
                @Override
                public void onSuccess(String s) {
                    SignBean signBean = new Gson().fromJson(s, SignBean.class);
                    if (signBean.getCode() == 1){
                        tvScore.setText(signBean.getFflist());
                    }
                    ToastUtil.showMessage(getActivity(),signBean.getMsg());
                }

                @Override
                public void onError(String s) {

                }
            });
                break;
        }
    }

}
