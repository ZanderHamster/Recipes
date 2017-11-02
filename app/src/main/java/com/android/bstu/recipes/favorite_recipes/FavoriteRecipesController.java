package com.android.bstu.recipes.favorite_recipes;

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
import com.bluelinelabs.conductor.Controller;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipesController extends Controller {
    private View view;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("FavoriteRecipes", "onCreateView");
        view = inflater.inflate(R.layout.controller_favorite_recipes, container, false);
        configureToolbar();
        configureRecycler();

        return view;
    }

    private void configureRecycler() {
        Log.i("FavoriteRecipes", "configureRecycler");
        FavoriteRecipesAdapter adapter = new FavoriteRecipesAdapter();
        adapter.setItems(getTestRecipes());
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
            recipe.setDescription("Description " + i);
            list.add(recipe);
        }
        return list;
    }

    private void configureToolbar() {
        Log.i("FavoriteRecipes", "configureToolbar");
        Toolbar toolbar = view.findViewById(R.id.toolbar_favorite_recipes);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FavoriteRecipes", "Toolbar back pressed");
                getRouter().handleBack();
            }
        });
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).closeNavigation();
        }
        Log.i("FavoriteRecipes", "onDestroyView");
        super.onDestroyView(view);
    }
}
