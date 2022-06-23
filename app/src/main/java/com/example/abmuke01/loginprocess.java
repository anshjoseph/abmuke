package com.example.abmuke01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abmuke01.API.Modelclass.UserLoginAPI_Model;
import com.example.abmuke01.API.UserLoginAPI;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onFail;
import com.example.abmuke01.utils.AsyncRun.AsyncRun_onSuccess;


import retrofit2.Response;

public class loginprocess extends AppCompatActivity {
    EditText nickname,email,password;
    Button login;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginprocess);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);


        nickname = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // string
                String name = nickname.getText().toString();
                String emailid = email.getText().toString();
                String pass = password.getText().toString();


                AsyncRun_onSuccess success = new AsyncRun_onSuccess() {
                    @Override
                    public void run(Response response) {

                        UserLoginAPI_Model userLogin = (UserLoginAPI_Model) response.body();
                        if(userLogin.getCode() == 3){
                            SharedPreferences.Editor edi = sharedPreferences.edit();
                            edi.putString("name",name);
                            edi.putString("email",emailid);


                            // sigin action
                            UserLoginAPI.siginUser(emailid,pass,
                                    new AsyncRun_onSuccess() {
                                @Override
                                public void run(Response responseSigin) {
                                    UserLoginAPI_Model userSigin = (UserLoginAPI_Model) responseSigin.body();
                                    Log.d("sigin",userSigin.toString());

                                    if(userSigin.getCode()==5) {
                                        edi.putString("id", userSigin.getAuthcode());
                                        edi.commit();
                                        startActivity(new Intent(loginprocess.this, MainActivity.class));
                                        finish();
                                    }else
                                        Toast.makeText(loginprocess.this, "Auth error", Toast.LENGTH_SHORT).show();
                                }
                            },
                                    new AsyncRun_onFail() {
                                @Override
                                public void run() {
                                    Toast.makeText(loginprocess.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else Toast.makeText(loginprocess.this, "Some went wrong", Toast.LENGTH_SHORT).show();

                    }
                };
                AsyncRun_onFail fail = new AsyncRun_onFail() {
                    @Override
                    public void run() {
                        Toast.makeText(loginprocess.this, "Sorry network", Toast.LENGTH_SHORT).show();
                    }
                };



                // login api call
                UserLoginAPI.makeUser(emailid, pass,success,fail);

            }
        });
    }






}