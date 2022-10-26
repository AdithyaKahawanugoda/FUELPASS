package com.example.fuelpass;

/**
 * class ModelFuel models fuel data in the app
 */
public class ModelFuel {
    String fuelType;
    String fuelStatus;

    //Constructor
    public ModelFuel(String fuelType, String fuelStatus){
        this.fuelType = fuelType;
        this.fuelStatus = fuelStatus;
    }

    //getters and setters
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(String fuelStatus) {
        this.fuelStatus = fuelStatus;
    }
}
