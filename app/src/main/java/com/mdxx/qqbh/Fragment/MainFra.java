package com.mdxx.qqbh.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mdxx.qqbh.Activity.GetBBCandyActivity;
import com.mdxx.qqbh.Activity.MainActivity;
import com.mdxx.qqbh.Activity.SharkLotteryActivity;
import com.mdxx.qqbh.Base.Contants;
import com.mdxx.qqbh.DataBean.MainADBean;
import com.mdxx.qqbh.DataBean.MainUserMsgBean;
import com.mdxx.qqbh.DataBean.SignBean;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.SPControl;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.umeng.message.ALIAS_TYPE;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFra extends Fragment {
    @BindView(R.id.btn_gift_state)
    Button btnGiftState;
    @BindView(R.id.btn_sign_state)
    Button btnSignState;
    @BindView(R.id.btn_qq_state)
    Button btnQqState;
    @BindView(R.id.btn_work_state)
    Button btnWorkState;
    @BindView(R.id.adview)
    ImageView adView;
    @BindView(R.id.ad_container)
    FrameLayout adViewContainer;


    Map<String, Object> parmap = new HashMap<>();

    public MainFra() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        requestAdPic();
        registUser();
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
                KLog.e("userID = " + fflistBean.getUserid());
                KLog.e("deviceToken = " + SPControl.getString(getActivity(), Contants.UM_DEVICE_TOKEN));
                registerUMWithUserId();
                registerOurServer();
                int xslb = fflistBean.getXslb();
                if (xslb == 1) {
                    btnGiftState.setEnabled(false);
                    btnGiftState.setText("已完成");
                }
                int qqshare_num = fflistBean.getQqshare_num();
                if (qqshare_num == 1) {
                    btnQqState.setEnabled(false);
                    btnQqState.setText("已完成");
                }
                int isqd = fflistBean.getIsqd();
                if (isqd == 1) {
                    btnSignState.setEnabled(false);
                    btnSignState.setText("已完成");
                }
            }


            @Override
            public void onError(String s) {

            }
        });
    }


    private void registerOurServer() {
        parmap.clear();
        parmap.put("userid", SPControl.getString(getActivity(), Contants.USER_ID_KEY));
        parmap.put("umeng", SPControl.getString(getActivity(), Contants.UM_DEVICE_TOKEN));
        BaseRequest.xutilsPostData("user_umeng", parmap, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
            }

            @Override
            public void onError(String s) {

            }
        });
    }

    private void registerUMWithUserId() {
        PushAgent mPushAgent = PushAgent.getInstance(getActivity());
        mPushAgent.addAlias(SPControl.getString(getActivity(), Contants.USER_ID_KEY), ALIAS_TYPE.QQ, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {

            }
        });
    }

    private void requestAdPic() {
        BaseRequest.xutilsPostData("index", null, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
                //TODO
                MainADBean mainADBean = new Gson().fromJson(s, MainADBean.class);
                final MainADBean.FflistBean fflistBean = mainADBean.getFflist();
                if ("1".equals(fflistBean.getIsshow())) {
                    Picasso.with(getActivity()).load(Contants.IMGUURL + fflistBean.getImg()).into(adView);
                } else {
                    adViewContainer.setVisibility(View.GONE);
                }
                adViewContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent urlIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(fflistBean.getUrl()));
                        startActivity(urlIntent);
                    }
                });
            }

            @Override
            public void onError(String s) {

            }
        });
    }


    @OnClick({R.id.btn_shark, R.id.btn_rocket, R.id.btn_gift_state, R.id.btn_sign_state, R.id.btn_qq_state, R.id.btn_work_state, R.id.imageView6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shark:
                startActivity(new Intent(getActivity(), SharkLotteryActivity.class));
                break;
            case R.id.btn_rocket:
                startActivity(new Intent(getActivity(), GetBBCandyActivity.class));
                break;
            case R.id.btn_gift_state:
                break;
            case R.id.imageView6:
                adViewContainer.setVisibility(View.GONE);
                break;
            case R.id.btn_sign_state:
                parmap.clear();
                KLog.e(SPControl.getString(getActivity(), Contants.USER_ID_KEY));
                parmap.put("userid", SPControl.getString(getActivity(), Contants.USER_ID_KEY));
                BaseRequest.xutilsPostData("qian", parmap, new ResultCallback() {
                    @Override
                    public void onSuccess(String s) {
                        SignBean signBean = new Gson().fromJson(s, SignBean.class);
                        if (signBean.getCode() == 1) {
                            Toast.makeText(getActivity(), "签到成功", Toast.LENGTH_SHORT).show();
                            btnSignState.setEnabled(false);
                            btnSignState.setText("已完成");
                        }
                    }

                    @Override
                    public void onError(String s) {

                    }
                });
                break;
            case R.id.btn_qq_state:
                break;
            case R.id.btn_work_state:
                ((MainActivity) getActivity()).go2work();
                break;
        }
    }
}
