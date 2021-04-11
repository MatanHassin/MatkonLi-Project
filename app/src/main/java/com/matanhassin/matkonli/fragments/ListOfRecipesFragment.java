package com.matanhassin.matkonli.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.matanhassin.matkonli.R;
import com.matanhassin.matkonli.model.Recipe;

import java.util.LinkedList;
import java.util.List;


public class ListOfRecipesFragment extends Fragment {

    String category;
    RecyclerView list;
    List<Recipe> data = new LinkedList<>();
    RecipeListAdapter adapter;
    Recipe_List_ViewModel viewModel;
    LiveData<List<Recipe>> liveData;
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

    static class RecipeViewHolder extends RecyclerView.ViewHolder{

        public RecipeViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
        }

    }
    interface OnItemClickListener {
        void onClick(int position);
    }

    class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

        private OnItemClickListener listener;

        void setOnClickListener(OnItemClickListener listener){ this.listener=listener; }

        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //create row
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.recipe_row_list,parent,false);
            RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view,listener);
            return recipeViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            // the connection between row to data
            //Recipe recipe = data.get(position);
            //holder.bind(recipe);
        }

        @Override
        public int getItemCount() {return 0;}
    }
}