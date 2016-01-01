package com.google.davidsuzukinaturechallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.davidsuzukinaturechallenge.R;
import com.google.davidsuzukinaturechallenge.ui.fragments.NewSecretGardenFieldsFragment;
import com.google.davidsuzukinaturechallenge.ui.fragments.NewSecretGardenFragment;

public class SecretGardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_garden);

        final Button addNewGarden = (Button)findViewById(R.id.button_new_garden);
        Button storeGarden = (Button)findViewById(R.id.button_store_garden);


        addNewGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewGardens();
            }
        });

        storeGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void addNewGardens(){
        NewSecretGardenFragment newSecretGardenFragment = new NewSecretGardenFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, newSecretGardenFragment).addToBackStack(null).commit();


        NewSecretGardenFieldsFragment newSecretGardenFieldsFragment = new NewSecretGardenFieldsFragment();
        getFragmentManager().beginTransaction().replace(R.id.fields_container, newSecretGardenFieldsFragment).addToBackStack(null).commit();


        Log.i("buttons", "" + getFragmentManager().findFragmentById(android.R.id.content));
    }



}
