package com.example.godriveapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_policy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CheckBox agreement = findViewById(R.id.agreeTerms);
        agreement.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.greenThemeColor)));

        Button registerButton = findViewById(R.id.register);

        // Set Checkbox Listener
        agreement.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Enable or Disable Button based on Checkbox state
            registerButton.setEnabled(isChecked);
            if (isChecked) {
                registerButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white)); // Change to enabled color
                registerButton.setTextColor(ContextCompat.getColor(this, R.color.blueThemeColor)); // Change text color (optional)
            } else {
                registerButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.gray)); // Change to disabled color
                registerButton.setTextColor(ContextCompat.getColor(this, R.color.blueThemeColor)); // Change text color (optional)
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PolicyActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PolicyActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}