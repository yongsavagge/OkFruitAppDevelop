package com.example.okfruitapp.viewsImage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;

public class ViewImage extends AppCompatActivity {

    Button btnEnvia;
    ImageView imageView;
    Button volverAtras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        btnEnvia = findViewById(R.id.btnEnvia);
        imageView = findViewById(R.id.imageTEC);
        volverAtras = findViewById(R.id.volverAtras);
        Bitmap bitmap = getIntent().getParcelableExtra("bitmapImage");

        imageView.setImageBitmap(bitmap);

        volverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
