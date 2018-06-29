package com.example.mrrobot.hola;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrrobot.hola.Services.ServiceLocation;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements
        MapboxMap.OnMapClickListener,OnMapReadyCallback
    {

    // attributes
    private MapView mapView;
    private MapboxMap mapboxMap;
    private Location myLocation;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // get my location lat and long
        this.myLocation= new ServiceLocation(getActivity()).getLocation();
        String token="pk.eyJ1IjoibXJtaWNoYWVsYm90IiwiYSI6ImNqZHpiamNnNzBwMXYycXA5cXh2M2xnZjcifQ.iqfPeoVbpWQcLG8bvf9qzw";
        Mapbox.getInstance(getActivity(), token);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // MAPBOX
        mapView = (MapView)view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }


    // MAP BOX listener

    @Override
    public void onMapClick(@NonNull LatLng point) {

    }

    // END MAP BOX listener
    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        //set camera
        this.mapboxMap=mapboxMap;
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude()))
                .zoom(10)                            // enable zoom feature
                .build();

        this.mapboxMap.setCameraPosition(cameraPosition);
        this.mapboxMap.setMinZoomPreference(10);
        this.mapboxMap.setMaxZoomPreference(16);
        // marker in my position
        LatLng my = new LatLng(this.myLocation.getLatitude(),this.myLocation.getLongitude());
        this.mapboxMap.addMarker(new MarkerOptions().position(my));


    }


    @Override
    public void onStart() {
        mapView.onStart();
        super.onStart();

    }

    @Override
    public void onResume() {
        this.mapView.onResume();
        super.onResume();

    }

    @Override
    public void onPause() {
        this.mapView.onPause();
        super.onPause();

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();

    }
    @Override
    public void onDestroy() {
        this.mapView.onDestroy();
        super.onDestroy();

    }

    }
