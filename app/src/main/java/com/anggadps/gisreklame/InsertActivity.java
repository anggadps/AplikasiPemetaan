package com.anggadps.gisreklame;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anggadps.gisreklame.Api.ApiRequestReklame;
import com.anggadps.gisreklame.Api.Retroserver;
import com.anggadps.gisreklame.Model.ResponseModel;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    EditText nama_tempat, lat, lng, lokasi, keterangan;
    Button btnsave, btntampildata, btnUpdate, btndelete;
    ProgressDialog pd;


    //Get location
    private static  final int REQUEST_LOCATION=1;
    Button getlocationBtn;

    LocationManager locationManager;
    String latitude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        nama_tempat = (EditText) findViewById(R.id.edtnama_tempat);
        lat = (EditText) findViewById(R.id.edtlat);
        lng = (EditText) findViewById(R.id.edtlng);
        lokasi = (EditText) findViewById(R.id.edtlokasi);
        keterangan = (EditText) findViewById(R.id.edtketerangan);
        btnsave = (Button) findViewById(R.id.btninsertdata);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btntampildata = (Button) findViewById(R.id.btntampildata);
        btndelete = (Button) findViewById(R.id.btndelete);


        //GET LOCATION

        //Add permission

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        getlocationBtn=findViewById(R.id.getLocation);

        getlocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //Check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then

                    getLocation();
                }
            }
        });






        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id_tempat");
        if (iddata != null) {
            btnsave.setVisibility(View.GONE);
            //btntampildata.setVisibility(View.GONE);//
            btnUpdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);

            nama_tempat.setText(data.getStringExtra("nama_tempat"));
            lat.setText(data.getStringExtra("lat"));
            lng.setText(data.getStringExtra("lng"));
            lokasi.setText(data.getStringExtra("lokasi"));
            keterangan.setText(data.getStringExtra("keterangan"));

        }
        pd = new ProgressDialog(this);


        btntampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godata = new Intent(InsertActivity.this, TampilData.class);
                startActivity(godata);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Loading hapus ...");
                pd.setCancelable(true);
                pd.show();

                ApiRequestReklame api = Retroserver.getClient().create(ApiRequestReklame.class);
                Call<ResponseModel> del = api.deleteData(iddata);
                del.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("Retro", "onResponse");
                        Toast.makeText(InsertActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent goTampil = new Intent(InsertActivity.this, TampilData.class);
                        startActivity(goTampil);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Update ...");
                pd.setCancelable(false);
                pd.show();

                ApiRequestReklame api = Retroserver.getClient().create(ApiRequestReklame.class);
                Call<ResponseModel> update = api.updateData(iddata, nama_tempat.getText().toString(),
                                                                    lat.getText().toString(),
                                                                    lng.getText().toString(),
                                                                    lokasi.getText().toString(),
                                                                    keterangan.getText().toString());
                update.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(InsertActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        pd.hide();
                        Intent goTampil = new Intent(InsertActivity.this, TampilData.class);
                        startActivity(goTampil);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "OnFailure");
                    }
                });
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data...");
                pd.setCancelable(false);
                pd.show();

                String snama_tempat = nama_tempat.getText().toString();
                String slat = lat.getText().toString();
                String slng = lng.getText().toString();
                String slokasi = lokasi.getText().toString();
                String sketerangan = keterangan.getText().toString();
                ApiRequestReklame api = Retroserver.getClient().create(ApiRequestReklame.class);

                Call<ResponseModel> sendrek = api.sendReklame(snama_tempat, slat, slng, slokasi, sketerangan);
                sendrek.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(InsertActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            Intent goTampil = new Intent(InsertActivity.this, TampilData.class);
                            startActivity(goTampil);
                        }else
                        {
                            Toast.makeText(InsertActivity.this, "Data error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Failure : " + "Gagal mengirim request");

                    }
                });

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home ){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(InsertActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(InsertActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lati=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lati);
                longitude=String.valueOf(longi);

                lat.setText(latitude);
                lng.setText(longitude);
            }
            else if (LocationNetwork !=null)
            {
                double lati=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lati);
                longitude=String.valueOf(longi);

                lat.setText(latitude);
                lng.setText(longitude);
            }
            else if (LocationPassive !=null)
            {
                double lati=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lati);
                longitude=String.valueOf(longi);

                lat.setText(latitude);
                lng.setText(longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}
