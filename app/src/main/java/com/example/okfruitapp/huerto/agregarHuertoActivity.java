package com.example.okfruitapp.huerto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;

public class agregarHuertoActivity  extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actiivity_agregar_huerto);
        Button btnCancelar = findViewById(R.id.btn_CancelarHuerto);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(agregarHuertoActivity.this, huertoActivity.class);
                startActivity(intent);
            }
        });
    }
}
