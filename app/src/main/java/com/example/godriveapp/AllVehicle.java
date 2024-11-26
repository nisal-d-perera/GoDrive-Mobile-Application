package com.example.godriveapp;

public class AllVehicle {

    private String model;
    private String type;
    private String transmission;
    private String fuelType;
    private String seats;
    private int imageResourceId;
    private String price;

    public AllVehicle(String model, String type, String transmission, String fuelType, String seats, int imageResourceId, String price) {
        this.model = model;
        this.type = type;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.seats = seats;
        this.imageResourceId = imageResourceId;
        this.price = price;
    }

    public String getModel() { return model; }
    public String getTypes() { return type; }
    public String getTransmission() { return transmission; }
    public String getFuelType() { return fuelType; }
    public String getSeats() { return seats; }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public String getPrice() { return price; }
}
