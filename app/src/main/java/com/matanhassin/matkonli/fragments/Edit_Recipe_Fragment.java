package com.matanhassin.matkonli.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.matanhassin.matkonli.R;
import com.matanhassin.matkonli.model.Recipe;

public class Edit_Recipe_Fragment extends Fragment {

    View view;
    Recipe recipe;
    EditText recipeTitle;
    EditText recipeIngredients;
    EditText recipeInstructions;
    Button saveChangesBtn;
    ImageView recipeImageView;
    Spinner chooseCategory;
    Uri recipeImageUri;
    Bitmap recipeImgBitmap;
    static int REQUEST_CODE = 1;

    public Edit_Recipe_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit__recipe_, container, false);

        recipeTitle = view.findViewById(R.id.Edit_Recipe_Recipe_title_edittext);
        recipeIngredients = view.findViewById(R.id.Edit_Recipe_Ingredients_edittext);
        recipeInstructions = view.findViewById(R.id.Edit_Recipe_Instructions_edittext);
        recipeImageView = view.findViewById(R.id.Edit_Recipe_Photo_imageview);
        chooseCategory = (Spinner) view.findViewById(R.id.Edit_Recipe_Spinner);

        return view;
    }
}