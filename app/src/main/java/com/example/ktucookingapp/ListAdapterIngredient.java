package com.example.ktucookingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListAdapterIngredient extends ArrayAdapter<String> {

    public ListAdapterIngredient(Context context, List<String> objects) {
        super(context, R.layout.listitem_ingredient, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listitem_ingredient, null);
        }

        String item = getItem(position);
        TextView ingr = (TextView) v.findViewById(R.id.tvAddedIngredient);
        ingr.setText(item);

        //Button to delete a ingredient
        Button deleteIngredient = (Button) v.findViewById(R.id.btnDeleteIngredient);
        deleteIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRecipe.addedIngredients.remove(position);
                notifyDataSetChanged();
                ListViewUtil.setListViewHeightBasedOnChildren(AddRecipe.listViewIngredients);
            }
        });

        return v;
    }
}
