package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountQuizActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount1);

        Button NEXT = (Button) findViewById(R.id.nextBtnToNewAccount2);

        NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });

    }

    public void changelayout1() {
        setContentView(R.layout.newaccount1);

        Button NEXT = (Button) findViewById(R.id.nextBtnToNewAccount2);
        NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });
    }
    public void changelayout2() {
        setContentView(R.layout.newaccount2);

        Button NEXT = (Button) findViewById(R.id.nextBtnToNewAccount3);
        NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout3();
            }
        });

        Button BACK = (Button) findViewById(R.id.backBtnToNewAccount1);
        BACK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout1();
            }
        });
    }

    public void changelayout3(){
        setContentView(R.layout.newaccount3);

        Spinner spinner = (Spinner) findViewById(R.id.monthsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Month");

        Button BACK = (Button) findViewById(R.id.backBtnToNewAccount2);
        BACK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });
    }
}