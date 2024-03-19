package Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.TabUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import Fragments.DietFragment;
import Fragments.HomeFragment;
import Fragments.SettingFragment;
import Fragments.WorkoutFragment;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        List<Fragment> fragments = Arrays.asList(
                new HomeFragment(),
                new DietFragment(),
                new WorkoutFragment(),
                new SettingFragment()
        );

        List<String> tabTitles = Arrays.asList("Home", "Diet", "Workouts", "Profile");

        int[] tabIcons = {
                R.drawable.baseline_cottage_24,
                R.drawable.baseline_fastfood_24,
                R.drawable.baseline_directions_run_24,
                R.drawable.baseline_insert_emoticon_24
        };

        TabUtil.setupTabs(this, viewPager, tabLayout, fragments, tabTitles, tabIcons);
    }
}
