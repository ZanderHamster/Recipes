package com.android.bstu.recipes.animation;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.android.bstu.recipes.R;
import com.bluelinelabs.conductor.Controller;

public class AnimationController extends Controller {
    private View view;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("Animation", "onCreateView");
        view = inflater.inflate(R.layout.controller_animation, container, false);
        configureToolbar();
        configureAnimation();
        return view;
    }

    private void configureAnimation() {
        final ImageView sun = view.findViewById(R.id.animation_sun);
        final Animation sunRiseAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.sun_rise);

        Button startAnimation = view.findViewById(R.id.animation_start_btn);
        startAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Animation", "Sun start animation");
                sun.setVisibility(View.VISIBLE);
                sun.startAnimation(sunRiseAnimation);
            }
        });
    }

    private void configureToolbar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar_animation);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Animation", "Toolbar back pressed");
                getRouter().handleBack();
            }
        });
    }
}
