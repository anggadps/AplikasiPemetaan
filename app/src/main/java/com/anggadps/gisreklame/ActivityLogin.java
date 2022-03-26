package com.anggadps.gisreklame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anggadps.gisreklame.Api.ApiRequest;
import com.anggadps.gisreklame.Api.RetroClient;
import com.anggadps.gisreklame.Model.ResponseApiModel;
import com.anggadps.gisreklame.Model.UserModel;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    private EditText edtUser,edtPass;
    private Button btnLogin;
    private ProgressDialog pd;
    private static final String TAG = ActivityLogin.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pd =new ProgressDialog(ActivityLogin.this);
        pd.setMessage("Loading...");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                ApiRequest Api = RetroClient.getRequestService();
                Call<ResponseApiModel> login = Api.login(edtUser.getText().toString(), edtPass.getText().toString());
                login.enqueue(new Callback<ResponseApiModel>() {
                    @Override
                    public void onResponse(Call<ResponseApiModel> call, Response<ResponseApiModel> response) {
                        pd.dismiss();
                        Log.d(TAG, "response : " + response.toString());
                        ResponseApiModel res = response.body();
                        List<UserModel> user = res.getResult();
                        if (res.getKode().equals("1")) {
                            Toast.makeText(ActivityLogin.this, "username/password ditemukan login sukses!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivityLogin.this, Main2Activity.class);
                            intent.putExtra("nama", user.get(0).getNamauser());
                            intent.putExtra("jk", user.get(0).getJk());
                            startActivity(intent);

                        }else
                            {
                                Toast.makeText(ActivityLogin.this, "username/password tidak cocok", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseApiModel> call, Throwable t) {
                        pd.dismiss();
                        Log.e(TAG, "error: " + t.getMessage());

                    }
                });
            }
        });
    }
}
