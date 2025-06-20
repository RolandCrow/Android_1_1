package com.example.android11.mvc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.android11.R;

import java.util.Random;

public class ComplexViewActivity  extends Activity {
    public static final int DOT_DIAMETER = 6;
    private final Random rand = new Random();
    final Dots dotModel = new Dots();
    DotView dotView;
    DotGenerator dotGenerator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_view);
        dotView = new DotView(this,dotModel);
        ((LinearLayout) findViewById(R.id.root)).addView(dotView,0);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(MotionEvent.ACTION_DOWN != motionEvent.getAction()) {
                    return false;
                }
                dotModel.addDot(motionEvent.getX(), motionEvent.getY(),Color.CYAN,DOT_DIAMETER);
                return true;
            }
        });
        dotView.setFocusable(true);
        dotView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(KeyEvent.ACTION_UP != keyEvent.getAction()) {
                    int color = Color.BLUE;
                    switch (i) {
                        case KeyEvent.KEYCODE_SPACE:
                            color = Color.MAGENTA;
                            break;
                        case KeyEvent.KEYCODE_ENTER:
                            color = Color.YELLOW;
                            break;
                        default:;
                    }
                    makeDot(dotModel,dotView,color);
                }
                return (i < KeyEvent.KEYCODE_0) || (i > KeyEvent.KEYCODE_9);
            }
        });
        dotView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b && (null != dotGenerator)) {
                    dotGenerator.done();
                    dotGenerator = null;
                }
                else if(b &&(null == dotGenerator)) {
                    dotGenerator = new DotGenerator(dotModel,dotView,Color.BLACK);
                    new Thread(dotGenerator).start();
                }
            }
        });
        final EditText tb1 = (EditText) findViewById(R.id.complex_text1);
        final EditText tb2 = (EditText) findViewById(R.id.complex_text2);

        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tb1.setText(String.valueOf(rand.nextInt(200)));
                tb2.setText(String.valueOf(rand.nextInt(200)));
            }
        };

        ((Button) findViewById(R.id.complex_button1)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        makeDot(dotModel, dotView, Color.RED);
                    }
                }
        );
        ((Button) findViewById(R.id.complex_button2)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        makeDot(dotModel, dotView,Color.GREEN);
                    }
                }
        );
        dotModel.setDotsChangeListener(new Dots.DotsChangeListener() {
            @Override
            public void onDotsChange(Dots dots) {
                Dot d = dotModel.getLastDot();
                tb1.setText((null == d) ? "": String.valueOf(d.getX()));
                tb2.setText((null == d) ? "": String.valueOf(d.getY()));
                dotView.invalidate();
            }
        });
    }

    private final class DotGenerator implements Runnable {
        final Dots dots;
        final DotView view;
        final int color;

        final Handler handler = new Handler();
        final Runnable makeDots = new Runnable() {
            @Override
            public void run() {
                makeDot(dots,view,color);
            }
        };
        private volatile boolean done;

        DotGenerator(Dots dots, DotView view, int color) {
            this.dots = dots;
            this.view = view;
            this.color = color;
        }
        public void done() {done = true;}
        public void run() {
            while (!done) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                handler.post(makeDots);
            }
        }
    }

    public void makeDot(Dots dots, DotView view, int color) {
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(
                DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
                DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
                color,
                DOT_DIAMETER
        );
    }
}

