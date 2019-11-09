package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LabWork2 extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button sendRequestButton;
    TextView title;
    TextView bodyText;
    public static ProgressBar progressBar;

    private ModelPost publication;
    private IndicatingView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_work2);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        title = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);
        indicator = (IndicatingView) findViewById(R.id.generated_graphic);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sendRequest();
        }
    };

    private void sendRequest(){
        setIndicatorStatus(IndicatingView.SUCCESS);
        progressBar.setVisibility(View.VISIBLE);
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();
    }

    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publication != null){
                    title.setText(publication.getTitle());
                    bodyText.setText(publication.getBodyText());
                } else{
                    title.setText("");
                    bodyText.setText("");
                }
            }
        });
    }

    @Override
    public void success(ModelPost publication) {
        this.publication = publication;
        updatePublication();
        progressBar.setVisibility(View.INVISIBLE);
        setIndicatorStatus(IndicatingView.SUCCESS);
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        updatePublication();
        progressBar.setVisibility(View.INVISIBLE);
        setIndicatorStatus(IndicatingView.FAILED);
    }

    public void setIndicatorStatus(final int status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
            }
        });
    }
}
