package com.example.mrrobot.hola;


import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mrrobot.hola.Services.ServiceLocation;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;



public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, OnMapReadyCallback,
        MapboxMap.OnMapClickListener
        {

    // attributes
    private MapView mapView;
    private MapboxMap mapboxMap;
    private Location myLocation;

    private FloatingActionsMenu floatingActionMenu;// menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);

        initUI();
        // get my location lat and long
        this.myLocation= new ServiceLocation(MainActivity.this).getLocation();
        String token="pk.eyJ1IjoibXJtaWNoYWVsYm90IiwiYSI6ImNqZHpiamNnNzBwMXYycXA5cXh2M2xnZjcifQ.iqfPeoVbpWQcLG8bvf9qzw";
        Mapbox.getInstance(this, token);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // set listener for menu


    }
    private void initUI(){
        mapView = (MapView) findViewById(R.id.mapView);
        floatingActionMenu = (FloatingActionsMenu) findViewById(R.id.floatingActionMenu);
    }
    @Override
    public void onClick(View view) {

    }
    // MAP BOX listener
    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        //set position of camera
        MainActivity.this.mapboxMap=mapboxMap;
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude()))
                .zoom(10)                            // enable zoom feature
                .build();

        this.mapboxMap.setCameraPosition(cameraPosition);
        // marker in my position
        LatLng my = new LatLng(this.myLocation.getLatitude(),this.myLocation.getLongitude());
        this.mapboxMap.addMarker(new MarkerOptions().position(my));


    }
    @Override
    public void onMapClick(@NonNull LatLng point) {

    }
    // END MAP BOX listener



    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}