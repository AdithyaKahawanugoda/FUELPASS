package com.example.fuelpass;

public class ModelAvailableFuel {

    private String id;
    private String fuelType;
    private String capacity;
    private String available;
    private String unitPrice;
    private String status;

    public ModelAvailableFuel(String id, String fuelType, String capacity, String available, String unitPrice, String status) {
        this.id = id;
        this.fuelType = fuelType;
        this.capacity = capacity;
        this.available = available;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
