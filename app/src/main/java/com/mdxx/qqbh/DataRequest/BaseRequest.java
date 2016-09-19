package com.mdxx.qqbh.DataRequest;

import android.text.TextUtils;

import com.mdxx.qqbh.Base.Contants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by DDstar on 2016/9/8.
 */
public class BaseRequest {
    public static void xutilsGetData(String head, Map<String, Object> parmarMap, final ResultCallback rc) {
        String itemHeade = "";
        if (!TextUtils.isEmpty(head)) {
            itemHeade = head;
        }
        RequestParams requestParams = new RequestParams(Contants.BBCANDY + itemHeade);
        if (parmarMap != null) {
            for (Map.Entry<String, Object> entry : parmarMap.entrySet()) {
                requestParams.addQueryStringParameter(entry.getKey(), (String) entry.getValue());
            }
        }
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rc.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rc.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void xutilsPostData(String head, Map<String, Object> parmarMap, final ResultCallback rc) {
        String itemHeade = "";
        if (!TextUtils.isEmpty(head)) {
            itemHeade = head;
        }
        RequestParams requestParams = new RequestParams(Contants.BASE_URL + itemHeade);
        if (parmarMap != null) {
            for (Map.Entry<String, Object> entry : parmarMap.entrySet()) {
                requestParams.addParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
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
