package com.example.okfruitapp.endpoints;

import com.example.okfruitapp.models.ImageModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {
    @Multipart
    @POST("/analisis")
    Call<ResponseBody> agregarFrutaEimagen(@Part ("tipo") RequestBody tipoFruta,
                                           @Part MultipartBody.Part imagen) ;
}