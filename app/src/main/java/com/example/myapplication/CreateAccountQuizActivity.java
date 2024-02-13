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
                //make if statement to make sure all fields are filled out, if not - prompt "not all fields are filled out"
                //if all fields are filled out go next
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

        //spinners
        Spinner monthSpinner = (Spinner) findViewById(R.id.monthsSpinner);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setPrompt("Month");
        //
        Spinner daySpinner = (Spinner) findViewById(R.id.daySpinner);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
        daySpinner.setPrompt("Day");
        //
        Spinner yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setPrompt("Year");

        Button BACK = (Button) findViewById(R.id.backBtnToNewAccount2);
        BACK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });
    }
}