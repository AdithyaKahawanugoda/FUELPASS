package com.example.fuelpass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * class AdapterFuelAvailability implements the setting up of data to the recyclerview of viewing
 * fuel availability
 */
public class AdapterFuelAvailability  extends RecyclerView.Adapter<AdapterFuelAvailability.ViewHolder>{
    final Context context;
    private final ArrayList<ModelFuel> fuelList;

    //Constructor
    public AdapterFuelAvailability(Context context, ArrayList<ModelFuel> fuelList){
        this.context = context;
        this.fuelList = fuelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout for each of the items in the recyclerview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fuel_availability, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFuelAvailability.ViewHolder holder, int position) {
        //value assignment to text views in the card layout
        ModelFuel fuel = fuelList.get(position);
        holder.fuelType.setText(fuel.getFuelType());
        holder.fuelStatus.setText(fuel.getFuelStatus());
    }

    @Override
    public int getItemCount() {
        //outputs the amounts of cards to be displayed in the recycler view
        return fuelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView fuelType;
        private final TextView fuelStatus;

        //initialize the text views
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fuelType = itemView.findViewById(R.id.fuelAvailabilityCard_TextViewR1);
            fuelStatus = itemView.findViewById(R.id.fuelAvailabilityCard_TextViewR2);
        }
    }
}
