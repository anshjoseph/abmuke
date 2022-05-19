package com.example.abmuke01.API.Interface;

import com.example.abmuke01.API.Modelclass.UserLoginAPI_Model;
import com.example.abmuke01.API.Modelclass.SendData.UserLoginAPI_Payload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserLoginAPI {
    @POST("/login")
    Call<UserLoginAPI_Model> login(@Body UserLoginAPI_Payload body);
    @POST("/sigin")
    Call<UserLoginAPI_Model> sigin(@Body UserLoginAPI_Payload body);
}
