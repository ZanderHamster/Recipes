package com.android.bstu.recipes.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bstu.recipes.MainActivity;
import com.android.bstu.recipes.R;
import com.android.bstu.recipes.RecipeModel;
import com.android.bstu.recipes.recipe.RecipeController;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import java.util.ArrayList;
import java.util.List;

public class RecipesController extends Controller {
    private Toolbar toolbar;
    private View view;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("Recipes", "onCreateView");
        view = inflater.inflate(R.layout.controller_recipes, container, false);
        configureToolbar();
        configureRecycler();

        return view;
    }

    private void configureRecycler() {
        Log.i("FavoriteRecipes", "configureRecycler");
        RecipesAdapter adapter = new RecipesAdapter();
        adapter.setItems(getTestRecipes());
        adapter.setOnRecipeClickListener(new RecipesAdapter.RecipeClickListener() {
            @Override
            public void onClick(RecipeModel item) {
                Log.i("FavoriteRecipes", "recipeOnClick");
                getRouter().pushController(RouterTransaction.with(new RecipeController())
                        .popChangeHandler(new HorizontalChangeHandler())
                        .pushChangeHandler(new HorizontalChangeHandler()));
            }
        });
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }

    private List<RecipeModel> getTestRecipes() {
        Log.i("FavoriteRecipes", "getTestRecipes");
        List<RecipeModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RecipeModel recipe = new RecipeModel();
            recipe.setTitle("title " + i);
            if (i % 2 == 0) recipe.setFavorite(true);
            else recipe.setFavorite(false);
            recipe.setDescription("Description " + i);
            list.add(recipe);
        }
        return list;
    }

    private void configureToolbar() {
        toolbar = view.findViewById(R.id.toolbar_recipes);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Recipes", "Open drawer");
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).openNavigation();
                }
            }
        });
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).closeNavigation();
        }
        Log.i("Recipes", "onDestroyView");
        super.onDestroyView(view);
    }
}
