package com.google.davidsuzukinaturechallenge.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        View view = inflater.inflate(R.layout.new_garden_fragment, container, false);

        EditText parkNameEditText = (EditText)view.findViewById(R.id.edit_text_park_name);
        EditText parkLatEditText = (EditText)view.findViewById(R.id.edit_text_park_lat);
        EditText parkLngEditText = (EditText)view.findViewById(R.id.edit_text_park_lng);
        Button addAnotherButton = (Button)view.findViewById(R.id.button_add_another_garden);
        Button doneAddingButton = (Button)view.findViewById(R.id.button_done_adding_garden);

        addAnotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewSecretGardenFragment newSecretGardenFragment = new NewSecretGardenFragment();
                getActivity().getFragmentManager().beginTransaction().add(android.R.id.content, newSecretGardenFragment).commit();
            }
        });

        doneAddingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
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
