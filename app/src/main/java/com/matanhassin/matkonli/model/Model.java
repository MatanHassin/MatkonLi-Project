package com.matanhassin.matkonli.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

public class Model {

    public final static Model instance = new Model();
    ModelFirebase modelFirebase = new ModelFirebase();
    private Model(){
    }

    public interface Listener<T> {
        void onComplete(T data);
    }

@SuppressLint("StaticFieldLeak")
    public void addRecipe(final Recipe recipe, Listener<Boolean> listener){
    ModelFirebase.addRecipe(recipe, listener);
    new AsyncTask<String, String, String>() {
        @Override
        protected String doInBackground(String... strings) {
            AppLocalDb.db.RecipeDao().insertAllRecipes(recipe);
            return "";
        }
    }.execute();
    }

    public void updateUserProfile(String username, String profileImgUrl, Listener<Boolean> listener) {
        ModelFirebase.updateUserProfile(username, profileImgUrl, listener);
    }

    public void setUserAppData(String email) {
        ModelFirebase.setUserData(email);
    }

    public Recipe getRecipeById (String recipeId){
        Recipe recipe = null;
        return recipe;
    }
}
