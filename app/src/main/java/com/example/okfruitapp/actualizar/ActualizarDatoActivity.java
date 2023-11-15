package com.example.okfruitapp.actualizar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.okfruitapp.R;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;
import com.example.okfruitapp.campo.campoActivity;
import com.example.okfruitapp.homepage.HomePageActivity;
import com.example.okfruitapp.huerto.huertoActivity;
import com.example.okfruitapp.resultado.ResultadoActivity;
import com.example.okfruitapp.viewsImage.ViewImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActualizarDatoActivity  extends AppCompatActivity  {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);
        Button btnHuerto = findViewById(R.id.btn_huerto);
        Button btnCampo = findViewById(R.id.btn_campo);
        Button btnVolver = findViewById(R.id.btn_volverAtrasHome);
        btnCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(ActualizarDatoActivity.this, campoActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(ActualizarDatoActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        btnHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(ActualizarDatoActivity.this, huertoActivity.class);
                startActivity(intent);
            }
        });

    }
}
