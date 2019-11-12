package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LabWork2 extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button sendRequestButton;
    TextView title;
    TextView bodyText;
    public static TextView tfPosts;
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
        tfPosts = (TextView) findViewById(R.id.tfPosts);
        indicator = (IndicatingView) findViewById(R.id.generated_graphic);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sendRequest();
        }
    };

    private void sendRequest() {
        setIndicatorStatus(IndicatingView.SUCCESS);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphaview);
        indicator.startAnimation(animation1);
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
        indicator.clearAnimation();
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

    @Override
    public void addPostsNumber(int countPosts) {
        tfPosts.setText("Request out of " + countPosts + " posts");
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
