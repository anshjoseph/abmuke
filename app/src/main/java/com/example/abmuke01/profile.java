package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferences = getSharedPreferences("login",0);
        ((TextView)findViewById(R.id.textView8)).setText(sharedPreferences.getString("name",null));
        ((TextView)findViewById(R.id.textView9)).setText(sharedPreferences.getString("email",null));

    }
    public void pcontact(View view){startActivity(new Intent(this,ContactUs.class));}
    public void ppolicy(View view){startActivity(new Intent(this,ClientPolicy.class));}
    public void pbug(View view){ startActivity(new Intent(this,Clientreport.class)); }
    public void phelp(View view){startActivity(new Intent(this,ClientHelp.class));}
    public void plogout(View view){
        SharedPreferences.Editor edi = sharedPreferences.edit();
        edi.putString("name",null);
        edi.putString("email",null);
        edi.putString("id",null);
        edi.commit();
        startActivity(new Intent(this,nonlprofile.class));
        finish();
    }
}