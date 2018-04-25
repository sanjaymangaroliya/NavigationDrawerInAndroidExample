package com.zt.navigationdrawer;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends BaseNavigationActivity {

    private TextView tvTitle, tvLanguages;
    private String strTitle = "ANDROID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            strTitle = bundle.getString("title");
        }

        initUI();
    }

    private void initUI() {
        tvTitle = findViewById(R.id.tv_title);
        tvLanguages = findViewById(R.id.tv_languages);

        if (strTitle.equals(getResources().getString(R.string.android))) {
            tvTitle.setText(getString(R.string.android));
            tvLanguages.setText(getString(R.string.android));
        } else if (strTitle.equals(getResources().getString(R.string.ios))) {
            tvTitle.setText(getString(R.string.ios));
            tvLanguages.setText(getString(R.string.ios));
        } else if (strTitle.equals(getResources().getString(R.string.java))) {
            tvTitle.setText(getString(R.string.java));
            tvLanguages.setText(getString(R.string.java));
        } else if (strTitle.equals(getResources().getString(R.string.php))) {
            tvTitle.setText(getString(R.string.php));
            tvLanguages.setText(getString(R.string.php));
        } else {
            tvTitle.setText(getString(R.string.asp_net));
            tvLanguages.setText(getString(R.string.asp_net));
        }
    }

    @Override
    protected int getSelfNavDrawerItem() {
        if (strTitle.equals(getResources().getString(R.string.android))) {
            return R.id.nav_android;
        } else if (strTitle.equals(getResources().getString(R.string.ios))) {
            return R.id.nav_ios;
        } else if (strTitle.equals(getResources().getString(R.string.java))) {
            return R.id.nav_java;
        } else if (strTitle.equals(getResources().getString(R.string.php))) {
            return R.id.nav_php;
        } else {
            return R.id.nav_asp_net;
        }
    }
}
