package com.example.fuelpass;

import java.util.ArrayList;

public class ModelStation {

    private String id;

    private String stationName;

    private String stationAddress;

    private ArrayList<ModelAvailableFuel> availableFuel;

    public ModelStation() {
    }

    public ModelStation(String id, String stationName, String stationAddress, ArrayList<ModelAvailableFuel> availableFuel) {
        this.id = id;
        this.stationName = stationName;
        this.stationAddress = stationAddress;
        this.availableFuel = availableFuel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public ArrayList<ModelAvailableFuel> getAvailableFuel() {
        return availableFuel;
    }

    public void setAvailableFuel(ArrayList<ModelAvailableFuel> availableFuel) {
        this.availableFuel = availableFuel;
    }
}
