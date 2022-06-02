package com.example.abmuke01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;

import com.example.abmuke01.Fragments.audioView;
import com.example.abmuke01.Fragments.cameraView;
import com.example.abmuke01.utils.AsyncImageDownloader.AsyncDownloadImage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // sp
    SharedPreferences sharedPreferences;

    // ele in view
    private BottomNavigationView bottomNavigationView;
    private  ImageButton imageButton;
    private ImageView menubtn;


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
        sharedPreferences = getSharedPreferences("login",0);
        // set content view


        // default vars
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new audioView()).commit();
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        imageButton = findViewById(R.id.imageButton);
        menubtn = findViewById(R.id.profileicon);



        // menu button
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
        // default mic
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
//                Intent showres = new Intent(MainActivity.this,showResponse.class);
//                showres.putExtra("string","hello world");
//                startActivity(showres);

                Toast.makeText(MainActivity.this, "mic", Toast.LENGTH_SHORT).show();
            }
        });

        // menu btn
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

        View login = view.findViewById(R.id.login);
        TextView loginuser = view.findViewById(R.id.loginuser);
        TextView usermail = view.findViewById(R.id.useremail);
        TextView logintxt = view.findViewById(R.id.logintxt);

        if(sharedPreferences.getString("id",null)==null){
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,loginprocess.class));
                    finish();

                }
            });
        }else{

            loginuser.setText(sharedPreferences.getString("name",null));
            usermail.setText(sharedPreferences.getString("email",null));
            logintxt.setText("logout");
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor edi = sharedPreferences.edit();
                    edi.putString("name",null);
                    edi.putString("email",null);
                    edi.putString("id",null);
                    edi.commit();
                    loginuser.setText("Guest");
                    usermail.setText("guest@gmail.com");
                    logintxt.setText("login");

                }
            });

        }


    }

    private void audio(){
        audio = new audioView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,audio).commit();
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
//                Intent showres = new Intent(MainActivity.this,showResponse.class);
//                showres.putExtra("string","hello world");
//                startActivity(showres);
                Toast.makeText(MainActivity.this, "mic", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void camera(){
        camera = new cameraView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,camera).commit();
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



    // audio and login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case audioGet:
                if (data != null){
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                     responseTxt = result.get(0).toString();
                     responseStatus = true;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checktxt, viewGroup, false);
//
                    EditText input = dialogView.findViewById(R.id.editdialog);
                    input.setText(responseTxt);
                    View ok = dialogView.findViewById(R.id.ok);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent showres = new Intent(MainActivity.this,showResponse.class);
                            showres.putExtra("string",input.getText().toString());
                            startActivity(showres);
                        }
                    });

                    builder.setView(dialogView);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                break;
            case videoGet:
                if(data!=null){
                    videoUri = data.getData();
                    videoUriStatus = true;
                    Toast.makeText(this, "service is not aviable", Toast.LENGTH_SHORT).show();
                    camera.getCameraView(videoUri,videoUriStatus);
                }
                break;

        }
    }



}