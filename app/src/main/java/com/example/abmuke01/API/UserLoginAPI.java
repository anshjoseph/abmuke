package com.example.abmuke01.API;

import com.example.abmuke01.API.Modelclass.SendData.UserLoginAPI_Payload;
import com.example.abmuke01.API.Modelclass.UserLoginAPI_Model;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onFail;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginAPI {
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://login-auth24.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private static com.example.abmuke01.API.Interface.UserLoginAPI api = retrofit.create(com.example.abmuke01.API.Interface.UserLoginAPI.class);


    public static void siginUser(String username, String password, AsyncRun_onSuccess onSuccess, AsyncRun_onFail onFail){
        Call<UserLoginAPI_Model> response = api.login(new UserLoginAPI_Payload(username,password));
        response.enqueue(new Callback<UserLoginAPI_Model>() {
            @Override
            public void onResponse(Call<UserLoginAPI_Model> call, Response<UserLoginAPI_Model> response) {
                onSuccess.run(response);
            }

            @Override
            public void onFailure(Call<UserLoginAPI_Model> call, Throwable t) {
                onFail.run();
            }
        });
    }
    public static void makeUser(String username, String password, AsyncRun_onSuccess onSuccess, AsyncRun_onFail onFail){
        Call<UserLoginAPI_Model> response = api.sigin(new UserLoginAPI_Payload(username,password));
        response.enqueue(new Callback<UserLoginAPI_Model>() {
            @Override
            public void onResponse(Call<UserLoginAPI_Model> call, Response<UserLoginAPI_Model> response) {
                onSuccess.run(response);
            }

            @Override
            public void onFailure(Call<UserLoginAPI_Model> call, Throwable t) {
                onFail.run();
            }
        });
    }
}
