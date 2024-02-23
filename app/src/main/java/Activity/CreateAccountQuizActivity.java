package Activity;

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

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateAccountQuizActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner heightSpinner;
    EditText weight;
    String selectedSex;
    String selectedGoal;

    /**
     * On Create is when the user first makes the account this form is called
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeLayout1();
    }

    /**
     * First layout in the CreateAccountQuizActivity
     * the user has no ability to go backwards but can go forward to the next form if all forms are filled out
     */
    public void changeLayout1() {
        setContentView(R.layout.newaccount1);
        radioGroup = findViewById(R.id.radioGroup0);
        weight = findViewById(R.id.weight);
        heightSpinner = findViewById(R.id.heightSpinner);

        // Getting the array from resources
        String[] heightsArray = getResources().getStringArray(R.array.Heights);

        // Adding a title to the heights array
        String[] heightsWithTitle = new String[heightsArray.length + 1];
        heightsWithTitle[0] = "Height";
        System.arraycopy(heightsArray, 0, heightsWithTitle, 1, heightsArray.length);

        // Creating an ArrayAdapter with the array including the title
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, heightsWithTitle);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Setting the adapter for the height spinner
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

    /**
     * Changed to layout 2 with ability to go back to layout 1 or next to layout 3
     * not able to change layout forward unless filling out the form
     * Radio group resets every time the form is seen again for the first time
     */
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

    /**
     * Changed to layout 3 with ability to go back to layout 2 or next to main menu IF everything is filled out and it successfully put your
     * data into the database
     * not able to change layout forward unless filling out the form
     * Make it so every time the user first enters this page the spinners reset to no input
     */
    public void changeLayout3() {
        setContentView(R.layout.newaccount3);

        //month spinner
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

        //day spinner
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

        //year spinner
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

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLayout2();
            }
        });

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

                //get data
                int age = calculateAge(selectedMonth, selectedDay, selectedYear);
                int height = Integer.parseInt((heightSpinner.getSelectedItem().toString()));
                int weightValue = Integer.parseInt((weight.getText().toString()));
                String sex = selectedSex;
                int goal = Integer.parseInt(selectedGoal);

                // Insert data into the database
                boolean inserted = new DatabaseHelper(CreateAccountQuizActivity.this).insertAccountInfo(height, weightValue, age, sex, goal);

                // Check if data was inserted successfully
                if (inserted) {
                    // Start the MainMenuActivity
                    startActivity(new Intent(CreateAccountQuizActivity.this, MainMenuActivity.class));
                    finish();

                } else {
                    Toast.makeText(CreateAccountQuizActivity.this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Gets birthday and calculates age
     * @param selectedMonth gets month from input user
     * @param selectedDay gets day from user
     * @param selectedYear gets year from user
     * @return calculated age based on current date and birthday input
     */
    private int calculateAge(String selectedMonth, String selectedDay, String selectedYear) {
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

        // Return only the integer part of the age
        return years;
    }
}
