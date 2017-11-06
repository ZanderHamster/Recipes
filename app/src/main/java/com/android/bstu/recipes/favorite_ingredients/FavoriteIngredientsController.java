package com.android.bstu.recipes.favorite_ingredients;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bstu.recipes.IngredientModel;
import com.android.bstu.recipes.MainActivity;
import com.android.bstu.recipes.R;
import com.android.bstu.recipes.RecipeModel;
import com.android.bstu.recipes.favorite_recipes.FavoriteRecipesAdapter;
import com.android.bstu.recipes.recipe.RecipeController;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Экран "Избранные ингредиенты"
 */
public class FavoriteIngredientsController extends Controller {
    private Toolbar toolbar;
    private View view;

    /**
     * Создание отображения которое было создано в xml
     *
     * @param inflater
     * @param container
     * @return
     */
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("FavoriteIngredients", "onCreateView");
        view = inflater.inflate(R.layout.controller_favorite_ingredients, container, false);
        configureToolbar();
        configureRecycler();
        return view;
    }

    /**
     * Настройка тублара
     */
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

    /**
     * Настройка списка элементов
     */
    private void configureRecycler() {
        Log.i("FavoriteIngredients", "configureRecycler");
        FavoriteIngredientsAdapter adapter = new FavoriteIngredientsAdapter();
        adapter.setItems(getTestIngredients());
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler.setAdapter(adapter);
    }

    /**
     * Создание фейковых элементов
     *
     * @return - список ингредиентов
     */
    private List<IngredientModel> getTestIngredients() {
        Log.i("FavoriteIngredients", "getTestIngredients");
        List<IngredientModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            IngredientModel ingredient = new IngredientModel();
            ingredient.setTitle("titleIngredient " + i);
            if (i % 2 == 0) ingredient.setFavorite(true);
            list.add(ingredient);
        }
        return list;
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
