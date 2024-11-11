package com.example.godriveapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize logo ImageView
        ImageView logo = findViewById(R.id.logo_splash);

        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Set the ProgressBar color to the blue color
        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.blueThemeColor), PorterDuff.Mode.SRC_IN);

        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        // Start animations
        logo.startAnimation(fadeIn);
        logo.startAnimation(scaleUp);

        // Duration for the splash screen
        int splashScreenDuration = 5000; // 3 seconds

        // Move to the Main Activity after the splash duration
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, splashScreenDuration);
    }
}