package com.matanhassin.matkonli;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListOfRecipesFragment extends Fragment {

    String category;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_recipes, container, false);

        category = ListOfRecipesFragmentArgs.fromBundle(getArguments()).getCategory();

        Log log = null;
        log.d("TAG","arg_category"+ category);

        return view;
    }
}