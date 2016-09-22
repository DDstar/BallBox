package com.mdxx.qqbh.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdxx.qqbh.R;

import net.youmi.android.offers.OffersManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFra extends Fragment {


    public WorkFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.mdxx.qqbh.R.layout.fragment_work, container, false);
        ButterKnife.bind(this, view);
        initYouMi();
        return view;
    }

    private void initYouMi() {
        OffersManager.getInstance(getActivity()).onAppLaunch();
    }

    @OnClick(R.id.work_1)
    public void onClick() {
        OffersManager.getInstance(getActivity()).showOffersWall();
    }
}
