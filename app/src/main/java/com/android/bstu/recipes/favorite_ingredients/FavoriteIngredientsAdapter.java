package com.android.bstu.recipes.favorite_ingredients;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.bstu.recipes.IngredientModel;
import com.android.bstu.recipes.R;
import com.android.bstu.recipes.RecipeModel;

import java.util.List;

/**
 * Адаптер для отображения элементов списка "Избранных ингредиентов"
 */
public class FavoriteIngredientsAdapter extends RecyclerView.Adapter<FavoriteIngredientsAdapter.FavoriteIngredientsViewHolder> {

    private List<IngredientModel> items;

    /**
     * Создание отображения которое было создано в xml
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public FavoriteIngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("FavoriteIngredients", "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_favorite_ingredient, parent, false);
        return new FavoriteIngredientsViewHolder(view);
    }

    /**
     * Присвоение необходимых значений полям
     * @param holder - xml объект в котором мы будем устанавливать значения полям
     * @param position - номер объекта в общем списке
     */
    @Override
    public void onBindViewHolder(FavoriteIngredientsViewHolder holder, final int position) {
        Log.i("FavoriteIngredients", "onBindViewHolder");
        holder.textView.setText(items.get(position).getTitle());
        holder.imageView.setImageDrawable(
                holder.itemView.getResources().getDrawable(R.drawable.ingredient_icon_defaulte));
    }

    void setItems(List<IngredientModel> items) {
        Log.i("FavoriteIngredients", "setItems");
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FavoriteIngredientsViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        FavoriteIngredientsViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_view);
            imageView = itemView.findViewById(R.id.item_image_view);
        }
    }

}
