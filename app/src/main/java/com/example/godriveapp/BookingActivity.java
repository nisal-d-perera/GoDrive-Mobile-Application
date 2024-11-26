package com.example.godriveapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private TextView vehicle;
    private ImageView vehicleImage;
    private EditText defaultDate, dateDropoffEditText;
    private String selectedPickupDate, selectedDropoffDate;

    private EditText locationPickupEditText, purposeEditText;
    private RadioGroup paymentMethodGroup;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView back = findViewById(R.id.backPage);
        back.setOnClickListener(v -> {
            finish();
        });

        vehicle = findViewById(R.id.nameVehicle);
        vehicleImage = findViewById(R.id.imageVehicle);

        Intent intent = getIntent();
        String type = intent.getStringExtra("CAR_NAME");
        int imageRes = intent.getIntExtra("CAR_IMAGE", 0);


        vehicle.setText(type);
        vehicleImage.setImageResource(imageRes);

        defaultDate = findViewById(R.id.datePickup);
        dateDropoffEditText = findViewById(R.id.dateDropoff);
        setCurrentDate();
        defaultDate.setOnClickListener(v -> showDatePickerDialog(true));
        dateDropoffEditText.setOnClickListener(v -> showDatePickerDialog(false));


        RadioButton radioCash = findViewById(R.id.radioCash);
        radioCash.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueThemeColor)));

        RadioButton radioCreditDebit = findViewById(R.id.radioCreditDebit);
        radioCreditDebit.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueThemeColor)));

        RadioButton radioBankTransfer = findViewById(R.id.radioBankTransfer);
        radioBankTransfer.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueThemeColor)));

        locationPickupEditText = findViewById(R.id.locationPickup);
        purposeEditText = findViewById(R.id.purpose);
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        confirmButton = findViewById(R.id.continueButton);

        confirmButton.setOnClickListener(v -> {
            // Get all input field values
            String location = locationPickupEditText.getText().toString().trim();
            String pickupDate = defaultDate.getText().toString().trim();
            String dropoffDate = dateDropoffEditText.getText().toString().trim();
            String purpose = purposeEditText.getText().toString().trim();

            // Check if any input field is empty
            if (location.isEmpty() || pickupDate.isEmpty() || dropoffDate.isEmpty() || purpose.isEmpty()) {
                // Show error toast if any field is empty
                Toast.makeText(this, "Please fill all the fields and try again", Toast.LENGTH_SHORT).show();
            } else {
                // Check if payment method is selected
                int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    // No payment method selected
                    Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                } else {
                    // Get selected payment method
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String paymentMethod = selectedRadioButton.getText().toString();

                    // Show the selected payment method and the booking details
                    Toast.makeText(this, "Booked Vehicle : " + type, Toast.LENGTH_SHORT).show();

                    // Redirect to DashboardActivity which will load HomeFragment
                    Intent intentnew = new Intent(BookingActivity.this, DashboardActivity.class);
                    startActivity(intentnew);

                    // Show success message
                    Toast.makeText(this, "Vehicle successfully booked. Our agent will contact you soon!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setCurrentDate() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

        // Set the current date in the EditText
        defaultDate.setText(currentDate);
    }

    private void showDatePickerDialog(boolean isPickupDate) {
        // Get current date
        Calendar calendar = Calendar.getInstance();

        // If it's the Dropoff Date, use the Pickup Date's value to calculate the min date
        if (!isPickupDate && selectedPickupDate != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                calendar.setTime(dateFormat.parse(selectedPickupDate));
                calendar.add(Calendar.MONTH, 1);  // Add one month to Pickup Date for Dropoff's min date
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                BookingActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                (view, year1, month1, dayOfMonth) -> {
                    // Format and set the selected date in the respective EditText
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year1, month1, dayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    String selectedDateStr = dateFormat.format(selectedDate.getTime());

                    if (isPickupDate) {
                        defaultDate.setText(selectedDateStr);
                        selectedPickupDate = selectedDateStr;  // Store Pickup Date
                    } else {
                        dateDropoffEditText.setText(selectedDateStr);
                        selectedDropoffDate = selectedDateStr;  // Store Dropoff Date
                    }
                },
                year, month, day
        );

        // Set minimum date for Dropoff date (1 month later than Pickup date)
        if (!isPickupDate && selectedPickupDate != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                Calendar pickupCalendar = Calendar.getInstance();
                pickupCalendar.setTime(dateFormat.parse(selectedPickupDate));
                pickupCalendar.add(Calendar.MONTH, 1); // Set min date to 1 month after pickup
                datePickerDialog.getDatePicker().setMinDate(pickupCalendar.getTimeInMillis());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            // Set min date for Pickup Date to today's date
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        }

        // Show the dialog
        datePickerDialog.show();
    }
}