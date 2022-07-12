package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_help);
    }

    public void how_to_use(View v)
    {
        startActivity(new Intent (this,HelpDetails.class));
    }

    public void how_to_login(View v)
    {
        startActivity(new Intent (this,HowToLogin.class));
    }
    public void how_to_report(View v)
    {
        startActivity(new Intent (this,HowToReport.class));
    }
    public void get_in_touch(View v)
    {
        startActivity(new Intent (this,GetInTouch.class));
    }
    public  void server_not_work(View v)
    {
        startActivity(new Intent (this,ServerNotWork.class));

    }
}