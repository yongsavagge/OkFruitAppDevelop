package com.example.okfruitapp.huerto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;
import com.example.okfruitapp.campo.agregarCampoActivity;
import com.example.okfruitapp.campo.campoActivity;

public class huertoActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_huerto);
        ImageView imagenCampo = findViewById(R.id.btn_editar_huerto);
        Button btnVolver = findViewById(R.id.volverAtrasHome);
        com.google.android.material.floatingactionbutton.FloatingActionButton floatAgregarHuerto = findViewById(R.id.floatingActionAgregarHuerto);
        floatAgregarHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(huertoActivity.this, agregarHuertoActivity.class);
                startActivity(intent);
            }
        });

        floatAgregarHuerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(huertoActivity.this, agregarHuertoActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(huertoActivity.this, ActualizarDatoActivity.class);
                startActivity(intent);
            }
        });
    }
}
