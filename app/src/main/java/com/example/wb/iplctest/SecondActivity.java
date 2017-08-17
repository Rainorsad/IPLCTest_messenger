package com.example.wb.iplctest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Zhangchen on 2017/8/17.
 */

public class SecondActivity extends AppCompatActivity {

    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.bt)
    public void onClick() {

    }
}
