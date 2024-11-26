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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Define the color for "Sign In" link (same as themeColor)
        int linkColor = getResources().getColor(R.color.white);
        int defaultColor = getResources().getColor(R.color.white);

        TextView signup = findViewById(R.id.signupText);
        signup.setTextColor(defaultColor);  // Ensure default text color is applied

        // Set link color for clickable text explicitly
        signup.setLinkTextColor(linkColor);

        String signText = "Don't have an account? Sign Up";
        SpannableString signSpannable = new SpannableString(signText);

        int accountStart = signText.indexOf("Sign Up");
        int accountEnd = accountStart + "Sign Up".length();

        signSpannable.setSpan(new ForegroundColorSpan(linkColor), accountStart, accountEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan signInClick = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                // Open LoginActivity when "Sign In" is clicked
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };

        signSpannable.setSpan(signInClick, accountStart, accountEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        signup.setText(signSpannable);
        signup.setMovementMethod(LinkMovementMethod.getInstance());


        EditText emailInput = findViewById(R.id.email);
        EditText passwordInput = findViewById(R.id.password);
        Button loginbtn = findViewById(R.id.button_login);

        final String validEmail = "nisal.perera@bci.lk";
        final String validPassword = "12345678";

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(email.equals(validEmail) && password.equals(validPassword)){
                    Toast.makeText(LoginActivity.this, "Successfully Logged!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
                    emailInput.setText("");
                    passwordInput.setText("");
                }


            }
        });


    }
}