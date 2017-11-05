package com.android.bstu.recipes.favorite_recipes;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.bstu.recipes.R;
import com.android.bstu.recipes.RecipeModel;

import java.util.List;

public class FavoriteRecipesAdapter extends RecyclerView.Adapter<FavoriteRecipesAdapter.FavoriteRecipesViewHolder> {

    private List<RecipeModel> items;

    private RecipeClickListener onRecipeClickListener;
    @Override
    public FavoriteRecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("FavoriteRecipesAdapter", "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_favorite_recipe, parent, false);
        return new FavoriteRecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteRecipesViewHolder holder, final int position) {
        Log.i("FavoriteRecipesAdapter", "onBindViewHolder");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecipeClickListener.onClick(items.get(position));
            }
        });
        holder.textView.setText(items.get(position).getTitle());
            holder.imageView.setImageDrawable(
                    holder.itemView.getResources().getDrawable(R.drawable.recipe_icon_defaulte));
    }
    void setOnRecipeClickListener(RecipeClickListener listener) {
        this.onRecipeClickListener = listener;
    }
    interface RecipeClickListener {
        void onClick(RecipeModel item);
    }

    void setItems(List<RecipeModel> items) {
        Log.i("FavoriteRecipesAdapter", "setItems");
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FavoriteRecipesViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        FavoriteRecipesViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_view);
            imageView = itemView.findViewById(R.id.item_image_view);
        }
    }

}
