package com.example.okfruitapp.viewsImage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.MyApp;
import com.example.okfruitapp.R;
import com.example.okfruitapp.ui.login.UserRegistrationActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ViewImage extends AppCompatActivity {

    Button btnEnvia;
    ImageView imageView;
    Button volverAtras;
    Spinner tipoFruta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        btnEnvia = findViewById(R.id.btnEnvia);
        imageView = findViewById(R.id.imageTEC);
        tipoFruta = findViewById(R.id.tipoFruta);
        volverAtras = findViewById(R.id.volverAtras);
        Bitmap bitmap = getIntent().getParcelableExtra("bitmapImage");

        imageView.setImageBitmap(bitmap);

        btnEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarImagen(bitmap,tipoFruta.getSelectedItem().toString());
            }
        });

        volverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void enviarImagen(Bitmap bitmap, String tipoFruta) {
        File imageFile = convertirBitmapAFile(bitmap);

        if (imageFile == null) {
            Toast.makeText(ViewImage.this, "No sea guardado la imagen en el dispositivo!!", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageFile.getName(), RequestBody.create(MediaType.parse("image/*"), imageFile))
                .addFormDataPart("selecction", tipoFruta)
                .build();

        Request request = new Request.Builder()
                .url(MyApp.getBaseUrl() + "analisis")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            // Manejar la respuesta aquí
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File convertirBitmapAFile(Bitmap bitmap) {
        return null;
    }

    private Uri getImageUri(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
}
