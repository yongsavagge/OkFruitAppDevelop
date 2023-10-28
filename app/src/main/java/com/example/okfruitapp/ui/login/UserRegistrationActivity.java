package com.example.okfruitapp.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okfruitapp.R;
import com.example.okfruitapp.endpoints.UserService;
import com.example.okfruitapp.models.UserModel; // Importar la clase UserModel

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRegistrationActivity extends AppCompatActivity {
    private UserService userService; // Renombrar la variable a userService
    private ProgressBar loadingPB;

    private TextView responseTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        // initializing our views
        loadingPB = findViewById(R.id.idLoadingPB); // Asegúrate de que el ID coincida con el ProgressBar en tu diseño XML
        responseTV = findViewById(R.id.idTVResponse);

        Button enviarDatos = findViewById(R.id.Registrar);

        // adding on click listener to our button.
        enviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText correoEditText = findViewById(R.id.correoText);
                EditText passwordEditText = findViewById(R.id.passwordText);


                String email = correoEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Log.d("UserRegistrationActivity", "Email: " + email);

                // validating if the text field is empty or not.
                if (email.toString().isEmpty() && password.toString().isEmpty()) {
                    Toast.makeText(UserRegistrationActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(email, password);
            }
        });
    }
    private void postData(String email, String password) {

        // below line is for displaying our progress bar.
        if (loadingPB != null) {
            // Muestra el ProgressBar
            loadingPB.setVisibility(View.VISIBLE);
        }
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:5000/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        UserService UserService = retrofit.create(UserService.class);

        // passing data from our text fields to our modal class.
        UserModel modal = new UserModel(email, password);
        Log.d("UserModel", "Email: " + email);


        // calling a method to create a post and passing our modal class.
        Call<UserModel> call= UserService.sendUserData(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                // this method is called when we get response from our api.
                Toast.makeText(UserRegistrationActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.


                // we are getting response from our body
                // and passing it to our modal class.
                UserModel responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.

                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getEmail() + "\n" + "Job : " + responseFromAPI.getPassword();

                // below line we are setting our
                // string to our text view.
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
                Log.d("TAG", "onFailure: "+t.getMessage()) ;
                //holaaa
            }
        });
    }
}
