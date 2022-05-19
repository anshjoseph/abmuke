package com.example.abmuke01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;

import com.example.abmuke01.Fragments.audioView;
import com.example.abmuke01.Fragments.cameraView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // ele in view
    private BottomNavigationView bottomNavigationView;
    private  ImageButton imageButton;
    private ImageButton menubtn;


    // final var's
    private final int siginAuth =   100;
    private final int audioGet =    101;
    private final int videoGet =    102;

    // flag var's
        // audio to text
        private String responseTxt;
        private boolean responseStatus = false;
        // video
        private Uri videoUri;
        private boolean videoUriStatus =  false;

    // fragment var's
    audioView audio;
    cameraView camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        imageButton = findViewById(R.id.imageButton);
        menubtn = findViewById(R.id.imageButton3);



        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.menushow, viewGroup, false);
                // for view control
                MainActivity.this.menu(dialogView);
                //
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.audio:
                        audio();
                        return true;
                    case R.id.camera:
                        camera();
                        return true;
                }
                return false;
            }
        });





    }

    private void menu(View view){

    }

    private void audio(){
        audio = new audioView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,audio).commit();
        imageButton.setImageDrawable(getResources().getDrawable(R.drawable.mic));
        // mic button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                );


                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                // Note in display box
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");


                try {
                    startActivityForResult(intent, audioGet);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(MainActivity.this, "mic", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void camera(){
        camera = new cameraView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,camera).commit();
        imageButton.setImageDrawable(getResources().getDrawable(R.drawable.camera));
        // camera
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, videoGet);
                }
                Toast.makeText(MainActivity.this, "cam", Toast.LENGTH_SHORT).show();
            }
        });
    }



    // audio and google login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case audioGet:
                if (data != null){
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                     responseTxt = result.get(0).toString();
                     responseStatus = true;
                }
                break;
            case videoGet:
                if(data!=null){
                    videoUri = data.getData();
                    videoUriStatus = true;
                    Toast.makeText(this, "video incoming", Toast.LENGTH_SHORT).show();
                    camera.getCameraView(videoUri,videoUriStatus);
                }
        }
    }
}