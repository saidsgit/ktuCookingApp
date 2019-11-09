package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LabOverview extends AppCompatActivity {

    private Button btnLW1, btnLW2, btnLW3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_overview);

        btnLW1 = (Button) findViewById(R.id.btnLW1);
        btnLW2 = (Button) findViewById(R.id.btnLW2);
        btnLW3 = (Button) findViewById(R.id.btnLW3);

        btnLW1.setOnClickListener(LW1click);
        btnLW2.setOnClickListener(LW2click);
        btnLW3.setOnClickListener(LW3click);

    }

    private View.OnClickListener LW1click = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LabOverview.this, LabWork1.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener LW2click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LabOverview.this, LabWork2.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener LW3click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ;
        }
    };
}
