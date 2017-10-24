package com.android.bstu.recipes.FavoriteRecipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.bstu.recipes.R;

import java.util.List;

public class FavoriteRecipesAdapter extends RecyclerView.Adapter<FavoriteRecipesAdapter.FavoriteRecipesViewHolder> {

    private List<Recipe> items;
    private LayoutInflater inflater;

    @Override
    public FavoriteRecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_ingredient, parent, false);
        return new FavoriteRecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteRecipesViewHolder holder, int position) {
        holder.textView.setText(items.get(position).getTitle());
    }

    public void setItems(List<Recipe> items) {
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
