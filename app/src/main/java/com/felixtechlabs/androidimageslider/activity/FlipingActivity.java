package com.felixtechlabs.androidimageslider.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.felixtechlabs.androidimageslider.R;
import com.felixtechlabs.androidimageslider.adapter.ImageAdapter;

public class FlipingActivity extends AppCompatActivity {

    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fliping);

        ViewPager mViewPager = findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);

        btn_next = findViewById(R.id.button);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FlipingActivity.this,HomeActivity.class);
        startActivity(intent);

            }
        });

//
    }


}
