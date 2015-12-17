package com.google.davidsuzukinaturechallenge.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.davidsuzukinaturechallenge.R;
import com.google.davidsuzukinaturechallenge.adapters.ParkAdapter;
import com.google.davidsuzukinaturechallenge.models.Park;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MapsActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;
    private Park[] mParkList;
    private LatLng mCurrentLatLng;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        toolbar.setTitle(""); // Take away label

        setUpMapIfNeeded();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_profile:
                Intent intent = new Intent(MapsActivity.this, com.google.davidsuzukinaturechallenge.ui.ProfileActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

            Log.i(TAG, "Location services null.");
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, "LocationWOO" + location.toString());

        // Get current location
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        mCurrentLatLng = new LatLng(currentLatitude, currentLongitude);

        // Fetch Parks data from API

        //String parksUrl = "https://api.namara.io/v0/data_sets/735b7e24-0fdb-4918-b112-5958725410a7/data/en-2?where=nearby(geometry," + currentLatitude + "," + currentLongitude + ",0.5km)&select=name,geometry&api_key=84f746dc871c40817621ec389dd49982fb3df4510451491a36cbad3c6ca4eab0";
        String parksUrl ="https://api.namara.io/v0/data_sets/735b7e24-0fdb-4918-b112-5958725410a7/data/en-2?api_key=896cf0acbd9737caa5546d04a0017d7c7d5ce30a58d78ec302dd42daadc42e59&nearby(geometry,43.653226,-79.3831843,0.5km)&select=name,geometry";

        callParksNetwork(parksUrl);




    }

    private void addMapMarkers() {

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLatLng, 15));
        mMap.addMarker(new MarkerOptions()
                .position(mCurrentLatLng)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        for (int i = 0; i < mParkList.length; i++) {
            Park park = mParkList[i];
            mMap.addMarker(new MarkerOptions().position(new LatLng(park.getLng(), park.getLat())).title(park.getName()));
        }

    }

    private void addParkListCards(){
        mRecyclerView = (RecyclerView)findViewById(R.id.map_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ParkAdapter(mParkList);
        mRecyclerView.setAdapter(mAdapter);
    }



    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    private void callParksNetwork(String parksUrl) {

        //TODO: Getting an SSL handshake error

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(parksUrl)
                    .build();

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Exception caught", e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.i(TAG, "Connected and responding");
                        if (response.isSuccessful()) {

                            // Create Park object
                            mParkList = parseParksDetails(jsonData);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addMapMarkers();
                                    addParkListCards();
                                }
                            });


                        } else {
                            //alertUserAboutError();
                            Log.e(TAG, "There was an error connecting");
                        }
                    }

                    catch (IOException e) {
                        Log.e(TAG, "Exception caught", e);
                    }

                    catch(JSONException e) {
                        Log.e(TAG, "Exception caught", e);
                    }
                }
            });

        }

        else {
            Toast.makeText(this, R.string.network_unavailable, Toast.LENGTH_LONG).show();
        }
    }

    private Park[] parseParksDetails(String jsonData) throws JSONException {

        JSONArray parkData = new JSONArray(jsonData);

        Park[] parkList = new Park[parkData.length()];

        for (int i = 0; i < parkData.length(); i++ ) {
            JSONObject jsonPark = parkData.getJSONObject(i);

            JSONObject jsonGeometry = jsonPark.getJSONObject("geometry");
            JSONArray jsonCoordinates = jsonGeometry.getJSONArray("coordinates");
            JSONArray coordinates = jsonCoordinates.getJSONArray(0);

            double latSum = 0;
            double lngSum = 0;
            int coordCount = 0;

            // get center of park from avg coordinates
            for (int j = 0; j < coordinates.length(); j++) {
                latSum += coordinates.getJSONArray(j).getDouble(0);
                lngSum += coordinates.getJSONArray(j).getDouble(1);
                coordCount += 1;
            }

            double latAvg = latSum/coordCount;
            double lngAvg = lngSum/coordCount;

            Park park = new Park();
            park.setName(jsonPark.getString("name"));
            park.setLat(latAvg);
            park.setLng(lngAvg);
            parkList[i] = park;
        }
        return parkList;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;

    }

}
