package com.matanhassin.matkonli.model;

public class Recipe {
    private String recipeId;
    private String recipeName;
    private String categoryId;
    private String recipeIngredients;
    private String recipeContent;
    private String recipeImageUrl;
    private String userId;
    private String username;
    private long lastUpdated;
    private double lat;
    private double lon;

    public Recipe(){
        recipeId = "";
        recipeName = "";
        categoryId = "";
        recipeIngredients = "";
        recipeContent = "";
        recipeImageUrl = "";
        userId = "";
        username = "";
        lastUpdated = 0;
    }
}
