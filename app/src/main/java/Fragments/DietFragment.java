package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;

import java.util.Locale;

public class DietFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private String currentUsername; // Placeholder: Replace with actual username retrieval logic
    private TextView caloriesTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diet_fragment, container, false);

        databaseHelper = new DatabaseHelper();
        currentUsername = "someUsername"; // Replace with actual username retrieval logic

        // UI elements
        caloriesTextView = view.findViewById(R.id.calories_text_view);
        Button gainWeightButton = view.findViewById(R.id.gain_weight_button);
        Button loseWeightButton = view.findViewById(R.id.lose_weight_button);

        // Calculate and update the recommended calorie intake
        updateCalorieIntake();

        gainWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implement navigation to gain weight page
            }
        });

        loseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implement navigation to lose weight page
            }
        });

        return view;
    }

    private void updateCalorieIntake() {
        int recommendedCalories = calculateRecommendedCalories();
        caloriesTextView.setText(String.format(Locale.getDefault(), "Recommended calorie intake: %d kcal", recommendedCalories));
    }

    private int calculateRecommendedCalories() {
        int height = databaseHelper.getUserHeight(currentUsername);
        int weight = databaseHelper.getUserWeight(currentUsername);
        int age = databaseHelper.getUserAge(currentUsername);
        String sex = databaseHelper.getUserSex(currentUsername);
        int goal = databaseHelper.getUserGoal(currentUsername);

        int calories;
        boolean isGainWeight = goal == 1; // assuming 1 is the value for 'gain weight', and any other value is 'lose weight'

        if ("Male".equalsIgnoreCase(sex)) {
            calories = calculateCaloriesForMale(weight, height, age, isGainWeight);
        } else {
            calories = calculateCaloriesForFemale(weight, height, age, isGainWeight);
        }

        return calories;
    }

    private int calculateCaloriesForMale(int weight, int height, int age, boolean isGainWeight) {
        int baseCalories = (int) (22 * weight + 2.5 * height - (6.8 * age));
        return isGainWeight ? baseCalories + 339 : baseCalories - 161;
    }

    private int calculateCaloriesForFemale(int weight, int height, int age, boolean isGainWeight) {
        int baseCalories = (int) (22 * weight + 2.5 * height - (5 * age));
        return isGainWeight ? baseCalories + 339 : baseCalories - 161;
    }
}