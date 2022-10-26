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
 * ViewQuotaAdapter used to set up data items of recycler view in ViewQuota Activity
 */
public class AdapterViewQuota extends RecyclerView.Adapter<AdapterViewQuota.ViewHolder> {
    final Context context;
    private final ArrayList<ModelVehicleList> vehicleList;

    /**
     * Constructor
     *
     * @param context
     * @param vehicleList
     */
    public AdapterViewQuota(Context context, ArrayList<ModelVehicleList> vehicleList){
        this.context = context;
        this.vehicleList = vehicleList;
    }

    /**
     * Method is called when a new view needs to be created
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout for each of the items in the recyclerview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_quota, parent, false);
        return new ViewHolder(view);
    }

    /**
     *Method used to display data at the specified location
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //assigning values to text views in the card layout
        ModelVehicleList vehicles = vehicleList.get(position);
        holder.vehicleNo.setText(vehicles.getVehicleNo());
        holder.allocatedQuota.setText(vehicles.getFuelQuota()+"");
        holder.remainingQuota.setText(vehicles.getRemainingQuota()+"");
    }

    /**
     * Method returns the total number of items in data being held by adapter
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //display the number of cards in the recycler view
        return vehicleList.size();
    }

    /**
     * ViewHolder Class caches data and information about action item layouts' sub views
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView vehicleNo;
        private final TextView allocatedQuota;
        private final TextView remainingQuota;

        public ViewHolder(@NonNull View itemView) {
            //initialize views
            super(itemView);
            vehicleNo = itemView.findViewById(R.id.cardLayoutTextViewR1);
            allocatedQuota = itemView.findViewById(R.id.cardLayoutTextViewR2);
            remainingQuota = itemView.findViewById(R.id.cardLayoutTextViewR3);
        }
    }


}
