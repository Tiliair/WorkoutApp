package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateAccountQuizActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner heightSpinner;
    EditText weight;
    String selectedSex;
    String selectedGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeLayout1();
    }

    public void changeLayout1() {
        setContentView(R.layout.newaccount1);
        radioGroup = findViewById(R.id.radioGroup0);
        weight = findViewById(R.id.weight);
        heightSpinner = findViewById(R.id.heightSpinner);
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(this, R.array.Heights, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightSpinner.setAdapter(heightAdapter);

        Button nextBtn = findViewById(R.id.nextBtnToNewAccount2);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(weight.getText())) {
                    Toast.makeText(getApplicationContext(), "Please enter a weight", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (heightSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select a height", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    selectedSex = radioButton.getText().toString();
                    changeLayout2();
                }
            }
        });
    }

    public void changeLayout2() {
        setContentView(R.layout.newaccount2);
        Button buttonBack = findViewById(R.id.backBtnToNewAccount1);
        Button buttonNext = findViewById(R.id.nextBtnToNewAccount3);
        radioGroup = findViewById(R.id.radioGroup1);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                } else {
                    int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);
                    selectedSex = selectedRadioButton.getText().toString();
                    // Determine selected goal based on radio button text
                    int selectedGoalIndex = radioGroup.indexOfChild(selectedRadioButton);
                    // Assign selected goal number (1-4)
                    selectedGoal = String.valueOf(selectedGoalIndex + 1);
                    changeLayout3();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout1();
            }
        });
    }

    public void changeLayout3() {
        setContentView(R.layout.newaccount3);

        Spinner monthSpinner = findViewById(R.id.monthsSpinner);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        // Prepend "Month" to the beginning of the array
        String[] monthsArray = getResources().getStringArray(R.array.Months);
        String[] monthsWithTitle = new String[monthsArray.length + 1];
        monthsWithTitle[0] = "Month";
        System.arraycopy(monthsArray, 0, monthsWithTitle, 1, monthsArray.length);
        ArrayAdapter<String> monthAdapterWithTitle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthsWithTitle);
        monthAdapterWithTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapterWithTitle);

        Spinner daySpinner = findViewById(R.id.daySpinner);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        // Prepend "Day" to the beginning of the array
        String[] daysArray = getResources().getStringArray(R.array.Days);
        String[] daysWithTitle = new String[daysArray.length + 1];
        daysWithTitle[0] = "Day";
        System.arraycopy(daysArray, 0, daysWithTitle, 1, daysArray.length);
        ArrayAdapter<String> dayAdapterWithTitle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daysWithTitle);
        dayAdapterWithTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapterWithTitle);

        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        // Prepend "Year" to the beginning of the array
        String[] yearsArray = getResources().getStringArray(R.array.years);
        String[] yearsWithTitle = new String[yearsArray.length + 1];
        yearsWithTitle[0] = "Year";
        System.arraycopy(yearsArray, 0, yearsWithTitle, 1, yearsArray.length);
        ArrayAdapter<String> yearAdapterWithTitle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearsWithTitle);
        yearAdapterWithTitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapterWithTitle);

        Button backButton = findViewById(R.id.backBtnToNewAccount2);
        Button nextButton = findViewById(R.id.nextBtnToMainMenu);

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String selectedMonth = monthSpinner.getSelectedItem().toString();
                String selectedDay = daySpinner.getSelectedItem().toString();
                String selectedYear = yearSpinner.getSelectedItem().toString();

                // Check if all fields are selected
                if (selectedMonth.equals("Month") || selectedDay.equals("Day") || selectedYear.equals("Year")) {
                    Toast.makeText(CreateAccountQuizActivity.this, "Please select your birth date!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //get age
                float age = calculateAge(selectedMonth, selectedDay, selectedYear);

                // Get other selected data
                float height = Float.parseFloat(heightSpinner.getSelectedItem().toString());
                float weightValue = Float.parseFloat(weight.getText().toString());
                String sex = selectedSex;
                int goal = Integer.parseInt(selectedGoal);

                // Insert data into the database
                boolean inserted = new DatabaseHelper(CreateAccountQuizActivity.this).insertAccountInfo(height, weightValue, age, sex, String.valueOf(goal));

                if (inserted) {
                    startActivity(new Intent(CreateAccountQuizActivity.this, MainMenuActivity.class));
                    finish(); // Finish the current activity to prevent going back to it on pressing back
                } else {
                    Toast.makeText(CreateAccountQuizActivity.this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                    // Optionally, you can keep the user on the current activity to try again
                    // You can add some logic here to reset the form or keep the entered data
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLayout2();
            }
        });
    }

    // Method to calculate age from selected birth date
    private float calculateAge(String selectedMonth, String selectedDay, String selectedYear) {
// Get current date
        LocalDate currentDate = LocalDate.now();

        // Parse selected date
        LocalDate selectedDate = LocalDate.parse(selectedYear + "-" + selectedMonth + "-" + selectedDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Calculate age
        int years = currentDate.getYear() - selectedDate.getYear();
        int months = currentDate.getMonthValue() - selectedDate.getMonthValue();
        int days = currentDate.getDayOfMonth() - selectedDate.getDayOfMonth();

        // Adjust age if current month/day is before selected month/day
        if (months < 0 || (months == 0 && days < 0)) {
            years--;
        }

        // Calculate fractional age
        float fractionalAge = years + (float) months / 12 + (float) days / 365;

        return fractionalAge;
    }
}
