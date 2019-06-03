package com.felixtechlabs.androidimageslider.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import com.felixtechlabs.androidimageslider.R;

public class VideoActivity extends AppCompatActivity {

    Button clik;
    VideoView videoV
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        clik = findViewById(R.id.paly);
        videoV = findViewById(R.id.videoView);
    }
}
