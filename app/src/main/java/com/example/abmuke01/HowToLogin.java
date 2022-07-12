package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HowToLogin extends AppCompatActivity {
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_login);

           detail=findViewById(R.id.steps_of_login);

           String x;
           x="1. Go to Login section \n" +
             "2. Enter following Details \n" +
             "3. (Username, mail Id,Password) \n" +
             "4. Login Successfully";

           detail.setText(x);
    }
}