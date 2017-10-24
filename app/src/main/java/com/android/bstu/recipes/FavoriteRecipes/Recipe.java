package com.android.bstu.recipes.FavoriteRecipes;

import android.widget.ImageView;

public class Recipe {
    private String title;
    private String description;
    private ImageView image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
