package com.example.ktucookingapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LabWork3 extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManger;
    private Sensor senAccelerometer;

    private Button startAndStop, btnStartCompass;

    private TextView xValue, yValue, zValue;
    private TextView orientation;

    private boolean InformationObtained;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_work3);

        InformationObtained = false;

        startAndStop = (Button) findViewById(R.id.start_and_stop);
        startAndStop.setOnClickListener(StartAndStopButtonListener);
        btnStartCompass = (Button) findViewById(R.id.startCompass);
        btnStartCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Compass.class);
                startActivity(intent);
            }
        });

        xValue = (TextView) findViewById(R.id.x_value);
        yValue = (TextView) findViewById(R.id.y_value);
        zValue = (TextView) findViewById(R.id.z_value);
        orientation = (TextView) findViewById(R.id.tvOrientation);


        senSensorManger = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    View.OnClickListener StartAndStopButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (senAccelerometer == null){
                Toast.makeText(LabWork3.this, "no_sensor", Toast.LENGTH_LONG).show();
            }

            if (InformationObtained){
                startAndStop.setText("start");
                senSensorManger.unregisterListener(LabWork3.this, senAccelerometer);
                InformationObtained = false;
            }

            else{
                senSensorManger.registerListener(LabWork3.this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                startAndStop.setText("stop");
                InformationObtained = true;
            }
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            xValue.setText(String.valueOf(x));
            yValue.setText(String.valueOf(y));
            zValue.setText(String.valueOf(z));

            //right down
            if (x < -8) {
                orientation.setText("right down");
            }
            //left down
            else if (x > 8) {
                orientation.setText("left down");
            }
            //top down
            else if (y < -8) {
                orientation.setText("top down");
            }
            //bottom down
            else if (y > 8) {
                orientation.setText("bottom down");
            }
            //screen down
            else if (z < -8) {
                orientation.setText("screen down");
            }
            //screen up
            else if (z > 8) {
                orientation.setText("screen up");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause(){
        super.onPause();
        if (senAccelerometer != null){
            senSensorManger.unregisterListener(LabWork3.this, senAccelerometer);
        }
    }

    @Override
    protected void onResume (){
        super.onResume();

        if(senAccelerometer != null && InformationObtained){
            senSensorManger.registerListener(LabWork3.this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
