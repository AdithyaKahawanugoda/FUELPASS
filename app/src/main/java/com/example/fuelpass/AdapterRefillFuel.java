package com.example.fuelpass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRefillFuel extends RecyclerView.Adapter<AdapterRefillFuel.ViewHolder> {
    final Context context;
    private final ArrayList<ModelQueue> queueArrayList;

    public AdapterRefillFuel(Context context, ArrayList<ModelQueue> queueArrayList){
        this.context = context;
        this.queueArrayList = queueArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout for each of the items in the recyclerview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fuel_refill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRefillFuel.ViewHolder holder, int position) {
        //assigning values to text views in the card layout
        ModelQueue queues = queueArrayList.get(position);
        holder.queueLength.setText(queueArrayList.size()+"");
        holder.fuelType.setText(queues.getFuelType());
        holder.fuelStatus.setText(queues.getVisitStatus());
        holder.timeSpent.setText(Integer.toString(queues.getExitTime()-queues.getArrivalTime()));
    }

    @Override
    public int getItemCount() {
        //display the number of cards in the recycler view
        return queueArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView timeSpent;
        private final TextView fuelStatus;
        private final TextView queueLength;
        private final TextView fuelType;

        public ViewHolder(@NonNull View itemView) {
            //initialize views
            super(itemView);
            fuelType = itemView.findViewById(R.id.pumpFuelCard_TextViewR1);
            queueLength = itemView.findViewById(R.id.pumpFuelCard_TextViewR2);
            fuelStatus = itemView.findViewById(R.id.pumpFuelCard_TextViewR3);
            timeSpent = itemView.findViewById(R.id.pumpFuelCard_TextViewR4);
        }
    }
}
