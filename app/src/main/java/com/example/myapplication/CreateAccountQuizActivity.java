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

        Button NEXT = findViewById(R.id.nextBtnToNewAccount2);

        NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });

    }

    public void changelayout1() {
        setContentView(R.layout.newaccount1);

        Spinner heightSpinner = findViewById(R.id.heightSpinner);
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(this, R.array.Heights, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightSpinner.setAdapter(heightAdapter);

        Button NEXT = findViewById(R.id.nextBtnToNewAccount2);
        NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });
    }

    public void changelayout2() {
        setContentView(R.layout.newaccount2);

        Button NEXT = findViewById(R.id.nextBtnToNewAccount3);
        NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout3();
            }
        });

        Button BACK = findViewById(R.id.backBtnToNewAccount1);
        BACK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout1();
            }
        });
    }

    public void changelayout3() {
        setContentView(R.layout.newaccount3);

        Spinner monthSpinner = findViewById(R.id.monthsSpinner);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        Spinner daySpinner = findViewById(R.id.daySpinner);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        Button BACK = findViewById(R.id.backBtnToNewAccount2);
        BACK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changelayout2();
            }
        });
    }
}
