package com.example.okfruitapp.endpoints;

import com.example.okfruitapp.models.UserModel;
import com.example.okfruitapp.models.LoginUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface UserService  {
    @POST("/add-usuario")
    Call<UserModel> sendUserData(@Body UserModel userModel  ) ;

    @POST("/logeo_user")
    Call<LoginUser> enviaCredenciales(@Body LoginUser loginUser);
}
//holaaa