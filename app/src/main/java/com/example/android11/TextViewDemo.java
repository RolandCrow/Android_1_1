package com.example.android11;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TextViewDemo extends Activity {
    // Do not place Android context classes in static fields; this is a memory leak
    // in 2009 methods was static
    private EditText ext1;

    private final Button.OnClickListener btnDoneOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String input = ext1.getText().toString();
            Log.v("TextViewDemo",input);
            ext1.setText("");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);
        TextView txt1 = (TextView) findViewById(R.id.txtDemo);
        ext1 = (EditText) findViewById(R.id.eTxtDemo);
        Button btn1 = (Button) findViewById(R.id.btnDone);
        txt1.setText(R.string.some_text);
        btn1.setOnClickListener(btnDoneOnClick);
    }
}
