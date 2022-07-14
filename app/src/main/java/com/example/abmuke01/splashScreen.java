package com.example.abmuke01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

public class splashScreen extends AppCompatActivity {

    FadingTextView fadingTextView;
    String[] text
            = { "   ", "ABMUKE" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        fadingTextView = findViewById(R.id.titleicon);
//        fadingTextView.set
        fadingTextView.setTexts(text);
        fadingTextView.pause();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }, 3000);
    }
}