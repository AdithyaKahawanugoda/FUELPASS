package com.example.fuelpass;

import com.google.gson.annotations.SerializedName;

public class ModelQuota {

    private String id;

    private String vehicleType;

    private String allocatedAmount;

    public ModelQuota(String id, String vehicleType, String allocatedAmount) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.allocatedAmount = allocatedAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(String allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }
}
