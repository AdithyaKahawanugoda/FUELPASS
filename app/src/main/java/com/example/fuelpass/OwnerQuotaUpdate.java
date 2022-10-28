package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class OwnerQuotaUpdate implements the functionality of when the station
 * owner updates the weekly fuel quotas per vehicle type.
 */
public class OwnerQuotaUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText newQuota;
    private String stationIdValue,vehicleTypeValue,newQuotaValue;
    private Button updateBtn;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.169:8082/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIQuotaManager apiQuotaManager = retrofit.create(APIQuotaManager.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_quota_update);

        Spinner vehicleTypesSpinner = findViewById(R.id.owner_quota_update_input1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.vehicle_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_list);
        // Apply the adapter and listener to the spinner
        vehicleTypesSpinner.setAdapter(adapter);
        vehicleTypesSpinner.setOnItemSelectedListener(this);

        newQuota = findViewById(R.id.owner_quota_update_input2);
        updateBtn = findViewById(R.id.owner_quota_update_btn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newQuotaValue = newQuota.getText().toString();
                if(vehicleTypeValue == null) {
                    Toast.makeText(getBaseContext(),"SELECT VEHICLE TYPE",Toast.LENGTH_SHORT).show();
                } else {
                    updateQuota();
                }
            }
        });
    }
    // triggers when spinner item selected by the user
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedValue = adapterView.getItemAtPosition(i).toString();

        if(!selectedValue.equals("Select Vehicle Type")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            vehicleTypeValue = selectedValue;
            stationIdValue= "635a2a2cc8a3556fe145297a";
            Toast.makeText(adapterView.getContext(),selectedValue,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void updateQuota() {
        Log.i("7526-REQ BODY",stationIdValue+" "+vehicleTypeValue+" "+newQuotaValue);
        ModelQuota modelQuota = new ModelQuota(stationIdValue,vehicleTypeValue,newQuotaValue);
        Call<ModelQuota> call = apiQuotaManager.updateQuota(stationIdValue, modelQuota);
        call.enqueue(new Callback<ModelQuota>() {
            @Override
            public void onResponse(Call<ModelQuota> call, Response<ModelQuota> response) {
                Toast.makeText(getBaseContext(),"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                Log.d("7526-RES OK",response+"");
            }

            @Override
            public void onFailure(Call<ModelQuota> call, Throwable t) {
                Toast.makeText(getBaseContext(),"FAILED",Toast.LENGTH_SHORT).show();
                Log.d("7526-RES ERR",t+"");
            }
        });
    }
}