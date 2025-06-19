package com.example.android11.mvc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.android11.R;

import java.util.Random;

public class ComplexViewActivity  extends Activity {
    private final Random rand = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_view);

        final EditText tb1 = (EditText) findViewById(R.id.complex_text1);
        final EditText tb2 = (EditText) findViewById(R.id.complex_text2);

        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tb1.setText(String.valueOf(rand.nextInt(200)));
                tb2.setText(String.valueOf(rand.nextInt(200)));
            }
        };

        ((Button) findViewById(R.id.complex_button1)).setOnClickListener(listener);
        ((Button) findViewById(R.id.complex_button2)).setOnClickListener(listener);


    }
}
