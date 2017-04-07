package com.example.alec.positive_eating;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * @author Christian Shinkle
 * The CustomerMap class creates a Google Maps map and draws the directions needed to reach
 * the restaurant. It uses the Google Maps API for creating the map and a third-party library
 * written by Github user Akexorcist.
 */
public class CustomerMap extends AppCompatActivity implements OnMapReadyCallback,
        DirectionCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap googleMap;
    private String serverKey = "AIzaSyC9KxRvcZhsLFBf7ggjZN4zO1lOJCUoMy4";
    private LatLng camera = new LatLng(42.02, -93.66);
    private LatLng origin;
    private LatLng destination = new LatLng(42.011428, -93.679304);
    private GoogleApiClient client;
    private Location myLocation;
    /**
     * Creates boilerplate for Google Maps. Asynchronously retrieves data for the map and calls back
     * onMapReady once its completed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        createClient();
        /*
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        */
    }

    @Override
    protected void onStart() {
        client.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        client.disconnect();
        super.onStop();
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
    public void onMapReady(GoogleMap gMap) {
        googleMap = gMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 13));
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(CustomerMap.this,
                    "You have to accept to enjoy all the wonderful services of the app!",
                    Toast.LENGTH_LONG).show();
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            } else {
                finish();
            }
        }
        if(origin!=null) {
            GoogleDirection.withServerKey(serverKey)
                    .from(origin)
                    .to(destination)
                    .transportMode(TransportMode.DRIVING)
                    .execute(this);
        }
    }

    /**
     * If GoogleDirections was completed,
     *   organizes and draws lines for directions to destinations.
     * @param direction
     * @param rawBody
     */
    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        // Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (direction.isOK()) {
            googleMap.addMarker(new MarkerOptions().position(origin));
            googleMap.addMarker(new MarkerOptions().position(destination));

            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));

            // btnRequestDirection.setVisibility(View.GONE);
        }
    }

    /**
     * Catches exceptions thrown during creation of directions. This method was required for
     * interface, but not needed for this activity.
     * @param t
     */
    @Override
    public void onDirectionFailure(Throwable t) {
        // Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(getApplicationContext(),"We connected successfully!",
                Toast.LENGTH_LONG).show();
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            myLocation = LocationServices.FusedLocationApi.getLastLocation(client);
            origin = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(getApplicationContext(),
                    "You need to allow the app to use your location.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getApplicationContext(),"The connection was suspended.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),"The connections failed?",
                Toast.LENGTH_LONG).show();
    }

    private void createClient() {
        if (client == null) {
            client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        }
    }
}
