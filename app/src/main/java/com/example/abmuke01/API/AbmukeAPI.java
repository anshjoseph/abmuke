package com.example.abmuke01.API;

import com.example.abmuke01.API.Modelclass.AbmukeAPI_Model;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onFail;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AbmukeAPI {
    private static String key = "ddb758dd74c008d6c16727cf50201ab24a342dac0214646a5ed574073106c9da";
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://projectisl.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private static com.example.abmuke01.API.Interface.AbmukeAPI api = retrofit.create(com.example.abmuke01.API.Interface.AbmukeAPI.class);

    public static void GetData(String text, AsyncRun_onSuccess onSuccess, AsyncRun_onFail onFail){
        Call<AbmukeAPI_Model> callAPI = api.getData(text,key);
        callAPI.enqueue(new Callback<AbmukeAPI_Model>() {
            @Override
            public void onResponse(Call<AbmukeAPI_Model> call, Response<AbmukeAPI_Model> response) {
                    onSuccess.run(response);
            }

            @Override
            public void onFailure(Call<AbmukeAPI_Model> call, Throwable t) {
                    onFail.run();
            }
        });
    }
}
