package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LabWork1 extends AppCompatActivity {

    private Button btnLab1, btnLab2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_work1);

        btnLab1 = (Button) findViewById(R.id.btnLab1);
        btnLab2 = (Button) findViewById(R.id.btnLab2);

        btnLab1.setOnClickListener(btnLab1Click);
    }

    private View.OnClickListener btnLab1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (btnLab2.getVisibility() == View.INVISIBLE) {
                btnLab2.setVisibility(View.VISIBLE);
            }
            else btnLab2.setVisibility(View.INVISIBLE);

        };
    };
}
