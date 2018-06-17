package com.example.mrrobot.hola.Services;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

public class ServiceLocation extends Service implements LocationListener {


    private Location location;
    private Context ctx;
    private LocationManager locationManager;
    private boolean gpsActivo;

    //METHODS
    public ServiceLocation() {
        super();
        this.ctx = this.getApplicationContext();
    }

    public ServiceLocation(Context context) {
        super();
        this.ctx = context;
        this.locationConfig();
    }

    public Location getLocation() {
        return this.location;
    }

    private void locationConfig() {
        try {
            if (ActivityCompat.checkSelfPermission( this.ctx, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

                throw  new Exception("no permission");
            }

            LocationManager lm = (LocationManager) this.ctx.getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled){
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
                this.location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }else{
                throw  new Exception("GPS not enable");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}