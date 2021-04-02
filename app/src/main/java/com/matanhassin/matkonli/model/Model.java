package com.matanhassin.matkonli.model;

public class Model {

    public final static Model instance = new Model();

    private Model(){
    }

    public interface Listener<T> {
        void onComplete(T data);
    }
    public void addRecipe(final Recipe recipe, Listener<Boolean> listener){

    }

    public Recipe getRecipeById (String recipeId){
        Recipe recipe = null;
        return recipe;
    }
}
