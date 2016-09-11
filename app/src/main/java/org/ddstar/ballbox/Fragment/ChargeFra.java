package org.ddstar.ballbox.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import org.ddstar.ballbox.DataBean.MainBean;
import org.ddstar.ballbox.DataRequest.BaseRequest;
import org.ddstar.ballbox.DataRequest.ResultCallback;
import org.ddstar.ballbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChargeFra extends Fragment {


    @BindView(R.id.listView)
    ListView listView;

    public ChargeFra() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_charge, container, false);
        ButterKnife.bind(this, view);
        requestData();
        return view;
    }

    private void requestData() {
        BaseRequest.xutilsPostData("index", null, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
                MainBean resultBean = new Gson().fromJson(s, MainBean.class);

            }

            @Override
            public void onError(String s) {
                Log.e("onError", "result = " + s);
            }
        });
    }

}
