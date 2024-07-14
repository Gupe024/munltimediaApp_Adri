package com.example.multimediaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class video extends AppCompatActivity {
    VideoView videoView;
    ImageButton play1;
    ImageButton pausa1;
    ImageButton detener1;
    ImageButton retroceder1;
    ImageButton adelantar1;
    Button regresar1;

    boolean Stopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.reproductor);
        play1 = findViewById(R.id.btn_play1);
        pausa1 = findViewById(R.id.btn_pause1);
        detener1 = findViewById(R.id.btn_detener1);
        retroceder1 = findViewById(R.id.btn_retroceder1);
        adelantar1 = findViewById(R.id.btn_adelantar1);
        regresar1 = findViewById(R.id.btn_regresar);

        configurarVideoView();

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!videoView.isPlaying()) {
                        if (Stopped) {
                            configurarVideoView();
                            Stopped = false;
                        }
                        videoView.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pausa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        detener1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (videoView.isPlaying()) {
                        videoView.stopPlayback();
                        Stopped = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        retroceder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int posicion = videoView.getCurrentPosition();
                    if (posicion - 5000 > 0) {
                        videoView.seekTo(posicion - 5000);
                    } else {
                        videoView.seekTo(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        adelantar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int posicion = videoView.getCurrentPosition();
                    int duracion = videoView.getDuration();
                    if (posicion + 5000 < duracion) {
                        videoView.seekTo(posicion + 5000);
                    } else {
                        videoView.seekTo(duracion);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        regresar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configurarVideoView() {
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.al_rescate);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null && videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
}