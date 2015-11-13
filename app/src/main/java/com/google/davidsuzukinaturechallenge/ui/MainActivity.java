package com.google.davidsuzukinaturechallenge.ui;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.davidsuzukinaturechallenge.R;

public class MainActivity extends AppCompatActivity {

    Button mGetMapButton;
    ImageView mHomeBackgroundImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetMapButton = (Button) findViewById(R.id.getMapButton);
        mHomeBackgroundImage = (ImageView) findViewById(R.id.homeBackgroundImage);


        Matrix m = mHomeBackgroundImage.getImageMatrix();
        RectF drawableRect = new RectF(0, 0, mHomeBackgroundImage.getWidth(), mHomeBackgroundImage.getHeight());
        RectF viewRect = new RectF(200, 200, 200, 200);
        m.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        float x = (float) -455.00;
        float y = (float) -155.00;
        m.setTranslate(x,y);
        mHomeBackgroundImage.setImageMatrix(m);



        // TODO: check if location services are on
        mGetMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ( checkGPS() == true ) {
                    Intent intent = new Intent(MainActivity.this, com.google.davidsuzukinaturechallenge.ui.MapsActivity.class);
                    startActivity(intent);
                }



            }
        });
    }

    public boolean checkGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            Toast.makeText(this, "Please enable your GPS", Toast.LENGTH_SHORT).show();
        }
        else {
            return true;
        }
        return false;
    }

}
