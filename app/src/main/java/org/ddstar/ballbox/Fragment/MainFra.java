package org.ddstar.ballbox.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.ddstar.ballbox.DataBean.MainTabMenuBean;
import org.ddstar.ballbox.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFra extends Fragment {


    @BindView(R.id.listView)
    ListView listView;


    List<MainTabMenuBean> mData = new ArrayList<>();
    int[] resIds = {R.drawable.gift, R.drawable.sign, R.drawable.share, R.drawable.work};
    private CusAdapter mAdapter;

    public MainFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initData();
        initListView();
        return view;
    }

    private void initData() {
        String[] titleArray = getResources().getStringArray(R.array.main_tab_menu_title);
        String[] subTitleArray = getResources().getStringArray(R.array.main_tab_menu_sub_title);
        for (int i = 0; i < 4; i++) {
            mData.add(new MainTabMenuBean(resIds[i], titleArray[i], subTitleArray[i], 1));
        }
    }

    private void initListView() {
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.list_header, null);
        listView.addHeaderView(headerView);
        mAdapter = new CusAdapter(getActivity(), R.layout.main_tab_item, mData);
        listView.setAdapter(mAdapter);
    }

    class CusAdapter extends CommonAdapter<MainTabMenuBean> {

        public CusAdapter(Context context, int layoutId, List<MainTabMenuBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, MainTabMenuBean item, int position) {
            ImageView photo = viewHolder.getView(R.id.ima_photo);
            TextView tvTitle = viewHolder.getView(R.id.tv_title);
            TextView tvSubTitle = viewHolder.getView(R.id.tv_sub_title);
            Button btnState = viewHolder.getView(R.id.tv_state);
            photo.setImageResource(item.getMenuPhotoRes());
            tvTitle.setText(item.getWorkName());
            tvSubTitle.setText(item.getWorkMsg());
            btnState.setText(item.getType());
        }
    }

}
