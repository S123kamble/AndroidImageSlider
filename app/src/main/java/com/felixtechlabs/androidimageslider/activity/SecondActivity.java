package com.felixtechlabs.androidimageslider.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felixtechlabs.androidimageslider.R;

public class SecondActivity extends AppCompatActivity {

    public static Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
