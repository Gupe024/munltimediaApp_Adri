package com.example.multimediaapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class video3 extends AppCompatActivity {

    VideoView videoReproductor;
    ImageButton play3;
    ImageButton pausa3;
    ImageButton detener3;
    ImageButton retroceder3;
    ImageButton adelantar3;
    Button regresar3;

    private boolean isStopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video3);

        videoReproductor = findViewById(R.id.video_reproductor);
        retroceder3 = findViewById(R.id.btn_retroceder3);
        pausa3 = findViewById(R.id.btn_pause3);
        play3 = findViewById(R.id.btn_play3);
        detener3 = findViewById(R.id.btn_detener3);
        adelantar3 = findViewById(R.id.btn_adelantar3);
        regresar3 = findViewById(R.id.btn_regresar3);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.el_misterio);
        videoReproductor.setVideoURI(uri);
        videoReproductor.setMediaController(new MediaController(this));
        videoReproductor.requestFocus();

        // Set click listeners
        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        pausa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseVideo();
            }
        });

        detener3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo();
            }
        });

        retroceder3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewindVideo();
            }
        });

        adelantar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardVideo();
            }
        });

        regresar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void playVideo() {
        if (isStopped) {
            videoReproductor.seekTo(0);
            isStopped = false;
        }
        if (!videoReproductor.isPlaying()) {
            videoReproductor.start();
        }
    }

    public void pauseVideo() {
        if (videoReproductor.isPlaying()) {
            videoReproductor.pause();
        }
    }

    public void stopVideo() {
        if (videoReproductor.isPlaying()) {
            videoReproductor.pause(); // Use pause() instead of stopPlayback()
            videoReproductor.seekTo(0); // Reset to start
            isStopped = true;
        }
    }

    public void rewindVideo() {
        int currentPosition = videoReproductor.getCurrentPosition();
        int seekTime = 5000; // 5 seconds
        videoReproductor.seekTo(Math.max(currentPosition - seekTime, 0));
    }

    public void forwardVideo() {
        int currentPosition = videoReproductor.getCurrentPosition();
        int seekTime = 5000; // 5 seconds
        videoReproductor.seekTo(Math.min(currentPosition + seekTime, videoReproductor.getDuration()));
    }
}
