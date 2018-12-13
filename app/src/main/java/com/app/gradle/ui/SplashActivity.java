package com.app.gradle.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.gradle.R;

public abstract class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
