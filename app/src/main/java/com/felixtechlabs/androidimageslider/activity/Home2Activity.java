package com.felixtechlabs.androidimageslider.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.felixtechlabs.androidimageslider.R;
import com.felixtechlabs.androidimageslider.adapter.GridViewAdapter;
import com.felixtechlabs.androidimageslider.adapter.MyAdapter;
import com.felixtechlabs.androidimageslider.view.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class Home2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> movieList = new ArrayList<>();
    private List<String> movieList2 = new ArrayList<>();
    private MyAdapter mAdapter;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter(movieList2);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            movieList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            movieList2.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new DownloadFilesTask().execute();
    }

    private class DownloadFilesTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... params) {
            Uri uri;
            Cursor cursor;
            int column_index_data, column_index_folder_name;
            String absolutePathOfImage = null;
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            cursor = getContentResolver().query(uri, projection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " DESC");

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int j = 0;
            while (cursor.moveToNext() /*&& j < 25*/) {
                absolutePathOfImage = cursor.getString(column_index_data);

                movieList.add(absolutePathOfImage);
                j++;
            }

            movieList2.addAll(movieList);
            //   Collections.reverse(movieList2);
            return null;
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(Home2Activity.this);
            progress.setMessage("Please wait loading..");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.show();

        }
    }

    @Override
    protected void onPause() {
        if (progress != null && progress.isShowing())
            progress.dismiss();
        super.onPause();

    }
}
