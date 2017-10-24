package com.android.bstu.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

public class IngredientsController extends Controller {
    private Toolbar toolbar;
    private View view;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        view = inflater.inflate(R.layout.controller_ingredients, container, false);
        configureToolbar();

        return view;
    }

    private void configureToolbar() {
        toolbar = view.findViewById(R.id.toolbar_ingredients);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).openNavigation();
                }
            }
        });
    }
}
