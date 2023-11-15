package com.example.okfruitapp.campo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;
import com.example.okfruitapp.homepage.HomePageActivity;

public class campoActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campo);

        Button btnVolver = findViewById(R.id.volverAtrasHome);

        ImageView imagenCampo = findViewById(R.id.btn_editar_campo);
        com.google.android.material.floatingactionbutton.FloatingActionButton floatAgregarCampo = findViewById(R.id.floatingActionAgregarCampo);

        // Establecer un OnClickListener para la imagen
        imagenCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Realizar la acción deseada al hacer clic en la imagen
                // Por ejemplo, redirigir a otra actividad
                Intent intent = new Intent(campoActivity.this, actualizarCampoActivity.class);
                startActivity(intent);
            }
        });
        floatAgregarCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(campoActivity.this, agregarCampoActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(campoActivity.this, ActualizarDatoActivity.class);
                startActivity(intent);
            }
        });
    }
}
