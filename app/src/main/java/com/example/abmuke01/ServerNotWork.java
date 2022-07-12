package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ServerNotWork extends AppCompatActivity {

    private TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_not_work);

        details = findViewById(R.id.sub_of_serverNotwork);
        String x;
        x="1. If your server dosen't work properly \n" +
                "2. Retry with your process of worling\n" +
                "3. If stilit doen't Work go to report section And report your problem";
        details.setText(x);
    }
}