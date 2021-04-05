package com.matanhassin.matkonli.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.matanhassin.matkonli.R;

public class ListOfRecipesFragment extends Fragment {

    String category;
    RecyclerView list;

    public ListOfRecipesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_recipes, container, false);
        category = ListOfRecipesFragmentArgs.fromBundle(getArguments()).getCategory();

        list=view.findViewById(R.id.list_of_recipes_recycler_view);
        list.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);



        return view;
    }
}