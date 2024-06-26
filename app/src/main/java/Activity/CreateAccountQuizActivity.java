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
import com.google.firebase.FirebaseApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateAccountQuizActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Spinner heightSpinner;
    EditText weight;
    String selectedSex;
    String selectedGoal;
    String username;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount1);
        FirebaseApp.initializeApp(this);
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
        changeLayout1();
    }

    public void changeLayout1() {
        radioGroup = findViewById(R.id.radioGroup0);
        weight = findViewById(R.id.weight);
        heightSpinner = findViewById(R.id.heightSpinner);

        String[] heightsArray = getResources().getStringArray(R.array.Heights);
        String[] heightsWithTitle = new String[heightsArray.length + 1];
        heightsWithTitle[0] = "Height";
        System.arraycopy(heightsArray, 0, heightsWithTitle, 1, heightsArray.length);

        ArrayAdapter<String> heightAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, heightsWithTitle);
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
                } else {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioId);
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
                    int selectedGoalIndex = radioGroup.indexOfChild(selectedRadioButton);
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

        Spinner daySpinner = findViewById(R.id.daySpinner);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

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

                if (selectedMonth.equals("Month") || selectedDay.equals("Day") || selectedYear.equals("Year")) {
                    Toast.makeText(CreateAccountQuizActivity.this, "Please select your birth date!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int age = calculateAge(selectedMonth, selectedDay, selectedYear);
                    int height = Integer.parseInt(heightSpinner.getSelectedItem().toString());
                    int weightValue = Integer.parseInt(weight.getText().toString());
                    String sex = selectedSex;
                    int goal = Integer.parseInt(selectedGoal);

                    boolean inserted = databaseHelper.insertAccountInfo(username, height, weightValue, age, sex, goal);

                    if (inserted) {
                        startActivity(new Intent(CreateAccountQuizActivity.this, MainMenuActivity.class));
                        finish(); // finish current activity
                    } else {
                        Toast.makeText(CreateAccountQuizActivity.this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int calculateAge(String selectedMonth, String selectedDay, String selectedYear) {
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = LocalDate.parse(selectedYear + "-" + selectedMonth + "-" + selectedDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int years = currentDate.getYear() - selectedDate.getYear();
        int months = currentDate.getMonthValue() - selectedDate.getMonthValue();
        int days = currentDate.getDayOfMonth() - selectedDate.getDayOfMonth();
        if (months < 0 || (months == 0 && days < 0)) {
            years--;
        }
        return years;
    }
}
