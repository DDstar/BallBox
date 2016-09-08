package org.ddstar.ballbox.Base;

import android.app.Application;

import org.xutils.x;

/**
 * Created by DDstar on 2016/9/8.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
