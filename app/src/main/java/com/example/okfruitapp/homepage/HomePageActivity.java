package com.example.okfruitapp.homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.actualizar.ActualizarDatoActivity;
import com.example.okfruitapp.resultado.ResultadoActivity;
import com.example.okfruitapp.viewsImage.ViewImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HomePageActivity extends AppCompatActivity  {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_FROM_GALLERY = 2;
    private String imagePath;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Obtén una referencia al botón por su ID
        Button btnDatos = findViewById(R.id.btn_dato);
        Button btnFotoSubir = findViewById(R.id.btn_foto);
        Button btnFotoTomar = findViewById(R.id.btn_foto2);
        com.google.android.material.floatingactionbutton.FloatingActionButton floatActualizar = findViewById(R.id.floatActualizar);

        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para redireccionar a la otra actividad
                Intent intent = new Intent(HomePageActivity.this, ResultadoActivity.class);
                startActivity(intent);
            }
        });

        btnFotoSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la cámara
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


        btnFotoTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la galería
                Intent pickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickImageIntent, REQUEST_IMAGE_FROM_GALLERY);
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

        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_IMAGE_CAPTURE) {
                    Bundle extras = data.getExtras();
                    Bitmap imgBitmap = (Bitmap) extras.get("data");
                    // Enviar el bitmap a la otra actividad
                    Intent intent = new Intent(HomePageActivity.this, ViewImage.class);
                    intent.putExtra("bitmapImage", imgBitmap); // Agregar el bitmap como un extra al intent
                    startActivity(intent);
                } else if (requestCode == REQUEST_IMAGE_FROM_GALLERY) {
                    Uri selectedImage = data.getData();
                    String imageUriString = selectedImage.toString(); // Convertir Uri a String
                    Intent intent = new Intent(HomePageActivity.this, ViewImage.class);
                    intent.putExtra("imageUri", imageUriString); // Pasar la Uri como un String en el intent
                    startActivity(intent);
                }
            }

        }
    private MultipartBody.Part convertBitmapToMultipart(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // Cambia a JPEG
        byte[] byteArray = stream.toByteArray();

        // Comprueba el tipo de imagen
        String imageType = "image/png";

        RequestBody requestFile = RequestBody.create(MediaType.parse(imageType), byteArray);
        return MultipartBody.Part.createFormData("imagen", "image.png", requestFile); // Cambia el nombre del archivo si es necesario
    }

    private MultipartBody.Part convertUriToMultipart(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                byte[] fileBytes = getBytes(inputStream);

                RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(uri)), fileBytes);
                return MultipartBody.Part.createFormData("imagen", "image.png", requestFile);
            } else {
                // Maneja el caso en el que no se pudo abrir el flujo de entrada desde la Uri
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Aquí puedes agregar la lógica para cerrar la sesión
                        // Por ejemplo, puedes limpiar la información de la sesión y volver a la pantalla de inicio de sesión
                        finish(); // Cierra la actividad actual si el usuario confirma el cierre de sesión
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}

