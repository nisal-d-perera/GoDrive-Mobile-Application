package com.example.godriveapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>{

    private Context context;
    private List<Vehicle> vehicleList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Vehicle vehicle);
    }

    public VehicleAdapter(Context context, List<Vehicle> vehicleList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.vehicleList = vehicleList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.model.setText(vehicle.getModel());
        holder.type.setText(vehicle.getTypes());
        holder.auto.setText(vehicle.getAutomation());
        holder.gas.setText(vehicle.getGas());
        holder.seat.setText(vehicle.getSeats());
        holder.vehicleImage.setImageResource(vehicle.getImageResourceId());
        holder.bind(vehicle, onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
//        ImageView image;
        TextView model, type, gas, auto, seat;
        ImageView vehicleImage;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.vehicleModel);
            type = itemView.findViewById(R.id.vehicleType);
            gas = itemView.findViewById(R.id.vehicleGas);
            auto = itemView.findViewById(R.id.vehicleAuto);
            seat = itemView.findViewById(R.id.vehicleSeat);
            vehicleImage = itemView.findViewById(R.id.vehicleImage);
        }

        public void bind(Vehicle vehicle, OnItemClickListener onItemClickListener) {
            // Set data to views
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(vehicle));
        }
    }
}
