package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class nonlprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonlprofile);
    }
    public void contact(View view){startActivity(new Intent(this,ContactUs.class));}
    public void policy(View view){startActivity(new Intent(this,ClientPolicy.class));}
    public void help(View view){startActivity(new Intent(this,ClientHelp.class));}
    public void bug(View view){
        Toast.makeText(this, "you have to login fisrt", Toast.LENGTH_SHORT).show();
    }

    public void login(View view){
        startActivity(new Intent(this,loginprocess.class));
        finish();
    }
}