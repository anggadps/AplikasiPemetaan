package com.anggadps.gisreklame.Api;

import com.anggadps.gisreklame.Model.ResponseApiModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiRequest {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseApiModel> login (@Field("user") String user,
                                @Field("pass") String pass);
}
