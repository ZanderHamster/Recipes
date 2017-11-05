package com.android.bstu.recipes.cooking_time;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.bstu.recipes.MainActivity;
import com.android.bstu.recipes.R;
import com.bluelinelabs.conductor.Controller;

import java.util.ArrayList;
import java.util.List;

public class CookingTimeController extends Controller {
    private View view;
    private LinearLayout cookingTimeLayout;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.i("CookingTime", "onCreateView");
        view = inflater.inflate(R.layout.controller_cooking_time, container, false);

        cookingTimeLayout = view.findViewById(R.id.cooking_time_layout);
        cookingTimeLayout.setBackgroundColor(Color.DKGRAY);

        configureToolbar();

        List<String[]> itemsPorridges = new ArrayList<>();
        itemsPorridges.add(new String[]{"", "Крупа", "Жид-ть", "Время варки"});
        itemsPorridges.add(new String[]{"Гречневая", "1 стакан", "2 стакана", "15-20 мин."});
        itemsPorridges.add(new String[]{"Манная", "1-2ст. ложки", "1 стакан", "4 мин."});
        itemsPorridges.add(new String[]{"Перловая", "1,5 стакана", "1 литр", "4 мин."});

        List<String[]> itemsMeat = new ArrayList<>();
        itemsMeat.add(new String[]{"", "Варка", "Жарение", "Тушение"});
        itemsMeat.add(new String[]{"Баранина", "1,5-2 ч.", "15-20 мин.", "1,5-2 ч."});
        itemsMeat.add(new String[]{"Говядина", "2-2,5 ч.", "20 мин.", "1,5-2 ч."});
        itemsMeat.add(new String[]{"Котлеты", "", "15-25 мин.", ""});

        createTable("Каши",itemsPorridges);
        createTable("Мясо",itemsMeat);

        return view;
    }

    private void createTable(String title,List<String[]> items) {
        Context context = getApplicationContext();
        TableLayout tableLayout = new TableLayout(getApplicationContext());
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tableLayout.setBackgroundColor(Color.DKGRAY);

        TableRow tbrowTitle = new TableRow(context);
        tbrowTitle.setWeightSum(1);
        tbrowTitle.setGravity(Gravity.CENTER);
        TextView tvTitle = new TextView(context);
        tvTitle.setText(title);
        tvTitle.setTextSize(20f);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextColor(Color.RED);
        tbrowTitle.addView(tvTitle);
        tableLayout.addView(tbrowTitle);
        for (int i = 0; i < items.size(); i++) {
            TableRow tbrow = new TableRow(context);
            tbrow.setWeightSum(4);
            TableRow.LayoutParams lp;
            lp = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f);
            lp.weight = 1;
            for (int j = 0; j < 4; j++) {
                TextView tv = new TextView(context);
                tv.setText(items.get(i)[j]);
                tv.setTextColor(Color.WHITE);
                tv.setLayoutParams(lp);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(10, 10, 10, 10);
                tbrow.addView(tv);
            }
            tableLayout.addView(tbrow);
        }
        cookingTimeLayout.addView(tableLayout);
    }

    private void configureToolbar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar_cooking_time);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CookingTime", "Toolbar back pressed");
                getRouter().handleBack();
            }
        });
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).closeNavigation();
        }
        Log.i("CookingTime", "onDestroyView");
        super.onDestroyView(view);
    }
}
