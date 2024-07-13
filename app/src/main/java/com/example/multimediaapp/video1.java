package com.example.multimediaapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class video1 extends AppCompatActivity {
    VideoView videoView;
    ImageButton play;
    ImageButton pausa;
    ImageButton detener;
    ImageButton retroceder;
    ImageButton adelantar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        videoView = findViewById(R.id.btn_video1);
        play = findViewById(R.id.btn_play);
        pausa = findViewById(R.id.btn_pause);
        detener = findViewById(R.id.btn_retroceder);
        retroceder = findViewById(R.id.btn_retroceder);
        adelantar = findViewById(R.id.btn_adelantar);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.);
        videoView.setVideoURI(videoUri);
    }
}