package com.mdxx.qqbh.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mdxx.qqbh.Base.BaseActivity;
import com.mdxx.qqbh.Base.Contants;
import com.mdxx.qqbh.DataBean.ChargeRecodeBean;
import com.mdxx.qqbh.DataRequest.BaseRequest;
import com.mdxx.qqbh.DataRequest.ResultCallback;
import com.mdxx.qqbh.R;
import com.mdxx.qqbh.Utils.SPControl;
import com.mdxx.qqbh.Utils.TimeUtil;
import com.socks.library.KLog;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChargeRecodActivity extends BaseActivity {

    @BindView(R.id.listView_record)
    ListView listViewRecord;
    @BindView(R.id.empty_data)
    RelativeLayout emptyData;
    @BindView(R.id.tv_score)
    TextView tvScore;
    private RecordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_recod);
        ButterKnife.bind(this);
        String score = getIntent().getStringExtra("score");
        tvScore.setText(score);
        requestData();
    }

    private void requestData() {
        parmap.clear();
        parmap.put("userid", SPControl.getString(this, Contants.USER_ID_KEY));
        BaseRequest.xutilsPostData("dh_log", parmap, new ResultCallback() {
            @Override
            public void onSuccess(String s) {
                ChargeRecodeBean recodeBean = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int code = jsonObject.optInt("code");
                    if (code == 1) {
                        recodeBean = new Gson().fromJson(s, ChargeRecodeBean.class);
                    } else {
                        emptyData.setVisibility(View.VISIBLE);
                        listViewRecord.setVisibility(View.GONE);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                KLog.e(s);
                List<ChargeRecodeBean.FflistBean> fflist = recodeBean.getFflist();
                if (fflist.size() > 0) {
                    emptyData.setVisibility(View.GONE);
                    listViewRecord.setVisibility(View.VISIBLE);
                    listViewRecord.setAdapter(mAdapter = new RecordAdapter(ChargeRecodActivity.this, R.layout.record_item, fflist));
                } else {
                    emptyData.setVisibility(View.VISIBLE);
                    listViewRecord.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String s) {
                KLog.e(s);
            }
        });
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    class RecordAdapter extends CommonAdapter<ChargeRecodeBean.FflistBean> {

        public RecordAdapter(Context context, int layoutId, List<ChargeRecodeBean.FflistBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, ChargeRecodeBean.FflistBean item, int position) {
            TextView tvGoods = viewHolder.getView(R.id.tv_goods);
            TextView tvScore = viewHolder.getView(R.id.tv_score);
            TextView tvDate = viewHolder.getView(R.id.tv_date);
            TextView tvState = viewHolder.getView(R.id.tv_state);
            tvGoods.setText(item.getReason());
            tvScore.setText("" + item.getMoney());
            tvState.setText(item.getStatus());
            KLog.e(item.getTime());
            tvDate.setText(TimeUtil.getDateToString(item.getTime()));
        }
    }
}
