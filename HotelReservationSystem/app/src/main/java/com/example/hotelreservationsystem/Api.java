package com.example.hotelreservationsystem;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Api {
    public static ApiInterface getClient(){

        //change your base url
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://Hotelreservation-env.eba-avazyhss.us-east-1.elasticbeanstalk.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api;
    }
}
