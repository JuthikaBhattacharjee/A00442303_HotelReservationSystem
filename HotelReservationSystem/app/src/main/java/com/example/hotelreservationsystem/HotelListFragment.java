package com.example.hotelreservationsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HotelListFragment extends Fragment {
    View view;
    TextView headingTextView;
    List<HotelListData> userListResponseData;
    ProgressBar progressBar;


    @Nullable
   // @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
        // I am passing the keys in the arguments to unlock the wrap from one page to another. If the
        //keys don't match, then we won't be able to access the information of the dates
        String checkInDate= getArguments().getString("check in date");
        String checkOutDate= getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        headingTextView.setText("Welcome user, displaying hotel for "+numberOfGuests+" guests staying from " +
                checkInDate +" to "+ checkOutDate);
        // Call the adapter from fragment
       /* ArrayList<HotelListData> hotelListData = inithotelListData();
        // I am going to bind it with recycler view
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recycler_view);
        //In order make recycler view work, we need to add set Layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Need to create constructor of the adapter where getAcitivity=current context/screen
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(),hotelListData);
        recyclerView.setAdapter(hotelListAdapter);*/
        getHotelListData();

    }
// It is oing to come from Dan, so this is temporary function

    // It will populate my screen
    //It is going to come from API Data
    public ArrayList<HotelListData> inithotelListData(){
        ArrayList<HotelListData> list = new ArrayList<>();

        list.add(new HotelListData("Halifax Hotel","$2000", "true"));
        return list;
    }

    public void getHotelListData(){
        progressBar.setVisibility(view.VISIBLE);
        Api.getClient().getHotelLists(new Callback<List<HotelListData>>() {
            @Override
            public void success(List<HotelListData> userlistResponse, Response response){
               userListResponseData= userlistResponse;

               progressBar.setVisibility(View.GONE);
               RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recycler_view);
               recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
               HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
               recyclerView.setAdapter(hotelListAdapter);

            }
            @Override
            public void failure(RetrofitError error){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG.show());
            }
        });
    }

    //public void setArguments(Bundle bundle) {
    //}
}
