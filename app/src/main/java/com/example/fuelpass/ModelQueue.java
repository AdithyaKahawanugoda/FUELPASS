package com.example.fuelpass;

public class ModelQueue {
    String customerId;
    String stationId;
    String fuelType;
    String vehicleNo;
    String date;
    int arrivalTime;
    int exitTime;
    String visitStatus;

    //Constructor
    public ModelQueue(String customerId, String stationId, String fuelType, String vehicleNo, String date, int arrivalTime, int exitTime, String visitStatus){
        this.customerId = customerId;
        this.stationId = stationId;
        this.fuelType = fuelType;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.arrivalTime = arrivalTime;
        this.exitTime = exitTime;
        this.visitStatus =visitStatus;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getExitTime() {
        return exitTime;
    }

    public void setExitTime(int exitTime) {
        this.exitTime = exitTime;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

}
