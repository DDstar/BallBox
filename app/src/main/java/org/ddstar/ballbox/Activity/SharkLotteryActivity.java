package org.ddstar.ballbox.Activity;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.ddstar.ballbox.Base.BaseActivity;
import org.ddstar.ballbox.Base.Contants;
import org.ddstar.ballbox.DataBean.SharkBean;
import org.ddstar.ballbox.DataRequest.BaseRequest;
import org.ddstar.ballbox.DataRequest.ResultCallback;
import org.ddstar.ballbox.R;
import org.ddstar.ballbox.Utils.SPControl;
import org.ddstar.ballbox.Utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharkLotteryActivity extends BaseActivity implements SensorEventListener {

    SensorManager sensorManager = null;
    Vibrator vibrator = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_lottery);
        ButterKnife.bind(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
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
                break;
            case R.id.btn_gift:
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER)
        {
            if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
                    .abs(values[2]) > 17))
            {
                Log.d("sensor x ", "============ values[0] = " + values[0]);
                Log.d("sensor y ", "============ values[1] = " + values[1]);
                Log.d("sensor z ", "============ values[2] = " + values[2]);
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
                ToastUtil.showMessage(SharkLotteryActivity.this,sharkBean.getMsg());
            }

            @Override
            public void onError(String s) {
                ToastUtil.showMessage(SharkLotteryActivity.this,s);
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
