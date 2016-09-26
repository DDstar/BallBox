package com.mdxx.qqbh.Activity;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mdxx.qqbh.Base.BaseActivity;
import com.mdxx.qqbh.Base.Contants;
import com.mdxx.qqbh.DataBean.BinboBean;
import com.mdxx.qqbh.DataBean.SharkBean;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.SPControl;
import com.mdxx.qqbh.Utils.ToastUtil;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharkLotteryActivity extends BaseActivity implements SensorEventListener {

    SensorManager sensorManager = null;
    Vibrator vibrator = null;
    @BindView(R.id.tv_user_data)
    TextView tvUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_lottery);
        ButterKnife.bind(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        requestLotteryRecord();
    }

    private void requestLotteryRecord() {
        BaseRequest.xutilsPostData("yaoyyao_log", null, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
                BinboBean binboBean = new Gson().fromJson(s, BinboBean.class);
                if (binboBean.getCode() == 1) {
                    List<String> binboBeanFflist = binboBean.getFflist();
                    //循环切换中奖用户信息
                    showingUserMsg(binboBeanFflist);
                }
            }


            @Override
            public void onError(String s) {

            }
        });
    }

    private void showingUserMsg(final List<String> binboBeanFflist) {
        final Random random = new Random();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final int index = random.nextInt(binboBeanFflist.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String userMsg = binboBeanFflist.get(index);
                        tvUserData.setText(userMsg);
                    }
                });
                new Handler().postDelayed(this, 3000);
            }
        }, 3000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null) {// 注册监听器
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @OnClick({R.id.btn_permsg, R.id.btn_gift})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_permsg:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("flag", 3);
                removeAllActivity();
                startActivity(intent);
                break;
            case R.id.btn_gift:
                startActivity(new Intent(this, ChargeRecodActivity.class));
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            if ((Math.abs(values[0]) > 12 || Math.abs(values[1]) > 12 || Math
                    .abs(values[2]) > 12)) {
//                tv.setText("摇一摇成功!!!");
                //摇动手机后，再伴随震动提示~~
                vibrator.vibrate(500);
                doLottery();
            }

        }
    }

    private void doLottery() {
        parmap.clear();
        parmap.put("userid", SPControl.getString(this, Contants.USER_ID_KEY));
        BaseRequest.xutilsPostData("yaoyyao", parmap, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
                SharkBean sharkBean = new Gson().fromJson(s, SharkBean.class);
//                if (sharkBean.getCode() == 1){
//                }
                ToastUtil.showMessage(SharkLotteryActivity.this, sharkBean.getMsg());
            }

            @Override
            public void onError(String s) {
                ToastUtil.showMessage(SharkLotteryActivity.this, s);
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
