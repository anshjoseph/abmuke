package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HowToReport extends AppCompatActivity {
    private TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_report);

        details=findViewById(R.id.Report_steps);

        String x="1. Click On login section \n" +
                 "2. Then click on the report section \n" +
                 "3. NOTE : you must login first before reporting problem \n" +
                 "4. Fillup your occuring reports \n" +
                 "5. Click on Submit_report button \n" +
                 "6. Reported successfully";

        details.setText(x);

    }
}