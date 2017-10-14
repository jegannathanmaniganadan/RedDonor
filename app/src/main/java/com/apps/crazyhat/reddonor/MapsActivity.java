package com.apps.crazyhat.reddonor;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        
        Geocoder geocode = new Geocoder(this);
        String sheetResponse=null;
        new downloadBloodDonorsProfile(this,sheetResponse).execute("https://spreadsheets.google.com/tq?key=1XwTeXXmK2_QikunL41ZPazhd86cvQGbkugZDYmrOYRw");
        String address = "600113";
        double[] latlon = geocodeAddress(address,geocode);
        double[] latitude = new double [6];
        double[] longitude = new double [6];
        latitude[0] = 12.9535;latitude[1] = 12.9856;latitude[2] =  13.0084; latitude[3] = 13.0063;latitude[4] = 12.9002;
        longitude[0] = 80.2572;longitude[1] = 80.2572;longitude[2] =  80.2572; longitude[3] = 80.2572;longitude[4] = 80.2279;
        if(latlon != null){
             latitude[5] = latlon[0];
             longitude[5] = latlon[1];
        }
            for (int i = 0; i < 6; i++) {

                // Adding a marker
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude[i], longitude[i]))
                        .title("Hello Maps " + i);
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                // changing marker color
                if (i == 0)
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                if (i == 1)
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                if (i == 2)
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                if (i == 3)
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                if (i == 4)
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                if (i == 5)
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

                googleMap.addMarker(marker);

                // Move the camera to last position with a zoom level
                if (i == 4) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(latitude[4], longitude[4])).zoom(15).build();

                    googleMap.animateCamera(CameraUpdateFactory
                            .newCameraPosition(cameraPosition));
                }
            }

            // Add a marker in Sydney and move the camera
           /* LatLng paalavakkam = new LatLng(12.9535, 80.2572);
            mMap.addMarker(new MarkerOptions().position(paalavakkam).title("Marker in paalavakkam"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(paalavakkam));
            LatLng thiruvanmyur = new LatLng(12.9856, 80.2614);
            mMap.addMarker(new MarkerOptions().position(paalavakkam).title("Marker in thiruvanmyur"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(thiruvanmyur));*/

    }

    public static double[] geocodeAddress(String addressStr, Geocoder gc) {

        double[] latlon= new double[2];
        Address address = null;
        List<Address> addressList = null;
        try {
            if (!TextUtils.isEmpty(addressStr)) {
                addressList = gc.getFromLocationName(addressStr, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != addressList && addressList.size() > 0) {
            address = addressList.get(0);
            Log.d("in address",addressList.get(0).toString());
        }

        if (null != address && address.hasLatitude()
                && address.hasLongitude()) {

            latlon[0] = address.getLatitude();
            latlon[1] = address.getLongitude();
        }
        if(latlon.length >0){
            Log.d("in Postal","has some values");
            return latlon;
        }else{
            Log.d("In postal","has null values");
            return latlon;


        }
    }

}
