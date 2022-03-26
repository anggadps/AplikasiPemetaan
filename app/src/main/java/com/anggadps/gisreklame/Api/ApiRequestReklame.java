package com.anggadps.gisreklame.Api;

import com.anggadps.gisreklame.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestReklame {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendReklame(@Field("nama_tempat") String nama_tempat,
                                    @Field("lat") String lat,
                                    @Field("lng") String lng,
                                    @Field("lokasi") String lokasi,
                                    @Field("keterangan") String keterangan);

    @GET("read.php")
    Call<ResponseModel> getReklame();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> updateData( @Field("id_tempat") String id_tempat,
                                    @Field("nama_tempat") String nama_tempat,
                                    @Field("lat") String lat,
                                    @Field("lng") String lng,
                                    @Field("lokasi") String lokasi,
                                    @Field("keterangan") String keterangan);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> deleteData(@Field("id_tempat")String id_tempat);
}
