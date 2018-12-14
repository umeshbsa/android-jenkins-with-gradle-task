package com.app.gradle.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.app.gradle.BuildConfig;
import com.app.gradle.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View bgHeader = findViewById(R.id.bg_header);
        ImageView splashLogo = (ImageView) findViewById(R.id.splash_logo);

        // This is coming from gralde file. This color comes from Jenkins script when we run script command.
        int bgColor = Color.parseColor(BuildConfig.PRIMARY_COLOR);
        bgHeader.setBackgroundColor(bgColor);

        // This is coming from gralde file. This image comes from Jenkins script when we run script command.
        splashLogo.setImageResource(R.drawable.splash_logo);

    }
}
