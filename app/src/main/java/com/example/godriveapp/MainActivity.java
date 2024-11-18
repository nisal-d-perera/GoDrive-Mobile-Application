package com.example.godriveapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Define the full text
        String welcomeText = "Your Unlimited Car Rental Experience";

        // Create a SpannableString from the full text
        SpannableString spannableStringOne = new SpannableString(welcomeText);

        // Define the color for "Car Rental"
        int themeColor = getResources().getColor(R.color.blueThemeColor);

        // Find the start and end indices of "Car Rental"
        int start = welcomeText.indexOf("Car Rental");
        int end = start + "Car Rental".length();

        // Apply the color span to "Car Rental"
        spannableStringOne.setSpan(new ForegroundColorSpan(themeColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString to the TextView
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        welcomeTextView.setText(spannableStringOne);

        Button rbtn = findViewById(R.id.startedButton);
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        // Define the color for "Sign In" link (same as themeColor)
        int linkColor = getResources().getColor(R.color.blueThemeColor);
        int defaultColor = getResources().getColor(R.color.black);

        TextView alreadyAccountText = findViewById(R.id.alreadyAccountText);
        alreadyAccountText.setTextColor(defaultColor);  // Ensure default text color is applied

        // Set link color for clickable text explicitly
        alreadyAccountText.setLinkTextColor(linkColor);

        String signText = "Already have an account? Sign In";
        SpannableString signSpannable = new SpannableString(signText);

        int themeColorTwo = getResources().getColor(R.color.greenThemeColor);

        int accountStart = signText.indexOf("Sign In");
        int accountEnd = accountStart + "Sign In".length();

        signSpannable.setSpan(new ForegroundColorSpan(linkColor), accountStart, accountEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan signInClick = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Open LoginActivity when "Sign In" is clicked
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        signSpannable.setSpan(signInClick, accountStart, accountEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        alreadyAccountText.setText(signSpannable);
        alreadyAccountText.setMovementMethod(LinkMovementMethod.getInstance());


    }
}