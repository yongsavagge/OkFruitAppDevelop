package com.example.okfruitapp.interFaces;

import com.example.okfruitapp.Module.Usuario;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

// Clase donde se destinaran los distintos metodos para peticiones a la API
// Referentes al User
public interface UsuarioAPI {
    @POST("/get_usuarios/{username}/{password}")
    public Call<Usuario> find(@Path("username") String username, @Path("password") String password);
    //@POST("login")
    //Call<Usuario>LOGIN_CALL(
    //        @Field("username")String username,
    //        @Field("password")String password
    //);

}

