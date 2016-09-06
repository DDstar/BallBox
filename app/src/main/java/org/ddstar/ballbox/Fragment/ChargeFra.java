package org.ddstar.ballbox.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ddstar.ballbox.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChargeFra extends Fragment {


    public ChargeFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charge, container, false);
    }

}
