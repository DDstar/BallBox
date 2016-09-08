package org.ddstar.ballbox.DataRequest;

import android.text.TextUtils;
import android.util.Log;

import org.ddstar.ballbox.Base.Contants;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by DDstar on 2016/9/8.
 */
public class BaseRequest {
    public static void xutilsGetData(){

    }
    public static void xutilsPostData(String head,Map<String,Object>parmarMap, final ResultCallback rc){
        String itemHeade = "";
        if (!TextUtils.isEmpty(head)){
            itemHeade = head;
        }
        RequestParams requestParams = new RequestParams(Contants.BASE_URL + itemHeade);
        if (parmarMap!= null){
            for (Map.Entry<String,Object> entry:parmarMap.entrySet()){
                requestParams.addParameter(entry.getKey(),entry.getValue());
            }
        }
      x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("xutilsPostData","thread--->" + Thread.currentThread().getName());
                rc.onSuccess(s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                rc.onError(throwable.toString());
            }

            @Override
            public void onCancelled(CancelledException e) {
                rc.onError(e.toString());
            }

            @Override
            public void onFinished() {

            }
        });

    }
}
