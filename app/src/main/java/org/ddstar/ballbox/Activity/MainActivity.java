package org.ddstar.ballbox.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import org.ddstar.ballbox.Fragment.ChargeFra;
import org.ddstar.ballbox.Fragment.MainFra;
import org.ddstar.ballbox.Fragment.UserFra;
import org.ddstar.ballbox.Fragment.WorkFra;
import org.ddstar.ballbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContainer();
        initTab();
    }

    private void initContainer() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFra()).commit();
    }

    private void initTab() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main:
                        fragment = new MainFra();
                        break;
                    case R.id.rb_work:
                        fragment = new WorkFra();
                        break;
                    case R.id.rb_charge:
                        fragment = new ChargeFra();
                        break;
                    case R.id.rb_user:
                        fragment = new UserFra();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
    }
}
