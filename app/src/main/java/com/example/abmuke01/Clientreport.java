package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Clientreport extends AppCompatActivity {
    private WebView report ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientreport);
        report = findViewById(R.id.web_for_report);
        report.loadUrl("http://117.254.197.7:8000/report/");
        WebSettings webSettings = report.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}