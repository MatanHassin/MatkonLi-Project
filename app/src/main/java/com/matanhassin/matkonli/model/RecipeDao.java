package com.matanhassin.matkonli.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("select * from Recipe")
    LiveData<List<Recipe>> getAllRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRecipes(Recipe...recipes);

    @Delete
    void deleteRecipe(Recipe recipe);
}
