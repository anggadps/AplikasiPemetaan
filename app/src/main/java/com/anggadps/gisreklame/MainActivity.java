package com.anggadps.gisreklame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button myLoginImageButton;
    Button myMapsImageButton;
    Button myTampilDataImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLoginImageButton =(Button) findViewById(R.id.buttonLogin);
        myMapsImageButton =(Button) findViewById(R.id.buttonMaps);
        myTampilDataImageButton =(Button) findViewById(R.id.btntampildata);




        myLoginImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadLoginActivity = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intentLoadLoginActivity);
            }
        });

        myMapsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadMapsActivity = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intentLoadMapsActivity);
            }
        });

        myTampilDataImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoData = new Intent (MainActivity.this, Tampil.class);
                startActivity(gotoData);
            }
        });

    }
}
