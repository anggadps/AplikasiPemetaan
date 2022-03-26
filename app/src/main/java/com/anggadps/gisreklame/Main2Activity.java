package com.anggadps.gisreklame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ImageButton myMapsImageButton, myListImageButton;
    TextView nama, jk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nama = (TextView) findViewById(R.id.tvNama);
        //jk  = (TextView) findViewById(R.id.tvJk);//
        myMapsImageButton =(ImageButton) findViewById(R.id.buttonMaps);
        myListImageButton =(ImageButton) findViewById(R.id.buttonList);

        Intent data = getIntent();

        nama.setText(data.getExtras().getString("nama"));
        //jk.setText(data.getExtras().getString("jk"));//

        myMapsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadMapsActivity = new Intent(Main2Activity.this, MapsActivity.class);
                startActivity(intentLoadMapsActivity);
            }
        });

        myListImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadListActivity = new Intent(Main2Activity.this, TampilData.class);
                startActivity(intentLoadListActivity);
            }
        });
    }
}
