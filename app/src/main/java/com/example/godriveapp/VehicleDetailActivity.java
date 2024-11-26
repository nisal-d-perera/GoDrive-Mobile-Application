package com.example.godriveapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import jp.wasabeef.blurry.Blurry;

public class VehicleDetailActivity extends AppCompatActivity {

    private ImageView mainVehicleImage;
    private TextView vehicleName, vehicleFuel, vehicleTransmission, vehicleModel, vehicleSeat, vehiclePrice;
    private LinearLayout vehicleTypesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vehicle_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView backToExplora = findViewById(R.id.backtoExplora);
        backToExplora.setOnClickListener(v -> {
            finish();
        });

        mainVehicleImage = findViewById(R.id.Image);
        vehicleName = findViewById(R.id.Model);
        vehicleModel = findViewById(R.id.Type);
        vehicleTransmission = findViewById(R.id.Auto);
        vehicleFuel = findViewById(R.id.Gas);
        vehicleSeat = findViewById(R.id.Seat);
        vehiclePrice = findViewById(R.id.price);
        vehicleTypesLayout = findViewById(R.id.vehicleTypesLayout);


        Intent intent = getIntent();
        int imageRes = intent.getIntExtra("image", 0);
        String type = intent.getStringExtra("type");
        String model = intent.getStringExtra("model");
        String fuelType = intent.getStringExtra("fuelType");
        String seats = intent.getStringExtra("seats");
        String transmission = intent.getStringExtra("transmission");
        String price = intent.getStringExtra("price");

        mainVehicleImage.setImageResource(imageRes);
        vehicleName.setText(model);
        vehicleModel.setText(type);
        vehicleTransmission.setText(transmission);
        vehicleFuel.setText(fuelType);
        vehicleSeat.setText(seats);
        vehiclePrice.setText(price + " /per month");


        addAnimations();

        Button bookNowBtn = findViewById(R.id.bookNowButton);
        View loadingOverlay = findViewById(R.id.loadingOverlay);

        bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the loading overlay
                loadingOverlay.setVisibility(View.VISIBLE);

                // Delay before transitioning to the next page
                new Handler().postDelayed(() -> {
                    // Redirect to BookingActivity
                    Intent intent = new Intent(VehicleDetailActivity.this, BookingActivity.class);
                    intent.putExtra("CAR_NAME", model);
                    intent.putExtra("CAR_IMAGE", imageRes);
                    startActivity(intent);

                    // Optional: Finish current activity
                    finish();
                }, 2000); // Adjust delay as needed (2 seconds)
            }
        });


    }


    private void addAnimations() {
        // Image Fade-In Animation
        mainVehicleImage.setAlpha(0f);
        mainVehicleImage.animate().alpha(1f).setDuration(1000).start();

        // Text Fade-In Animation
        vehicleName.setAlpha(0f);
        vehicleName.animate().alpha(1f).setDuration(1000).start();

        vehicleModel.setAlpha(0f);
        vehicleModel.animate().alpha(1f).setDuration(1000).start();

        // Bounce Animation for Button
        Button bookNowButton = findViewById(R.id.bookNowButton);
        bookNowButton.setScaleX(0f);
        bookNowButton.setScaleY(0f);
        bookNowButton.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(800)
                .setInterpolator(new BounceInterpolator())
                .start();

        // Slide-In Animation for Specifications Section
        vehicleTypesLayout.setTranslationY(500f);
        vehicleTypesLayout.animate().translationY(0f).setDuration(800).start();
    }

}