package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GetInTouch extends AppCompatActivity {

    private TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_in_touch);

        details=findViewById(R.id.get_in_touch);
        String x;
        x="Click On the link below to visit our website :- \n" +
                "EXAMPLE : www.google.com";

        details.setText(x);
    }
}