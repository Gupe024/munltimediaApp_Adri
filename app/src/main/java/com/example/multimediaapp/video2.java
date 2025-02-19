package com.example.multimediaapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class video2 extends AppCompatActivity {

    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
    ImageButton play2;
    ImageButton pausa2;
    ImageButton detener2;
    ImageButton retroceder2;
    ImageButton adelantar2;
    Button regresar2;

    boolean Stopped = false;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_video2);

        surfaceView = findViewById(R.id.Surface_View);
        play2 = findViewById(R.id.btn_play2);
        pausa2 = findViewById(R.id.btn_pause2);
        detener2 = findViewById(R.id.btn_detener2);
        retroceder2 = findViewById(R.id.btn_retroceder2);
        adelantar2 = findViewById(R.id.btn_adelantar2);
        regresar2 = findViewById(R.id.btn_regresar2);

        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                configurarMediaPlayer();
                mediaPlayer.setDisplay(holder);
                mediaPlayer.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // Manejar los cambios de superficie si es necesario
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });

        mediaPlayer = new MediaPlayer();

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!mediaPlayer.isPlaying()) {
                        if (Stopped) {
                            configurarMediaPlayer();
                            Stopped = false;
                        }
                        mediaPlayer.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pausa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        detener2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        Stopped = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        retroceder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int posicion = mediaPlayer.getCurrentPosition();
                    if (posicion - 5000 > 0) {
                        mediaPlayer.seekTo(posicion - 5000);
                    } else {
                        mediaPlayer.seekTo(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        adelantar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int posicion = mediaPlayer.getCurrentPosition();
                    int duracion = mediaPlayer.getDuration();
                    if (posicion + 5000 < duracion) {
                        mediaPlayer.seekTo(posicion + 5000);
                    } else {
                        mediaPlayer.seekTo(duracion);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        regresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configurarMediaPlayer() {
        try {
            mediaPlayer.reset();
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.descendientes);
            mediaPlayer.setDataSource(this, videoUri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
