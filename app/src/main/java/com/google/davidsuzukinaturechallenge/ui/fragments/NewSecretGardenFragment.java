package com.google.davidsuzukinaturechallenge.ui.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.davidsuzukinaturechallenge.R;

/**
 * Created by jenna on 15-12-21.
 */
public class NewSecretGardenFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_garden, container, false);

        ImageButton addAnotherButton = (ImageButton)view.findViewById(R.id.button_add_another_garden);
        ImageButton doneAddingButton = (ImageButton)view.findViewById(R.id.button_done_adding_garden);


        addAnotherButton.setBackgroundResource(R.drawable.roundbutton);
        addAnotherButton.setBackgroundColor(Color.MAGENTA); // for some reason when this is deleted the background colour goes away..?!

        doneAddingButton.setBackgroundResource(R.drawable.roundbutton);
        doneAddingButton.setBackgroundColor(Color.MAGENTA);

        EditText editGardenName = (EditText) view.findViewById(R.id.edit_text_park_name);

        addAnotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // first save the new park

                // Then open a new fragment to enter another park
                int fragCount = getFragmentManager().getBackStackEntryCount();
                NewSecretGardenFieldsFragment newSecretGardenFieldsFragment = NewSecretGardenFieldsFragment.newInstance("#" + fragCount + ": Park Name");
                getFragmentManager().beginTransaction().replace(R.id.fields_container, newSecretGardenFieldsFragment).addToBackStack(null).commit();

            }
        });

        doneAddingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // commit parks to file/database
            }
        });



        return view;
    }

    @Override
    public void onPause() {
        // Save data here if necessary
        super.onPause();
    }




}
