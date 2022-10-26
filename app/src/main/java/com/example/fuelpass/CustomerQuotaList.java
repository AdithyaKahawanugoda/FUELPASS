package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * class CustomerQuotaList models customer quota data in the app
 */
public class CustomerQuotaList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_quota_list);

        RecyclerView viewQuota = findViewById(R.id.view_quota_recycler);

        //Arraylist
        ArrayList<ModelVehicleList> vehicleList = new ArrayList<>();
        vehicleList.add(new ModelVehicleList("NA-5634","Car", "AZ-12-2345-123", 20, 12));
        vehicleList.add(new ModelVehicleList("NA-5635","Car", "AZ-12-2345-123", 20, 12));

        //initialize adapter and pass arraylist
        AdapterViewQuota viewQuotaAdapter = new AdapterViewQuota(this, vehicleList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //set layout manager and adapter to recycler view
        viewQuota.setLayoutManager(linearLayoutManager);
        viewQuota.setAdapter(viewQuotaAdapter);
    }
}