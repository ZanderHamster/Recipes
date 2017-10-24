package com.android.bstu.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.android.bstu.recipes.FavoriteIngredients.FavoriteIngredientsController;
import com.android.bstu.recipes.FavoriteRecipes.FavoriteRecipesController;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

public class MainActivity extends AppCompatActivity {
    private Router router;
    private NavigationView navigationDrawer;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup container = findViewById(R.id.controller_container);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new IngredientsController()));
        }
        navigationDrawer = findViewById(R.id.navigationDrawer);
        drawerLayout = findViewById(R.id.drawerLayout);
        configureNavigation();
    }

    private void pushController(Controller controller) {
        router.pushController(RouterTransaction.with(controller)
                .popChangeHandler(new HorizontalChangeHandler())
                .pushChangeHandler(new HorizontalChangeHandler()));
        closeNavigation();
    }

    private void configureNavigation() {
        navigationDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_favorite_recipes:
                                pushController(new FavoriteRecipesController());
                                break;
                            case R.id.menu_favorite_ingredients:
                                pushController(new FavoriteIngredientsController());
                                break;
                            default:
                                break;

                        }
                        return false;
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationDrawer)) {
            closeNavigation();
            return;
        }
        if (!router.handleBack())
            super.onBackPressed();
    }

    public void closeNavigation() {
        drawerLayout.closeDrawer(Gravity.START);
    }

    public void openNavigation() {
        drawerLayout.openDrawer(Gravity.START);
    }
}
