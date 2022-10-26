package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class CustomerRefill implements the functionality of when the customer wants to search a station,
 * join a fuel queue, leave before pumping fuel or leave after pumping fuel.
 */
public class CustomerRefill extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button joinBtn, leaveBeforeBtn, leaveAfterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_refill);

        joinBtn = findViewById(R.id.customer_refill_btn1);
        leaveBeforeBtn = findViewById(R.id.customer_refill_btn2);
        leaveAfterBtn = findViewById(R.id.customer_refill_btn3);

        Spinner stationSpinner = findViewById(R.id.customer_refill_input1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.station_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_list);
        // Apply the adapter to the spinner
        stationSpinner.setAdapter(adapter);
        stationSpinner.setOnItemSelectedListener(this);

        RecyclerView pumpFuel = findViewById(R.id.customer_refill_recycler);
        //ArrayList
        ArrayList<ModelQueue> queueArrayList = new ArrayList<ModelQueue>();
        queueArrayList.add(new ModelQueue("CU001","A-1", "Petrol (95 Octane)", "NA-1234", "24/10/22", 8, 10, "Active"));
        queueArrayList.add(new ModelQueue("CU002","A-2", "Auto Diesel", "NA-1235", "24/10/22", 8, 10, "Active"));

        //initialize adapter and pass arraylist
        AdapterRefillFuel joinQueueAdapter = new AdapterRefillFuel(this, queueArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to recycler view
        pumpFuel.setLayoutManager(linearLayoutManager);
        pumpFuel.setAdapter(joinQueueAdapter);

        leaveAfterBtn.setEnabled(false);
        leaveBeforeBtn.setEnabled(false);

        joinBtn.setVisibility(View.VISIBLE);
        leaveAfterBtn.setVisibility(View.INVISIBLE);
        leaveBeforeBtn.setVisibility(View.INVISIBLE);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinBtn.setEnabled(false);
                leaveAfterBtn.setEnabled(true);
                leaveBeforeBtn.setEnabled(true);
                leaveAfterBtn.setVisibility(View.VISIBLE);
                leaveBeforeBtn.setVisibility(View.VISIBLE);
                joinBtn.setVisibility(View.INVISIBLE);
            }
        });

        leaveAfterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinBtn.setEnabled(true);
                startActivity(new Intent(getApplicationContext(),CustomerHome.class));
            }
        });

        leaveBeforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinBtn.setEnabled(true);
                startActivity(new Intent(getApplicationContext(),CustomerHome.class));
            }
        });

    }

    // triggers when spinner item selected by the user
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedValue = adapterView.getItemAtPosition(i).toString();

        if(!selectedValue.equals("Select Station")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            Toast.makeText(adapterView.getContext(),selectedValue,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}