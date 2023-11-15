package com.example.okfruitapp.campo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;

public class agregarCampoActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agregar_campo);

        Button btnCancelar = findViewById(R.id.btn_CancelarCampo);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(agregarCampoActivity.this, campoActivity.class);
                startActivity(intent);
            }
        });
    }
}
