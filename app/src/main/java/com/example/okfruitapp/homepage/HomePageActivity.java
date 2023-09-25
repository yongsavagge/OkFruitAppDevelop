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

public class HomePageActivity extends AppCompatActivity {

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
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        btnFotoTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la galería
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
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
