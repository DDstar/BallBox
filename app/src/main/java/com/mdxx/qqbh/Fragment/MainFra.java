package com.mdxx.qqbh.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
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
import com.mdxx.qqbh.DataBean.ShareDetailBean;
import com.mdxx.qqbh.DataBean.SignBean;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.SPControl;
import com.mdxx.qqbh.Utils.ToastUtil;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.message.ALIAS_TYPE;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Tencent mTencent;
    private ShareDetailBean detailBean;

    public MainFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mTencent = Tencent.createInstance(Contants.QQ_APPID, getActivity().getApplicationContext());
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
                shareQQ();
                break;
            case R.id.btn_work_state:
                ((MainActivity) getActivity()).go2work();
                break;
        }
    }

    private void shareQQ() {
        BaseRequest.xutilsPostData("share_data", null, new ResultCallback() {
            @Override
            public void onSuccess(String s) {

                detailBean = new Gson().fromJson(s, ShareDetailBean.class);
                if (detailBean.getCode() == 1) {
                    // 1、检验登录，获取access_token和openid
//                    mTencent.login(MainFra.this, "topicget_simple_userinfo,add_topic", null);

                    //2 登录


                    // 3 分享
                    final Bundle params = new Bundle();
                    ShareDetailBean.FflistBean detailBeanFflist = detailBean.getFflist();
                    params.putString(QQShare.SHARE_TO_QQ_TITLE, detailBeanFflist.getSharetitle());
                    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, detailBeanFflist.getShareurl());
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://www.taoqiuqiu.com/static/qiuqiulog.png");
                    params.putString(QQShare.SHARE_TO_QQ_SUMMARY, detailBeanFflist.getShareword());
                    params.putString(QQShare.SHARE_TO_QQ_APP_NAME, getString(R.string.app_name));
                    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                    params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 0x00);
                    doShareToQQ(params);


                } else {
                    ToastUtil.showMessage(getActivity(), detailBean.getMsg());
                }
            }

            @Override
            public void onError(String s) {
                KLog.e(s);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, new IUiListener() {
            @Override
            public void onComplete(Object response) {
                if (null == response) {
                    return;
                }
                JSONObject jsonResponse = (JSONObject) response;
                if (null != jsonResponse && jsonResponse.length() == 0) {
                    return;
                }
                String token = null;
                String expires = null;
                String openId = null;
                try {
                    token = jsonResponse.getString(Constants.PARAM_ACCESS_TOKEN);

                    expires = jsonResponse.getString(Constants.PARAM_EXPIRES_IN);
                    openId = jsonResponse.getString(Constants.PARAM_OPEN_ID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                        && !TextUtils.isEmpty(openId)) {
                    mTencent.setAccessToken(token, expires);
                    mTencent.setOpenId(openId);
                    final Bundle params = new Bundle();
                    ShareDetailBean.FflistBean detailBeanFflist = detailBean.getFflist();
                    params.putString(QQShare.SHARE_TO_QQ_TITLE, detailBeanFflist.getSharetitle());
                    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, detailBeanFflist.getShareurl());
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://www.taoqiuqiu.com/static/qiuqiulog.png");
                    params.putString(QQShare.SHARE_TO_QQ_SUMMARY, detailBeanFflist.getShareword());
                    params.putString(QQShare.SHARE_TO_QQ_APP_NAME, getString(R.string.app_name));
                    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                    params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 0x00);
                    doShareToQQ(params);
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {
            }
        });
    }

    private void doShareToQQ(final Bundle params) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQQ(getActivity(), params, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            //回调成功，调用系统后台
                            KLog.e(o);
                        }

                        @Override
                        public void onError(UiError uiError) {
                            //分享失败
                            KLog.e(uiError);
                        }

                        @Override
                        public void onCancel() {
                            //分享取消
                            ToastUtil.showMessage(getActivity(), "取消分享了~");
                        }
                    });
                }
            }
        });
    }
}
