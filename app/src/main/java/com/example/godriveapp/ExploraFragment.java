package com.example.godriveapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploraFragment newInstance(String param1, String param2) {
        ExploraFragment fragment = new ExploraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_explora, container, false);

        RecyclerView vehicleAllViews = view.findViewById(R.id.allvehicle);
        vehicleAllViews.setLayoutManager(new GridLayoutManager(getContext(), 1)); // Flex layout

        List<AllVehicle> allvehicle = new ArrayList<>();
        allvehicle.add(new AllVehicle("Suzuki WagonR 2018", "Car", "Auto", "Patrol","5 Seats", R.drawable.wagonr, "Rs. 100,000.00"));
        allvehicle.add(new AllVehicle("Toyota Premio 2021", "Car / Sedan", "Auto", "Patrol","5 Seats", R.drawable.premio, "Rs. 150,000.00"));
        allvehicle.add(new AllVehicle("Honda Grace 2018", "Car / Sedan", "Auto", "Patrol","5 Seats", R.drawable.grace, "Rs. 110,000.00"));
        allvehicle.add(new AllVehicle("Toyota Axio 2021", "Car / Sedan", "Auto", "Patrol","5 Seats", R.drawable.popularcar, "Rs. 120,000.00"));
        allvehicle.add(new AllVehicle("Toyota Hilux 2019", "Cab", "Manual", "Diesel","5 Seats", R.drawable.hilux, "Rs. 180,000.00"));
        allvehicle.add(new AllVehicle("Toyota Hiace 2009", "Van", "Auto", "Diesel","12 Seats", R.drawable.hiace, "Rs. 160,000.00"));
        allvehicle.add(new AllVehicle("Nissan Caravan 2005", "Van / Highroof", "Auto", "Diesel","18 Seats", R.drawable.popularavan, "Rs. 150,000.00"));
        allvehicle.add(new AllVehicle("Honda CR-V 2025", "Jeep / Hybrid", "Auto", "Patrol","5 Seats", R.drawable.populrajeep, "Rs. 200,000.00"));
        allvehicle.add(new AllVehicle("Nissan Xtrail 2015", "Jeep / Hybrid", "Auto", "Patrol","5 Seats", R.drawable.xtrail, "Rs. 200,000.00"));
        allvehicle.add(new AllVehicle("Kia Sorento 2011", "Jeep", "Auto", "Diesel","7 Seats", R.drawable.sorento,"Rs. 220,000.00"));
        allvehicle.add(new AllVehicle("Suzuki Every 2025", "Minivan / Wagon", "Auto", "Patrol","7 Seats", R.drawable.popularminivan, "Rs. 80,000.00"));

        AllVehicleAdapter alladapter = new AllVehicleAdapter(getContext(),allvehicle, allVehicle -> {
            Intent intent = new Intent(getContext(), VehicleDetailActivity.class);
            intent.putExtra("image", allVehicle.getImageResourceId());
            intent.putExtra("type", allVehicle.getTypes());
            intent.putExtra("model", allVehicle.getModel());
            intent.putExtra("fuelType", allVehicle.getFuelType());
            intent.putExtra("seats", allVehicle.getSeats());
            intent.putExtra("transmission", allVehicle.getTransmission());
            intent.putExtra("price", allVehicle.getPrice());
            getContext().startActivity(intent);
        });
        vehicleAllViews.setAdapter(alladapter);

        return view;
    }
}