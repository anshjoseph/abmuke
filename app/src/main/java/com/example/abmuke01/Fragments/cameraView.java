package com.example.abmuke01.Fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.abmuke01.R;


public class cameraView extends Fragment {

    // var's
    private View view;
    private Uri videoUri;
    private boolean videoUriStatus = false;
    private VideoView camOut;


    public cameraView() {
        // Required empty public constructor
    }

    public void getCameraView(Uri videoUri,boolean videoUriStatus){
        this.videoUri = videoUri;
        this.videoUriStatus = videoUriStatus;

        if(videoUriStatus) {
            camOut.setVideoURI(videoUri);
            camOut.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mp.setVolume(0f,0f);
                    mp.start();
                }
            });
            Toast.makeText(getContext(), "set video", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_camera_view, container, false);
        camOut = view.findViewById(R.id.camOut);

        camOut.setVideoURI(Uri.parse("android.resource://com.example.abmuke01/"+R.raw.runvideo));
        camOut.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0f,0f);
                mp.start();
            }
        });
        return view;
    }
}