package org.ddstar.ballbox.Base;

import android.app.Application;

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
    }

    private void openUMPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable(new IUmengRegisterCallback() {
            @Override
            public void onRegistered(String deviceToken) {
                KLog.e(deviceToken);
            }
        });

    }
}
