package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import Fragments.DietFragment;
import Fragments.HomeFragment;
import Fragments.SettingFragment;
import Fragments.WorkoutFragment;

public class ViewPageAdapter extends FragmentStateAdapter {

        public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new DietFragment();
                case 2:
                    return new WorkoutFragment();
                case 3:
                    return new SettingFragment();
            }
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }

