package com.example.fuelpass;

public class ModelVehicleList {
    private String vehicleNo;
    private String vehicleType;
    private String chassisNo;
    private int fuelQuota;
    private int remainingQuota;

    //Constructor
    public ModelVehicleList(String vehicleNo, String vehicleType, String chassisNo, int fuelQuota, int remainingQuota){
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.chassisNo = chassisNo;
        this.fuelQuota = fuelQuota;
        this.remainingQuota = remainingQuota;
    }

    //Getters and Setters
    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public int getFuelQuota() {
        return fuelQuota;
    }

    public void setFuelQuota(int fuelQuota) {
        this.fuelQuota = fuelQuota;
    }

    public int getRemainingQuota() {
        return remainingQuota;
    }

    public void setRemainingQuota(int remainingQuota) {
        this.remainingQuota = remainingQuota;
    }
}
