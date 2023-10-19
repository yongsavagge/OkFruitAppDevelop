package  com.example.okfruitapp.ui.login;

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

import com.example.okfruitapp.Module.Usuario;
import com.example.okfruitapp.databinding.ActivityLoginBinding;
import com.example.okfruitapp.homepage.HomePageActivity;
import com.example.okfruitapp.interFaces.UsuarioAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

         EditText usernameEditText = binding.username;
         EditText passwordEditText = binding.password;
         Button loginButton = binding.login;
         ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                setResult(Activity.RESULT_OK);
//
                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingProgressBar.setVisibility(View.VISIBLE);
                //loginViewModel.login(usernameEditText.getText().toString(),
                //        passwordEditText.getText().toString());

                // Se activa la funcion para enviar los datos de usuario y contraseña
                find(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    // *********** Funcion que activa la API y hace la peticion de las credenciales del Login
    private void find(String usernameEditText, String passwordEditText){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.94.98:5000")// IP de la API
                .addConverterFactory(GsonConverterFactory.create()).build();
        UsuarioAPI usuarioAPI = retrofit.create(UsuarioAPI.class);
        Call<Usuario> call = usuarioAPI.find(usernameEditText,passwordEditText);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {
                    // Se ingresa aca Siempre y cuando la peticion sea correcta
                    if(response.isSuccessful()) {
                        Usuario usuarios = response.body();

                        //Deberia retornar a este modulo si la conexion y el logeo es exitoso
                        //if(exito == ture){
                        //updateUiWithUser(NameUser);
                        //}else{
                        // Mensaje en caso de no ser exitoso
                        // showLoginFailed();
                        // }
                    }
                    //Envia informacion sobre errores
                }catch (Exception ex){
                    Toast.makeText(LoginActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                String errorMessage = t.getMessage();
                // Se envia informacion frente a la conexion de la API
                Log.e("Retrofit Error", errorMessage); // Imprime el mensaje de error en los logs de depuración
                Toast.makeText(LoginActivity.this, "Error de conexión: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    };

    private void updateUiWithUser(String Usuario) {
        String welcome = "Bienvenido " + Usuario;
        // TODO : initiate successful logged in experience
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