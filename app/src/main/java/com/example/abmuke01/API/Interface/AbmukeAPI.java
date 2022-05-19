package com.example.abmuke01.API.Interface;

import com.example.abmuke01.API.Modelclass.AbmukeAPI_Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AbmukeAPI {
    @GET("/v1/isl/c01")
    public Call<AbmukeAPI_Model> getData(@Query("string") String string, @Query("key") String key);
}
