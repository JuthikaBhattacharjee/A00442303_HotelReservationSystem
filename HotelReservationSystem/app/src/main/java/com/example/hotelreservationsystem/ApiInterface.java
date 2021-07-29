package com.example.hotelreservationsystem;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiInterface {
    //@GET("/hotelsList")
    @GET("/hotelsList")
    public void getHotelLists(Callback<List<HotelListData>> callback);

}
