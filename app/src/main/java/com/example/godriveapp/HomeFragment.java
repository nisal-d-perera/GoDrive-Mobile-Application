package com.example.godriveapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView notificationIcon = view.findViewById(R.id.notification);

        // Set a click listener on the edit icon
        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to EditProfileActivity
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        RecyclerView vehicleViews = view.findViewById(R.id.popularVeh);
        vehicleViews.setLayoutManager(new GridLayoutManager(getContext(), 1)); // Flex layout

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Toyota Axio 2021", "Car / Sedan", "Auto", "Patrol","5 Seats", R.drawable.popularcar, "Rs. 120,000.00"));
        vehicles.add(new Vehicle("Nissan Caravan 2005", "Van / Highroof", "Auto", "Diesel","18 Seats", R.drawable.popularavan,"Rs. 150,000.00"));
        vehicles.add(new Vehicle("Honda CR-V 2025", "Jeep / Hybrid", "Auto", "Patrol","5 Seats", R.drawable.populrajeep, "Rs. 200,000.00"));
        vehicles.add(new Vehicle("Suzuki Every 2025", "Minivan / Wagon", "Auto", "Patrol","7 Seats", R.drawable.popularminivan, "Rs. 80,000.00"));

        VehicleAdapter adapter = new VehicleAdapter(getContext(), vehicles, vehicle -> {
            Intent intent = new Intent(getContext(), VehicleDetailActivity.class);
            intent.putExtra("image", vehicle.getImageResourceId());
            intent.putExtra("type", vehicle.getTypes());
            intent.putExtra("model", vehicle.getModel());
            intent.putExtra("fuelType", vehicle.getGas());
            intent.putExtra("seats", vehicle.getSeats());
            intent.putExtra("transmission", vehicle.getAutomation());
            intent.putExtra("price", vehicle.getPrice());
            getContext().startActivity(intent);
        });
        vehicleViews.setAdapter(adapter);

        return view;
    }
}