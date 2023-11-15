package com.example.okfruitapp.viewsImage;

import android.annotation.SuppressLint;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.MyApp;
import com.example.okfruitapp.R;
import com.example.okfruitapp.endpoints.ImageService;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.net.Uri;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewImage extends AppCompatActivity {
    Uri imageUri;
    Button btnEnvia;
    ImageView imageView;
    Button volverAtras;
    private ImageService imageService;
    private String frutaSeleccionada;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        btnEnvia = findViewById(R.id.btnEnvia);
        imageView = findViewById(R.id.imageTEC);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        imageService = retrofit.create(ImageService.class);
        Spinner spinnerFruits  = findViewById(R.id.tipoFruta);
        volverAtras = findViewById(R.id.volverAtras);
        Bitmap bitmap = getIntent().getParcelableExtra("bitmapImage");
        String imageUriString = getIntent().getStringExtra("imageUri");

        if(imageUriString != null){
            imageUri = Uri.parse(imageUriString);
            Bitmap bitmapUri = convertUriToBitmap(imageUri);
            imageView.setImageBitmap(bitmapUri);
        }else if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }else{
            Toast.makeText(ViewImage.this, "Error Imagen!!!", Toast.LENGTH_SHORT).show();
        }


        btnEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri != null){
                    MultipartBody.Part imagePart = convertUriToMultipart(imageUri);
                    sendImageAndType(imagePart);
                }else if(bitmap != null){
                    MultipartBody.Part imagePart = convertBitmapToMultipart(bitmap);
                    sendImageAndType(imagePart);
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
        });


        volverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Definir las opciones del Spinner
        String[] fruits = { "arandanos", "cerezas"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fruits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFruits.setAdapter(adapter);

        spinnerFruits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Obtener la opción seleccionada
                frutaSeleccionada = (String) parentView.getItemAtPosition(position);
                // Imprimir en la consola (Logcat)
                Log.d("SpinnerSelection", "Fruta seleccionada: " + frutaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar el caso en que no se seleccione nada
            }
        });

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
    private Bitmap convertUriToBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    private void sendImageAndType(MultipartBody.Part imagePart) {
        if (frutaSeleccionada != null) {
            RequestBody tipoFruta = RequestBody.create(MediaType.parse("text/plain"), frutaSeleccionada);
            Call<ResponseBody> call = imageService.agregarFrutaEimagen(tipoFruta, imagePart);
            // Agregar mensajes de registro para verificar los datos antes de enviarlos
            Log.d("DataToSend", "Tipo de fruta: " + frutaSeleccionada);
            Log.d("DataToSend", "Imagen: " + imagePart.body().contentType().toString());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // La solicitud se completó con éxito, maneja la respuesta aquí
                    } else {
                        Toast.makeText(getApplicationContext(), "Error con la conexión: " + response.errorBody(), Toast.LENGTH_LONG).show();
                        Log.d("TAG", "Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("ERROR", "onFailure: " + t.getMessage());
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
