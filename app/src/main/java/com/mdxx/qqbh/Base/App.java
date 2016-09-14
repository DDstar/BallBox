package com.mdxx.qqbh.Base;

import android.app.Application;

import com.mdxx.qqbh.Utils.SPControl;
import com.pgyersdk.crash.PgyCrashManager;
import com.socks.library.KLog;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.xutils.x;

/**
 * Created by DDstar on 2016/9/8.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        openUMPush();
        KLog.init(true, getApplicationContext().getPackageName());
        PgyCrashManager.register(this);
    }


    private void openUMPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        KLog.e(getPackageName());
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                SPControl.saveString(App.this, Contants.UM_DEVICE_TOKEN, deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                KLog.e("s = " + s + "   s1 = " + s);
            }
        });
        PushAgent.getInstance(this).onAppStart();
    }


}
