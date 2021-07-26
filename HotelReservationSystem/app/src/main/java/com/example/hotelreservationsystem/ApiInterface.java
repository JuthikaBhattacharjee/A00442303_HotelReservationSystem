package com.example.hotelreservationsystem;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiInterface {
    //@GET("/hotelsList")
    @GET("/hotelsList")
    public void getHotelLists(Callback<List<HotelListData>> callback);

}
