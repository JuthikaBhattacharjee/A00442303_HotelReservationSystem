package com.example.hotelreservationsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {
    // AT first, view holder is created and connected with the fragment views.
    // Then, the hotel_list_layout is being inflated into this
    //Then, the value and positions are fixed
    //Count keeps track of the number of entries of data for a particular hotel
    private ArrayList<HotelListData> hotelListData;
    private LayoutInflater layoutInflater;
// Here, context is the current state / screen
    HotelListAdapter(Context context, ArrayList<HotelListData> hotelListData){
        this.layoutInflater = LayoutInflater.from(context);
        this.hotelListData = hotelListData;
    }


    @NonNull
    //@org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_list_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.ViewHolder holder, int position) {
         String hotelName= hotelListData.get(position).getHotelName();
         String hotelPrice =hotelListData.get(position).getPrice();
         String hotelAvailability= hotelListData.get(position).getAvailability();

         holder.hotelName.setText(hotelName);
         holder.hotelPrice.setText(hotelPrice);
         holder.hotelAvailability.setText(hotelAvailability);
    }

    @Override
    public int getItemCount() {
        if (hotelListData!=null){
            return hotelListData.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView hotelName, hotelPrice, hotelAvailability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name_text_view);
            hotelPrice= itemView.findViewById(R.id.price_text_view);
            hotelAvailability = itemView.findViewById(R.id.availability_text_view);

        }
    }
}
