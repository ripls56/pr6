package com.example.pr6.Repository;

import com.example.pr6.Food;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("food")
    Call<ArrayList<Food>> getFoodList();

    @POST("food")
    Call<Food> setFood(@Body Food data);

    @DELETE("food/{id}")
    Call<Food> deleteFoodByID(@Path("id") int id);
}
