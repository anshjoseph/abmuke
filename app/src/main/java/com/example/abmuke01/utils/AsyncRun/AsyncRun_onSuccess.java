package com.example.abmuke01.utils.AsyncRun;

import com.example.abmuke01.API.Modelclass.AbmukeAPI_Model;

import retrofit2.Response;

public interface AsyncRun_onSuccess<T extends Comparable<T> >{


    public void run(Response<AbmukeAPI_Model> response);

}
