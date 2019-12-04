package com.example.ktucookingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Recipe> {

    public ListAdapter(Context context, List<Recipe> objects) {
        super(context, R.layout.listitem, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listitem, null);
        }

        TextView title = (TextView) v.findViewById(R.id.title);
        final TextView description = (TextView) v.findViewById(R.id.description);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView tvingredientsnumber = (TextView) v.findViewById(R.id.ingredientsnumber);
        TextView tvdifficulty = (TextView) v.findViewById(R.id.tvDifficulty);

        Recipe item = getItem(position);

        title.setText(item.getTitle());
        description.setText(item.getDescription());
        image.setImageResource(item.getImageId());
        tvingredientsnumber.setText(""+ item.getIngredients().length);
        tvdifficulty.setText((item.getDifficulty()));

        ImageButton btnShare = (ImageButton) v.findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setText("läuft");
            }
        });

        ImageButton btnYoutube = (ImageButton) v.findViewById(R.id.btnyoutube);
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setText("läuft");
            }
        });

        ImageButton btnEdit = (ImageButton) v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setText("läuft");
            }
        });

        ImageButton btnDelete = (ImageButton) v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Warning!");
                builder.setMessage("You want to delete recipe, bro?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "You've choosen to delete recipe", Toast.LENGTH_SHORT).show();
                        CookingApp.recipes.remove(CookingApp.position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "You've changed your mind, no problem bro", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });
        return v;

    }
}

