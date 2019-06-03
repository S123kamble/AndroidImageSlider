package com.felixtechlabs.androidimageslider.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.felixtechlabs.androidimageslider.R;

public class HomeActivity extends AppCompatActivity {


    Button btnquation,btn_ans,btnedit,btncal,btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnquation = findViewById(R.id.question_paper);
        btnquation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,QuestionPaperActivity.class);
                startActivity(intent);

            }
        });
        btn_ans = findViewById(R.id.answer_booklet);
        btn_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AnswerBookletActivity.class);
                startActivity(intent);

            }
        });
        btnedit = findViewById(R.id.edit_tool);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,FullscreenActivity.class);
                startActivity(intent);

            }
        });
        btncal = findViewById(R.id.cal);
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CalculatorActivity.class);
                startActivity(intent);

            }
        });
        btn_start = findViewById(R.id.start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
