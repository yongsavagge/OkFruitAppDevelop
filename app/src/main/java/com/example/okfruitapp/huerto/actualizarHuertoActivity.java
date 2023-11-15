package com.example.okfruitapp.huerto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;

public class actualizarHuertoActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actualizar_huerto);
        // Prueba
        Button btnCancelar = findViewById(R.id.btn_CancelarCampo);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(actualizarHuertoActivity.this, huertoActivity.class);
                startActivity(intent);
            }
        });
    }

}
