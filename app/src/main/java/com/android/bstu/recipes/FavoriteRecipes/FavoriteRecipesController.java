package com.android.bstu.recipes.FavoriteRecipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bstu.recipes.MainActivity;
import com.android.bstu.recipes.R;
import com.bluelinelabs.conductor.Controller;

public class FavoriteRecipesController extends Controller {
    private Toolbar toolbar;
    private View view;
    private RecyclerView recycler;
    private FavoriteRecipesAdapter adapter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        view = inflater.inflate(R.layout.controller_favorite_recipes, container, false);
        configureToolbar();
        configureRecycler();

        return view;
    }

    private void configureRecycler() {
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }

    private void configureToolbar() {
        toolbar = view.findViewById(R.id.toolbar_favorite_recipes);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRouter().handleBack();
            }
        });
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).closeNavigation();
        }
        super.onDestroyView(view);
    }
}
