package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;

public class SettingFragment extends Fragment {

<<<<<<< Updated upstream:app/src/main/java/Fragments/SettingFragment.java
    public SettingFragment() {
=======
    private TextView usernameTextView;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView ageTextView;
    private TextView sexTextView;
    private TextView goalTextView;

    public ProfileFragment() {
>>>>>>> Stashed changes:app/src/main/java/Fragments/ProfileFragment.java
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< Updated upstream:app/src/main/java/Fragments/SettingFragment.java
        return inflater.inflate(R.layout.settings, container, false);
=======
        View view = inflater.inflate(R.layout.profile, container, false);

        // Initialize TextViews
        usernameTextView = view.findViewById(R.id.usernameTextView);
        heightTextView = view.findViewById(R.id.heightTextView);
        weightTextView = view.findViewById(R.id.weightTextView);
        ageTextView = view.findViewById(R.id.ageTextView);
        sexTextView = view.findViewById(R.id.sexTextView);
        goalTextView = view.findViewById(R.id.goalTextView);

        // Retrieve user information from the database
        displayUserInfo();

        return view;
>>>>>>> Stashed changes:app/src/main/java/Fragments/ProfileFragment.java
    }

    private void displayUserInfo() {
        // Retrieve user information from the database using DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        // Example: Replace "currentUser" with the actual username of the logged-in user
        String currentUser = "username";
        int height = databaseHelper.getUserHeight(currentUser);
        int weight = databaseHelper.getUserWeight(currentUser);
        int age = databaseHelper.getUserAge(currentUser);
        String sex = databaseHelper.getUserSex(currentUser);
        int goal = databaseHelper.getUserGoal(currentUser);

        // Display user information in TextViews
        usernameTextView.setText(currentUser);
        heightTextView.setText(String.valueOf(height));
        weightTextView.setText(String.valueOf(weight));
        ageTextView.setText(String.valueOf(age));
        sexTextView.setText(sex);
        goalTextView.setText(String.valueOf(goal));
    }
}