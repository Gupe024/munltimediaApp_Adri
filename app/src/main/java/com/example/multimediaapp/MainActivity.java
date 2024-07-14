package com.example.multimediaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAudio;
    Button btnVideo;
    Button btnvideo2;
    Button btnvideo3;
    Button btnfotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAudio = findViewById(R.id.btn_audio);
        btnVideo = findViewById(R.id.btn_video1);
        btnvideo2 = findViewById(R.id.btn_video2);
        btnvideo3 = findViewById(R.id.btn_video3);
        btnfotos = findViewById(R.id.btn_fotos);

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, audio.class);
                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, video.class);
                startActivity(intent);
            }
        });

        btnvideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, video2.class);
                startActivity(intent);
            }
        });

        btnvideo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, video3.class);
                startActivity(intent);
            }
        });

        btnfotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, foto.class);
                startActivity(intent);
            }
        });

    }
}
