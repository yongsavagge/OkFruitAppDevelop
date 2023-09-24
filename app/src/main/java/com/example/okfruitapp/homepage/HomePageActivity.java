package com.example.okfruitapp.homepage;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.example.okfruitapp.actualizar.ActualizarDatoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.okfruitapp.R;
import com.example.okfruitapp.resultado.ResultadoActivity;

public class HomePageActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Obtén una referencia al botón por su ID
        Button btnFotoSubir = findViewById(R.id.btn_foto);
        Button btnFotoTomar = findViewById(R.id.btn_foto2);
        com.google.android.material.floatingactionbutton.FloatingActionButton floatActualizar = findViewById(R.id.floatActualizar);

        // Configura el evento de clic en el botón
        btnFotoSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(HomePageActivity.this, ResultadoActivity.class);
                startActivity(intent);
            }
        });

        btnFotoTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(HomePageActivity.this, ResultadoActivity.class);
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
