package org.ddstar.ballbox.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.ddstar.ballbox.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFra extends Fragment {


    @BindView(R.id.listView)
    ListView listView;

    public MainFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initListView();
        return view;
    }

    private void initListView() {
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.list_header, null);
        listView.addHeaderView(headerView);
    }

    class CusAdapter extends CommonAdapter<String> {

        public CusAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, String item, int position) {

        }
    }

}
