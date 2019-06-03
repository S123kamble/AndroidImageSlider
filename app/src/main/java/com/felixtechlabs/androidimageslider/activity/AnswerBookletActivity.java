package com.felixtechlabs.androidimageslider.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.felixtechlabs.androidimageslider.R;

public class AnswerBookletActivity extends BaseActivity {

    public static final int READ_WRITE_STORAGE = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_booklet);

        Button btn_next1 = findViewById(R.id.buttonLoadPicture);
        btn_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    startActivity(new Intent(AnswerBookletActivity.this, SelectImageActivity.class));
                }
            }
        });
    }
    public boolean requestPermission(String permission) {
        boolean isGranted = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{permission},
                    READ_WRITE_STORAGE);
        }
        return isGranted;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_WRITE_STORAGE:
                isPermissionGranted(grantResults[0] == PackageManager.PERMISSION_GRANTED, permissions[0]);
                break;
        }
    }
    public void isPermissionGranted(boolean isGranted, String permission) {

    }
}
