package com.example.godriveapp;

public class Vehicle {

    private String model;
    private String type;
    private String automation;
    private String gas;
    private String seats;
    private int imageResourceId;
    private String price;

    public Vehicle(String model, String type, String automation, String gas, String seats, int imageResourceId, String price) {
        this.model = model;
        this.type = type;
        this.automation = automation;
        this.gas = gas;
        this.seats = seats;
        this.imageResourceId = imageResourceId;
        this.price = price;
    }

    public String getModel() { return model; }
    public String getTypes() { return type; }
    public String getAutomation() { return automation; }
    public String getGas() { return gas; }
    public String getSeats() { return seats; }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public String getPrice() { return price; }
}
