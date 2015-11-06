package com.google.davidsuzukinaturechallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.davidsuzukinaturechallenge.R;

public class MainActivity extends AppCompatActivity {

    Button mGetMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetMapButton = (Button) findViewById(R.id.getMapButton);

        // TODO: check if location services are on
        mGetMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.google.davidsuzukinaturechallenge.ui.MapsActivity.class);
                startActivity(intent);
            }
        });
    }

}
