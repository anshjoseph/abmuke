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
                String name = nickname.getText().toString();
                String emailid = email.getText().toString();
                String pass = password.getText().toString();
                AsyncRun_onSuccess success = new AsyncRun_onSuccess() {
                    @Override
                    public void run(Response response) {

                        UserLoginAPI_Model userLogin = (UserLoginAPI_Model) response.body();
                        if(userLogin.getCode() != 3){
                            SharedPreferences.Editor edi = sharedPreferences.edit();
                            edi.putString("name",name);
                            edi.putString("email",emailid);
                            UserLoginAPI.siginUser(name, emailid, new AsyncRun_onSuccess() {
                                @Override
                                public void run(Response response) {
                                    UserLoginAPI_Model userLoginS = (UserLoginAPI_Model) response.body();
                                    edi.putString("id",userLoginS.getAuthcode());
                                    edi.commit();
                                    startActivity(new Intent(loginprocess.this,MainActivity.class));
                                    finish();
                                }
                            }, new AsyncRun_onFail() {
                                @Override
                                public void run() {
                                    Toast.makeText(loginprocess.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else Toast.makeText(loginprocess.this, "User is alredy exit", Toast.LENGTH_SHORT).show();

                    }
                };
                AsyncRun_onFail fail = new AsyncRun_onFail() {
                    @Override
                    public void run() {
                        Toast.makeText(loginprocess.this, "Sorry network", Toast.LENGTH_SHORT).show();
                    }
                };
                UserLoginAPI.makeUser(emailid, pass,success,fail);

            }
        });
    }






}