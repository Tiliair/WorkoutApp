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

import java.util.Stack;

public class WorkoutFragment extends Fragment implements View.OnClickListener {

    private Stack<Integer> layoutStack = new Stack<>();
    private View rootView;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.workouts, container, false);

        // Initialize the stack with the initial layout
        layoutStack.push(R.layout.workouts);

        initializeButtons(rootView);

        return rootView;
    }

    private void initializeButtons(View view) {
        Button BicepBTN = view.findViewById(R.id.BicepBTN);
        Button TricepBTN = view.findViewById(R.id.TricepsBTN);
        Button LegBTN = view.findViewById(R.id.LegsBTN);
        Button CoreBTN = view.findViewById(R.id.CoreBTN);
        Button BackBTN = view.findViewById(R.id.BackBTN);
        Button ShoulderBTN = view.findViewById(R.id.ShoulderBTN);
        Button ChestBTN = view.findViewById(R.id.ChestBTN);
        Button GluteBTN = view.findViewById(R.id.GlutesBTN);
        Button backBtn = view.findViewById(R.id.backBtn);

        BicepBTN.setOnClickListener(this);
        TricepBTN.setOnClickListener(this);
        LegBTN.setOnClickListener(this);
        CoreBTN.setOnClickListener(this);
        BackBTN.setOnClickListener(this);
        ShoulderBTN.setOnClickListener(this);
        ChestBTN.setOnClickListener(this);
        GluteBTN.setOnClickListener(this);
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onClick(View v) {
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
        layoutStack.push(layoutId); // Save the current layout ID to the stack

        // Inflate the specified workouts layout
        ViewGroup parentView = (ViewGroup) rootView.getParent();
        if (parentView != null) {
            View workoutsView = getLayoutInflater().inflate(layoutId, parentView, false);
            parentView.removeAllViews();
            parentView.addView(workoutsView);
            initializeButtons(workoutsView); // Reinitialize buttons in the new layout
        }
    }

    private void onBackPressed() {
        if (!layoutStack.isEmpty()) {
            layoutStack.pop(); // Remove the current layout
            if (!layoutStack.isEmpty()) {
                int previousLayoutId = layoutStack.peek(); // Get the previous layout
                // Switch to the previous layout
                ViewGroup parentView = (ViewGroup) rootView.getParent();
                if (parentView != null) {
                    View previousView = getLayoutInflater().inflate(previousLayoutId, parentView, false);
                    parentView.removeAllViews();
                    parentView.addView(previousView);
                    initializeButtons(previousView); // Reinitialize buttons in the previous layout
                }
            } else {
                // If stack is empty, exit the fragment
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }
    }
}
