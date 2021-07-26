package com.example.hotelreservationsystem;

import retrofit.RestAdapter;
public class Api {
    public static ApiInterface getClient(){

        //change your base url

        RestAdapter adapter = new RestAdapter().Builder().setEndpoint("http://Hotelreservation-env.eba-avazyhss.us-east-1.elasticbeanstalk.com/")
                .build();
        ApiInterface api = adapter.create(ApiInterface.class);

        return api;
    }
}
