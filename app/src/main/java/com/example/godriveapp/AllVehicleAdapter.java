package com.example.godriveapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllVehicleAdapter extends RecyclerView.Adapter<AllVehicleAdapter.AllVehicleViewHolder>{

    private Context context;
    private List<AllVehicle> vehicleList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(AllVehicle allVehicle);
    }

    public AllVehicleAdapter(Context context, List<AllVehicle> vehicleList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.vehicleList = vehicleList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AllVehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_vehicle, parent, false);
        return new AllVehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllVehicleViewHolder holder, int position) {
        AllVehicle allvehicle = vehicleList.get(position);
        holder.model.setText(allvehicle.getModel());
        holder.type.setText(allvehicle.getTypes());
        holder.auto.setText(allvehicle.getTransmission());
        holder.gas.setText(allvehicle.getFuelType());
        holder.seat.setText(allvehicle.getSeats());
        holder.vehicleImage.setImageResource(allvehicle.getImageResourceId());
        holder.bind(allvehicle, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    static class AllVehicleViewHolder extends RecyclerView.ViewHolder {
        //        ImageView image;
        TextView model, type, gas, auto, seat;
        ImageView vehicleImage;

        public AllVehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.vehicleExModel);
            type = itemView.findViewById(R.id.vehicleExType);
            gas = itemView.findViewById(R.id.vehicleExGas);
            auto = itemView.findViewById(R.id.vehicleExAuto);
            seat = itemView.findViewById(R.id.vehicleExSeat);
            vehicleImage = itemView.findViewById(R.id.vehicleExImage);
        }
        public void bind(AllVehicle allVehicle, AllVehicleAdapter.OnItemClickListener onItemClickListener) {
            // Set data to views
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(allVehicle));
        }
    }
}
