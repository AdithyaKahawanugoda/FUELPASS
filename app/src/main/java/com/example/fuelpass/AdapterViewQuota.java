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
 * class ViewQuotaAdapter implements the setting up of data to the recyclerview of viewing the quota
 */
public class AdapterViewQuota extends RecyclerView.Adapter<AdapterViewQuota.ViewHolder> {
    final Context context;
    private final ArrayList<ModelVehicleList> vehicleList;

    //Constructor
    public AdapterViewQuota(Context context, ArrayList<ModelVehicleList> vehicleList){
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout for each of the items in the recyclerview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_quota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //value assignment to text views in the card layout
        ModelVehicleList vehicles = vehicleList.get(position);
        holder.vehicleNo.setText(vehicles.getVehicleNo());
        holder.allocatedQuota.setText(vehicles.getFuelQuota()+"");
        holder.remainingQuota.setText(vehicles.getRemainingQuota()+"");
    }

    @Override
    public int getItemCount() {
        //outputs the amounts of cards to be displayed in the recycler view
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView vehicleNo;
        private final TextView allocatedQuota;
        private final TextView remainingQuota;

        public ViewHolder(@NonNull View itemView) {
            //initialize the text views
            super(itemView);
            vehicleNo = itemView.findViewById(R.id.cardLayoutTextViewR1);
            allocatedQuota = itemView.findViewById(R.id.cardLayoutTextViewR2);
            remainingQuota = itemView.findViewById(R.id.cardLayoutTextViewR3);
        }
    }


}
