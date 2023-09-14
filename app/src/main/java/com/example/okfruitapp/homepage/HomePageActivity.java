package com.example.okfruitapp.homepage;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.okfruitapp.R;
import com.example.okfruitapp.resultado.ResultadoActivity;

public class HomePageActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Obtén una referencia al botón por su ID
        Button btnFoto = findViewById(R.id.btn_foto);

        // Configura el evento de clic en el botón
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(HomePageActivity.this, ResultadoActivity.class);
                startActivity(intent);
            }
        });
    }
}
