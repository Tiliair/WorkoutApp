package com.example.myapplication;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.List;

public class TabUtil {

    public static void setupTabs(Activity activity, ViewPager2 viewPager, TabLayout tabLayout, List<Fragment> fragments, List<String> tabTitles, int[] tabIcons) {
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter((FragmentActivity) activity);
        viewPager.setAdapter(viewPageAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setText(tabTitles.get(position));
                    if (tabIcons != null && position < tabIcons.length) {
                        tab.setIcon(tabIcons[position]);
                    }
                }
        ).attach();
    }
}
