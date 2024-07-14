package com.example.multimediaapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class foto extends AppCompatActivity {

    private static final int TOMAR_FOTO = 1;

    private ImageButton btnTomarFoto;
    private ImageView imagenVista;
    private Button regresarBtn;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    startCameraIntent();
                } else {
                    Toast.makeText(foto.this, "Permiso de cámara no concedido", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        imagenVista = findViewById(R.id.imagenVista);
        regresarBtn = findViewById(R.id.regresar_btn);

        btnTomarFoto.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCameraIntent();
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA);
            }
        });

        regresarBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, TOMAR_FOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TOMAR_FOTO && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    imagenVista.setImageBitmap(bitmap);
                }
            }
        }
    }
}
