package com.example.android11;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SelectExample extends Activity {
    private CheckBox checkBox;
    private TextView txtCheckBox,txtRadio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkboxes_radiobuttons_spinners);

        checkBox = (CheckBox) findViewById(R.id.cbxBox1);
        txtCheckBox = (TextView) findViewById(R.id.txtCheckBox);
        txtRadio =  (TextView) findViewById(R.id.txtRadio);
        RadioButton rb1 = (RadioButton) findViewById(R.id.RB1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.RB2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.RB3);
        Spinner spnMusketeers = (Spinner) findViewById(R.id.spnMusketeers);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()) {
                    txtCheckBox.setText(R.string.box_checked);
                } else {
                    txtCheckBox.setText(R.string.checkbox_not_checked);
                }
            }
        });
        // lambda is not included in android 1.1
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRadio.setText(R.string.rb1_picked);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRadio.setText(R.string.rb2_picked);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                txtRadio.setText(R.string.rb3_picked);
            }
        });
        List<String> lsMusketeers = new ArrayList<String>();
        lsMusketeers.add("Athos");
        lsMusketeers.add("Porthos");
        lsMusketeers.add("Aramis");

        ArrayAdapter<String> aspnMusketeers =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                        lsMusketeers);
        aspnMusketeers.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spnMusketeers.setAdapter(aspnMusketeers);
        // Set up a callback for the spinner
        spnMusketeers.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onNothingSelected(AdapterView<?> arg0) { }
                    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {}
                });
    }

}
