package com.matanhassin.matkonli.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.matanhassin.matkonli.R;


public class Profile_Fragment extends Fragment {

    TextView userName;
    TextView userEmail;
    ImageView userProfileImage;
    Button editProfileBtn;
    Button myRecipesBook;
    Button logoutBtn;

    public Profile_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        userName = view.findViewById(R.id.profile_page_username_text_view);
        userEmail = view.findViewById(R.id.profile_page_email_text_view);
        userProfileImage = view.findViewById(R.id.profile_page_profile_image_view);
        editProfileBtn = view.findViewById(R.id.profile_page_edit_profile_btn);
        myRecipesBook = view.findViewById(R.id.profile_page_my_recipes_book_btn);
        logoutBtn = view.findViewById(R.id.profile_page_logout_btn);
        return view;
    }
}