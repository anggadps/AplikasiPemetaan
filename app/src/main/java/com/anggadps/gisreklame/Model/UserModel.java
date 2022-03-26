package com.anggadps.gisreklame.Model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("nama")
    String namauser;
    @SerializedName("jenis_kelamin")
    String jk;
    @SerializedName("user")
    String user;

   public UserModel(){};

    public String getNamauser() {
        return namauser;
    }

    public void setNamauser(String namauser) {
        this.namauser = namauser;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
