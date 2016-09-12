package com.mdxx.qqbh.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class ToastUtil {
    static Toast mToast;
    public static void showMessage(Context context,String msg){
        if (mToast == null){
            mToast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
