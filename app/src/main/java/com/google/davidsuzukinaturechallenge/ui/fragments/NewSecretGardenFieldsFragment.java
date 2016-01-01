package com.google.davidsuzukinaturechallenge.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.davidsuzukinaturechallenge.R;

/**
 * Created by jenna on 15-12-23.
 */
public class NewSecretGardenFieldsFragment extends Fragment {

    String mHintText;

    /**
     * Create a new instance of MyFragment that will be initialized
     * with the given arguments.
     */
    static NewSecretGardenFieldsFragment newInstance(String hintText) {
        NewSecretGardenFieldsFragment f = new NewSecretGardenFieldsFragment();
        Bundle b = new Bundle();
        b.putString("hintText", hintText);
        f.setArguments(b);
        return f;
    }


    /**
     * During creation, if arguments have been supplied to the fragment
     * then parse those out.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mHintText = args.getString("hintText", mHintText);
        }

    }

    /**
     * Create the view for this fragment, using the arguments given to it.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_garden_fields, container, false);

        EditText parkNameEditText = (EditText)view.findViewById(R.id.edit_text_park_name);
        EditText parkLatEditText = (EditText)view.findViewById(R.id.edit_text_park_lat);
        EditText parkLngEditText = (EditText)view.findViewById(R.id.edit_text_park_lng);

        parkNameEditText.setHint(mHintText != null ? mHintText : "#1: Park Name");
        return view;
    }

    @Override
    public void onPause() {
        // Save data here if necessary
        super.onPause();
    }


}
