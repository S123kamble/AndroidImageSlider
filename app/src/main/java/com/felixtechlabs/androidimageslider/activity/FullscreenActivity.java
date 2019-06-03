package com.felixtechlabs.androidimageslider.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.felixtechlabs.androidimageslider.R;
import com.felixtechlabs.androidimageslider.adapter.CanvasAdapter;
import com.felixtechlabs.androidimageslider.view.DrawingView;
import com.felixtechlabs.androidimageslider.view.Items;
import com.felixtechlabs.androidimageslider.view.ViewDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView drawView;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn, insertBtn, undoBtn, redoBtn;
    private float smallBrush, mediumBrush, largeBrush;

    // insert image from gallery
    private static int RESULT_LOAD_IMAGE = 1;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Items> items;
    Context mContext;

    FloatingActionButton btnFloat;
    public int hours = 3;
    public int seconds = 0;
    public int minutes = 0;
    Button rvToggle, canToggle;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);


        btn_back = findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullscreenActivity.this,MainActivity.class);
               startActivity(intent);

            }
        });

        canToggle = findViewById(R.id.canvas_toggle);
        canToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullscreenActivity.this, FullscreenActivity.class);
                startActivity(intent);
            }
        });

        //Declare the timer
        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        TextView tv = (TextView) findViewById(R.id.timer);
                        tv.setText(String.valueOf(hours) + ":" + (minutes) + ":" + String.valueOf(seconds));

                        if (seconds > 0) {
                            tv.setText(String.valueOf(hours) + ":" + (minutes) + ":" + String.valueOf(seconds));
                            seconds -= 1;
                        }

                        if (seconds == 0) {
                            tv.setText(String.valueOf(hours) + ":" + (minutes) + ":" + String.valueOf(seconds));
                            seconds = 60;
                            minutes = minutes - 1;
                        }


                    }
                });
            }

        }, 0, 1000);


        btnFloat = findViewById(R.id.floating_calcy);
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
//                dialogBuilder.setTitle("hi");
//                dialogBuilder.setMessage("this is my app");
//
////                dialogBuilder.setButton("Continue..", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        // here you can add functions
////                    }
////                });
//
//                dialogBuilder.show();  //<-- See This!

                ViewDialog viewDialog = new ViewDialog();
                viewDialog.showDialog(FullscreenActivity.this, "");
            }

        });



        drawView = (DrawingView) findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.pallet_pressed
                , null));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        drawBtn = findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);

        drawView.setBrushSize(mediumBrush);

        eraseBtn = findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        newBtn =  findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        saveBtn = (ImageButton) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

        insertBtn = (ImageButton) findViewById(R.id.insert_btn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        undoBtn = (ImageButton) findViewById(R.id.undo_btn);
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawView.onClickUndo()) {
                    Toast nothingToast = Toast.makeText(getApplicationContext(),
                            "Nothing to Undo!", Toast.LENGTH_SHORT);
                    nothingToast.show();
                }
            }
        });

        redoBtn = (ImageButton) findViewById(R.id.redo_btn);
        redoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawView.onClickRedo()) {
                    Toast nothingToast = Toast.makeText(getApplicationContext(),
                            "Nothing to Redo!", Toast.LENGTH_SHORT);
                    nothingToast.show();
                }
            }
        });

    }

    public void paintClicked(View view) {
        drawView.setErase(false);
        if (view != currPaint) {
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            drawView.setColor(color);

            imgView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.pallet_pressed, null));
            currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.pallet, null));
            currPaint = (ImageButton) view;
        }
        drawView.setBrushSize(drawView.getLastBrushSize());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.draw_btn) {
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size");
            brushDialog.setContentView(R.layout.dialog_brush_size);
            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            brushDialog.show();
        } else if (view.getId() == R.id.erase_btn) {
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size");
            brushDialog.setContentView(R.layout.dialog_brush_size);

            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        } else if (view.getId() == R.id.new_btn) {
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    drawView.startNew(null);
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();
        } else if (view.getId() == R.id.save_btn) {
            //save drawing
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save drawing");
            saveDialog.setMessage("Save drawing to device Gallery?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    //save drawing
                    drawView.setDrawingCacheEnabled(true);
                    String imgSaved = MediaStore.Images.Media.insertImage(
                            FullscreenActivity.this.getContentResolver(), drawView.getDrawingCache(),
                            UUID.randomUUID().toString() + ".png", "drawing");

                    if (imgSaved != null) {
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                        savedToast.show();
                    } else {
                        for (int i = 0; i < 3; i++) { // tried to increase the duration
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Oops! Image could not be saved. " +
                                            "Explicit write permission to storage device may required." +
                                            "Check Settings->" +
                                            "Application Manager->" +
                                            "JJDraw->" +
                                            "Permissions.", Toast.LENGTH_LONG);
                            unsavedToast.show();
                        }
                    }

                    drawView.destroyDrawingCache();
                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
         * Loading an image from gallery is based on
         * http://viralpatel.net/blogs/pick-image-from-galary-android-app/
         */
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            drawView.startNew(picturePath);
        }

    }

    }

