package com.android.bstu.recipes.recipes;

import android.graphics.drawable.Drawable;
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

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private List<RecipeModel> items;

    private RecipeClickListener onRecipeClickListener;

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("RecipesAdapter", "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipesViewHolder holder, final int position) {
        Log.i("RecipesAdapter", "onBindViewHolder");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecipeClickListener.onClick(items.get(position));
            }
        });
        holder.textView.setText(items.get(position).getTitle());
        if (items.get(position).getFavorite()) {
            holder.favotive.setImageDrawable(holder.favotive.getResources().getDrawable(R.drawable.ic_star_black_24dp));
        } else {
            holder.favotive.setImageDrawable(holder.favotive.getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
        }
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
        Log.i("RecipesAdapter", "setItems");
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RecipesViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private ImageView favotive;

        RecipesViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_view);
            imageView = itemView.findViewById(R.id.item_image_view);
            favotive = itemView.findViewById(R.id.item_favorite_image);
        }
    }

}
