package com.example.myapplicationfonts;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Typeface tf1,tf2,tf3,tf4,tf5,tf6;
    private ViewGroup rootlayout;
    private int _xDelta;
    private int _yDelta;
    RelativeLayout relativeLayout;
    Button btn;
    private int currentcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootlayout = (ViewGroup) findViewById(R.id.rel_lay);

        relativeLayout=(RelativeLayout)findViewById(R.id.rel_lay);
        btn=(Button)findViewById(R.id.btn1);

        editText = (EditText) findViewById(R.id.edt);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);


        tf1 = ResourcesCompat.getFont(this, R.font.abeezee_regular);
        tf2 = ResourcesCompat.getFont(this, R.font.aladin_regular);
        tf3 = ResourcesCompat.getFont(this, R.font.akronim_regular);
        tf4 = ResourcesCompat.getFont(this, R.font.amatica_sc_regular);
        tf5 = ResourcesCompat.getFont(this, R.font.bad_script_regular);
        tf6 = ResourcesCompat.getFont(this, R.font.arizonia_regular);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setTypeface(tf1);
            }
        });


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setTypeface(tf2);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setTypeface(tf3);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setTypeface(tf4);
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setTypeface(tf5);
            }
        });

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setTypeface(tf6);
            }
        });
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenDialog(false);
            }
        });


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(900, 900);
        editText.setLayoutParams(layoutParams);
        editText.setOnTouchListener(new ChoiceTouchListner());
    }




    private  void OpenDialog(boolean SupportAlpha){

        AmbilWarnaDialog dialog= new AmbilWarnaDialog(this, currentcolor, SupportAlpha,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {

                        currentcolor=color;
                        editText.setTextColor(color);


                        Window window=getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(color);
                        }
                    }
                });
        dialog.show();
    }





    private final class ChoiceTouchListner implements View.OnTouchListener {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lparm = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        _xDelta = X - lparm.leftMargin;
                        _yDelta = Y - lparm.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams lyparm = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        lyparm.leftMargin = X - _xDelta;
                        lyparm.topMargin = Y - _yDelta;
                        lyparm.rightMargin = -250;
                        lyparm.bottomMargin = -250;
                        v.setLayoutParams(lyparm);
                        break;


                }

                rootlayout.invalidate();

                return true;
            }

        }
}
