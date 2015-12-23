package com.google.davidsuzukinaturechallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.davidsuzukinaturechallenge.R;
import com.google.davidsuzukinaturechallenge.ui.fragments.NewSecretGardenFragment;

public class SecretGardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_garden);

        Button addNewGarden = (Button)findViewById(R.id.button_new_garden);
        Button storeGarden = (Button)findViewById(R.id.button_store_garden);

        addNewGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewSecretGardenFragment newSecretGardenFragment = new NewSecretGardenFragment();
                getFragmentManager().beginTransaction().add(android.R.id.content, newSecretGardenFragment).commit();
            }
        });

        storeGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewSecretGardenFragment newFragment = new NewSecretGardenFragment();
                getFragmentManager().beginTransaction().add(android.R.id.content, newFragment).commit();
            }
        });





    }



}
