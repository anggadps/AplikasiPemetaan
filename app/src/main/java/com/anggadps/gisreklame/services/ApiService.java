package com.anggadps.gisreklame.services;

import com.anggadps.gisreklame.models.ListLocationModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by putuguna on 30/09/17.
 */

public interface ApiService {
    @GET("JsonDisplayMarker.php")
    Call<ListLocationModel> getAllLocation();
}