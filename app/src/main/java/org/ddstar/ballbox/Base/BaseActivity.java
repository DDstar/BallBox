package org.ddstar.ballbox.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {
    protected Map<String, Object> parmap = new HashMap<>();
    private static List<Activity> mActiAct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActiAct.add(this);
    }

    protected void removeAllActivity() {
        for (Activity activity : mActiAct) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
