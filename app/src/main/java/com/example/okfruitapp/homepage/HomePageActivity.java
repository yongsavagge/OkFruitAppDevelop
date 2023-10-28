package com.example.okfruitapp.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;
import com.example.okfruitapp.resultado.ResultadoActivity;

public class HomePageActivity extends AppCompatActivity  {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Obtén una referencia al botón por su ID
        Button btnDatos = findViewById(R.id.btn_dato);
        Button btnFotoSubir = findViewById(R.id.btn_foto);
        Button btnFotoTomar = findViewById(R.id.btn_foto2);
        com.google.android.material.floatingactionbutton.FloatingActionButton floatActualizar = findViewById(R.id.floatActualizar);

        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(HomePageActivity.this, ResultadoActivity.class);
                startActivity(intent);
            }
        });
//
        //btnFotoTomar.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        // Crea un Intent para redireccionar a la otra actividad
        //        Intent intent = new Intent(HomePageActivity.this, ResultadoActivity.class);
        //        startActivity(intent);
        //    }
        //});

        btnFotoSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la cámara
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnFotoTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la galería
                Intent pickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickImageIntent, REQUEST_IMAGE_PICK);
            }
        });

        floatActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(HomePageActivity.this, ActualizarDatoActivity.class);
                startActivity(intent);
            }
        });
        }
}
