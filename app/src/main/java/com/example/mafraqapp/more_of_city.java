package com.example.mafraqapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class more_of_city extends AppCompatActivity implements OnMapReadyCallback {
    ImageView img ;
    TextView name , desc;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_of_city);
        img = findViewById(R.id.img2);
        Picasso.get().load(getIntent().getStringExtra("img")).into(img);
        name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        desc = findViewById(R.id.desc);
        desc.setText(getIntent().getStringExtra("desc"));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double lat = Double.parseDouble(getIntent().getStringExtra("lat").trim());
        double lng = Double.parseDouble(getIntent().getStringExtra("lng").trim());
        LatLng location = new LatLng(lat, lng);
        MarkerOptions marker = new MarkerOptions().position(location).title(name.getText().toString());
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
    }


}
