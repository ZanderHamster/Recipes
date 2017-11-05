package com.android.bstu.recipes.recipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.bstu.recipes.MainActivity;
import com.android.bstu.recipes.R;
import com.android.bstu.recipes.RecipeModel;
import com.bluelinelabs.conductor.Controller;

public class RecipeController extends Controller {
    private View view;
    private RecipeModel recipe = new RecipeModel();
    private TextView tvComposition;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("Recipe", "onCreateView");
        view = inflater.inflate(R.layout.controller_recipe, container, false);
        createStubRecipe();
        tvComposition = view.findViewById(R.id.recipe_composition_view);
        configureToolbar();
        configureComposition();
        return view;
    }

    private void configureComposition() {
        tvComposition.setText(recipe.getComposition());
    }

    private void createStubRecipe() {
        recipe.setTitle(view.getResources().getString(R.string.demo_recipe_title));
        recipe.setComposition(view.getResources().getString(R.string.demo_recipe_composition));
    }

    private void configureToolbar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar_recipe);
        toolbar.setTitle(recipe.getTitle());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Recipe", "Toolbar back pressed");
                getRouter().handleBack();
            }
        });
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).closeNavigation();
        }
        Log.i("Recipe", "onDestroyView");
        super.onDestroyView(view);
    }
}
