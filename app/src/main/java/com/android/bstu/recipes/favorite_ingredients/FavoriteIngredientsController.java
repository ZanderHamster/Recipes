package com.android.bstu.recipes.favorite_ingredients;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bstu.recipes.MainActivity;
import com.android.bstu.recipes.R;
import com.bluelinelabs.conductor.Controller;

public class FavoriteIngredientsController extends Controller {
    private Toolbar toolbar;
    private View view;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("FavoriteIngredients", "onCreateView");
        view = inflater.inflate(R.layout.controller_favorite_ingredients, container, false);
        configureToolbar();

        return view;
    }

    private void configureToolbar() {
        toolbar = view.findViewById(R.id.toolbar_favorite_ingredients);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FavoriteIngredients", "Toolbar back pressed");
                getRouter().handleBack();
            }
        });
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).closeNavigation();
        }
        Log.i("FavoriteIngredients", "onDestroyView");
        super.onDestroyView(view);
    }
}
