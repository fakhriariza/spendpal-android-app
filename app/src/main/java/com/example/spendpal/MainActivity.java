package com.example.spendpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    final Fragment homeFragment = new HomeFragment();
    final FragmentManager fm = getSupportFragmentManager();

    @BindView(R.id.bnv_main_bottom_nav)
    public BottomNavigationView bnvMainBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnvMainBottomNav = findViewById(R.id.bnv_main_bottom_nav);
        fm.beginTransaction().add(R.id.fcv_mainContainer, homeFragment, "1").commit();
        fragmentManager();
    }
    private void changeFragment (Class id) {
        fm.beginTransaction()
                .setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
                .replace(R.id.fcv_mainContainer, id, null)
                .setReorderingAllowed(true).commit();
    }
    private void fragmentManager () {
        bnvMainBottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                changeFragment(HomeFragment.class);
                return true;
            } else if (itemId == R.id.navigation_add) {
                changeFragment(AddFragment.class);
                return true;
            }
            return false;
        });
    }
}