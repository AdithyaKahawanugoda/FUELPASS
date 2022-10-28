package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class OwnerPriceUpdate implements the functionality of when the station
 * owner updates the fuel prices.
 */
public class OwnerPriceUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<ModelAvailableFuel> availableFuelObj;
    private ModelStation resStation;
    private String newPriceValue,selectedFuelType;

    private TextView textView;
    private EditText editText;
    private Button updateBtn;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.169:8082/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIStationManager apiStationManager = retrofit.create(APIStationManager.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_price_update);
        resStation = new ModelStation();
        Call<ModelStation> call = apiStationManager.getStationData("635a27fcc8a3556fe1452979");
        call.enqueue(new Callback<ModelStation>() {
            @Override
            public void onResponse(Call<ModelStation> call, Response<ModelStation> response) {
                if(response.body() != null){
                    resStation.setStationName(response.body().getStationName());
                    resStation.setId(response.body().getId());
                    resStation.setStationAddress(response.body().getStationAddress());
                    availableFuelObj = response.body().getAvailableFuel();
                    Toast.makeText(getBaseContext(),"Data Loaded",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelStation> call, Throwable t) {
                Toast.makeText(getBaseContext(),"FAILED",Toast.LENGTH_SHORT).show();
            }
        });

        textView = findViewById(R.id.owner_price_update_input2);
        updateBtn = findViewById(R.id.owner_price_update_btn);
        editText = findViewById(R.id.owner_price_update_input3);

        Spinner fuelTypeSpinner = findViewById(R.id.owner_price_update_input1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.fuel_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_list);
        // Apply the adapter to the spinner
        fuelTypeSpinner.setAdapter(adapter);
        fuelTypeSpinner.setOnItemSelectedListener(this);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPriceValue = editText.getText().toString();
                if(newPriceValue == null) {
                    Toast.makeText(getBaseContext(),"SELECT FUEL TYPE",Toast.LENGTH_SHORT).show();
                } else {
                    updatePrice(newPriceValue,selectedFuelType);
                }
            }
        });
    }
    // triggers when spinner item selected by the user
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedFuelType = adapterView.getItemAtPosition(i).toString();

        if(!selectedFuelType.equals("Select Fuel Type")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            displaySelectedFuelOldPrice(selectedFuelType);        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void displaySelectedFuelOldPrice(String fuelType) {
        availableFuelObj.forEach((fuel)->{
            if(fuel.getFuelType().equals(fuelType)) {
                textView.setText(fuel.getUnitPrice());
            }
        });
    }

    public void updatePrice(String newPrice, String selectedType) {
        availableFuelObj.forEach((fuel)->{
            if(fuel.getFuelType().equals(selectedType)) {
                fuel.setUnitPrice(newPrice);
            }
            fuel.setId("001");
        });
        resStation.setAvailableFuel(availableFuelObj);

        ArrayList<ModelAvailableFuel> availableFuel = resStation.getAvailableFuel();
        availableFuel.forEach((n)->{Log.i("7526-CHK",n.getUnitPrice()+" "+n.getFuelType());});
        Call<ModelStation> call = apiStationManager.updateStation("635a27fcc8a3556fe1452979", resStation);
        call.enqueue(new Callback<ModelStation>() {
            @Override
            public void onResponse(Call<ModelStation> call, Response<ModelStation> response) {
                Toast.makeText(getBaseContext(),"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                Log.d("7526-RES OK",response+"");
                startActivity(new Intent(getApplicationContext(),OwnerHome.class));
            }

            @Override
            public void onFailure(Call<ModelStation> call, Throwable t) {
                Toast.makeText(getBaseContext(),"FAILED",Toast.LENGTH_SHORT).show();
            }
        });
    }
}