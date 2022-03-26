package com.anggadps.gisreklame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView nama_tempat, lat, lng, lokasi, keterangan;
    ImageView gambar;
    Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama_tempat = findViewById(R.id.edtnama_tempat);

        //gambar = findViewById(R.id.imgplace);

        lat = findViewById(R.id.edtlat);
        lng = findViewById(R.id.edtlng);
        lokasi = findViewById(R.id.edtlokasi);
        keterangan = findViewById(R.id.edtketerangan);


        Intent data = getIntent();
        final String iddata = data.getStringExtra("id_tempat");
        if (iddata != null) {

            nama_tempat.setText(data.getStringExtra("nama_tempat"));



            lat.setText(data.getStringExtra("lat"));
            lng.setText(data.getStringExtra("lng"));
            lokasi.setText(data.getStringExtra("lokasi"));
            keterangan.setText(data.getStringExtra("keterangan"));

            latitude = Double.parseDouble(data.getStringExtra("lat"));
            longitude = Double.parseDouble(data.getStringExtra("lng"));

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng mLatLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(mLatLng).title("Lokasi Reklame"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,11.0f));
    }
}