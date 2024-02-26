package Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);

        int[] tabIcons = {
                R.drawable.baseline_cottage_24,
                R.drawable.baseline_fastfood_24,
                R.drawable.baseline_directions_run_24,
                R.drawable.baseline_insert_emoticon_24
        };

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Set the text
                    switch (position) {
                        case 0:
                            tab.setText("Home");
                            break;
                        case 1:
                            tab.setText("Diet");
                            break;
                        case 2:
                            tab.setText("Workouts");
                            break;
                        case 3:
                            tab.setText("Profile");
                            break;
                    }

                    // Set the icon
                    tab.setIcon(tabIcons[position]);
                }
        ).attach();
    }
}
