package com.google.davidsuzukinaturechallenge.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.davidsuzukinaturechallenge.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {

    EditText editNameText;
    EditText editEmailText;
    Button saveProfileButton;
    public static final String PROFILETEXT = "profile_info.txt";
    public static final String DEBUGTAG = "Debugging Profile";
    public static final String PROFILE = "Profile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editNameText = (EditText)findViewById(R.id.edit_text_name);
        editEmailText = (EditText)findViewById(R.id.edit_text_email);
        saveProfileButton = (Button)findViewById(R.id.button_save_profile);

        SharedPreferences prefs = getSharedPreferences(PROFILE, MODE_PRIVATE);
        String name = prefs.getString("user_name", "No name defined");//"No name defined" is the default value.
        String email = prefs.getString("user_email", "No email defined");

        if (name != null) {
            editNameText.setText(name);
        }

        if (email != null) {
            editEmailText.setText(email);
        }




        //loadSavedFile();

        saveProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = editNameText.getText().toString();
                String email = editEmailText.getText().toString();

                SharedPreferences prefs = getSharedPreferences(PROFILE, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("user_name", name);
                editor.putString("user_email", email);
                editor.commit();

                /**try {
                    FileOutputStream fos = openFileOutput(PROFILETEXT, Context.MODE_PRIVATE);
                     fos.write(name.getBytes());
                     fos.close();

                } catch (Exception e) {
                    Log.d(DEBUGTAG, "Unable to save file.");
                } **/


            }
        });

    }

    private void loadSavedFile() {
        try {
            FileInputStream fis = openFileInput(PROFILETEXT);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                    new DataInputStream(fis)));

            String line;
            while ((line = reader.readLine()) != null) {
                editNameText.setText(line);
            }

            fis.close();


        } catch (Exception e) {
            Log.d(DEBUGTAG, "Unable to read file.");
        }
    }


}
