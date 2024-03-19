package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import Activity.StartupActivity;
import Activity.loginActivity;

public class WorkoutFragment extends Fragment implements View.OnClickListener {

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workouts, container, false);

        Button BicepBTN = (Button) view.findViewById(R.id.BicepBTN);
        Button TricepBTN = (Button) view.findViewById(R.id.TricepsBTN);
        Button LegBTN = (Button) view.findViewById(R.id.LegsBTN);
        Button CoreBTN = (Button) view.findViewById(R.id.CoreBTN);
        Button BackBTN = (Button) view.findViewById(R.id.BackBTN);
        Button ShoulderBTN = (Button) view.findViewById(R.id.ShoulderBTN);
        Button ChestBTN = (Button) view.findViewById(R.id.ChestBTN);
        Button GluteBTN = (Button) view.findViewById(R.id.GlutesBTN);

        // Set OnClickListener for each button
        BicepBTN.setOnClickListener(this);
        TricepBTN.setOnClickListener(this);
        LegBTN.setOnClickListener(this);
        CoreBTN.setOnClickListener(this);
        BackBTN.setOnClickListener(this);
        ShoulderBTN.setOnClickListener(this);
        ChestBTN.setOnClickListener(this);
        GluteBTN.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // Handle button clicks here
        switch (v.getId()) {
            case R.id.BicepBTN:
                switchToBicepWorkouts();
                break;
            case R.id.TricepsBTN:
                // Handle Triceps button click
                break;
            case R.id.LegsBTN:
                // Handle Legs button click
                break;
            case R.id.CoreBTN:
                // Handle Core button click
                break;
            case R.id.BackBTN:
                // Handle Back button click
                break;
            case R.id.ShoulderBTN:
                // Handle Shoulder button click
                break;
            case R.id.ChestBTN:
                // Handle Chest button click
                break;
            case R.id.GlutesBTN:
                // Handle Glutes button click
                break;
        }
    }

    private void switchToBicepWorkouts() {
        // Inflate the Bicep Workouts layout
        View bicepWorkoutsView = getLayoutInflater().inflate(R.layout.bicepworkouts, null);

        // Replace the current fragment's view with the new layout
        ViewGroup rootView = (ViewGroup) getView();
        if (rootView != null) {
            rootView.removeAllViews();
            rootView.addView(bicepWorkoutsView);
        }
    }
}
