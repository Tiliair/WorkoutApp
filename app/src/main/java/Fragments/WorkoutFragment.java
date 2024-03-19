package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;

public class WorkoutFragment extends Fragment implements View.OnClickListener {

    private int initialLayoutId; // Store the initial layout resource ID
    private View rootView;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.workouts, container, false);
        initialLayoutId = R.layout.workouts; // Save the initial layout resource ID

        Button BicepBTN = rootView.findViewById(R.id.BicepBTN);
        Button TricepBTN = rootView.findViewById(R.id.TricepsBTN);
        Button LegBTN = rootView.findViewById(R.id.LegsBTN);
        Button CoreBTN = rootView.findViewById(R.id.CoreBTN);
        Button BackBTN = rootView.findViewById(R.id.BackBTN);
        Button ShoulderBTN = rootView.findViewById(R.id.ShoulderBTN);
        Button ChestBTN = rootView.findViewById(R.id.ChestBTN);
        Button GluteBTN = rootView.findViewById(R.id.GlutesBTN);

        // Set OnClickListener for each button
        BicepBTN.setOnClickListener(this);
        TricepBTN.setOnClickListener(this);
        LegBTN.setOnClickListener(this);
        CoreBTN.setOnClickListener(this);
        BackBTN.setOnClickListener(this);
        ShoulderBTN.setOnClickListener(this);
        ChestBTN.setOnClickListener(this);
        GluteBTN.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        // Handle button clicks here
        int viewId = v.getId();
        if (viewId == R.id.BicepBTN) {
            switchToWorkoutsLayout(R.layout.bicepworkouts);
        } else if (viewId == R.id.TricepsBTN) {
            switchToWorkoutsLayout(R.layout.tricepworkout);
        } else if (viewId == R.id.LegsBTN) {
            switchToWorkoutsLayout(R.layout.legworkouts);
        } else if (viewId == R.id.CoreBTN) {
            switchToWorkoutsLayout(R.layout.coreworkouts);
        } else if (viewId == R.id.BackBTN) {
            switchToWorkoutsLayout(R.layout.backworkout);
        } else if (viewId == R.id.ShoulderBTN) {
            switchToWorkoutsLayout(R.layout.shoulderworkouts);
        } else if (viewId == R.id.ChestBTN) {
            switchToWorkoutsLayout(R.layout.chestworkouts);
        } else if (viewId == R.id.GlutesBTN) {
            switchToWorkoutsLayout(R.layout.gluteworkouts);
        }
    }

    private void switchToWorkoutsLayout(int layoutId) {
        initialLayoutId = layoutId; // Update the initial layout resource ID
        // Inflate the specified workouts layout
        View workoutsView = getLayoutInflater().inflate(layoutId, (ViewGroup) rootView, false);

        // Replace the current fragment's view with the new layout
        ViewGroup parentView = (ViewGroup) rootView.getParent();
        if (parentView != null) {
            parentView.removeAllViews();
            parentView.addView(workoutsView);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Reset to initial layout when the fragment is created or revisited
        switchToWorkoutsLayout(initialLayoutId);
    }
}
