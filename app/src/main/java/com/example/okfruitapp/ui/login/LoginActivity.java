package com.example.okfruitapp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.okfruitapp.R;
import com.example.okfruitapp.databinding.ActivityLoginBinding;
import com.example.okfruitapp.endpoints.UserService;
import com.example.okfruitapp.homepage.HomePageActivity;
import com.example.okfruitapp.models.LoginUser;
import com.example.okfruitapp.models.UserModel;
import com.example.okfruitapp.resultado.ResultadoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        com.example.okfruitapp.databinding.ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Llena los campos!!", Toast.LENGTH_LONG).show();
                }else {
                    validaLogin(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                }
            }
        });

        Button btnGoToRegistration = findViewById(R.id.btn_Register);

        btnGoToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("LoginActivity", "Botón 'Ir a Registro' hizo clic");

                // Crear un Intent para abrir la actividad UserRegistrationActivity

                Intent intent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
                startActivity(intent);
            }
        });



    }
    private void validaLogin(String name, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService UserService = retrofit.create(UserService.class);
        LoginUser modal = new LoginUser(name, password);
        Call<LoginUser> call= UserService.enviaCredenciales(modal);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response)  {

                if(response.isSuccessful()){
                    LoginUser loginNice = response.body();
                    if(loginNice.getNombre() != "" &&  loginNice.getContrasena() != ""){
                        updateUiWithUser(loginNice.getNombre());
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrecta!!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error con la conexion: "+response.errorBody(), Toast.LENGTH_LONG).show();
                    Log.d("TAG","Error: "+response.message());
                }


            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage()) ;
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUiWithUser(String nombre) {
        String welcome = "Bienvenido " + nombre+"!!";
        // TODO: initiate a successful logged-in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        redirectToHomePage();
    }

    private void redirectToHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
