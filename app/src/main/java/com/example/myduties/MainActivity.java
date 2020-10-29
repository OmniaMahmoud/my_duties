package com.example.myduties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myduties.databinding.ActivityMainBinding;
import com.example.myduties.views.main_menu_views.MyTasksFragment;
import com.example.myduties.views.main_menu_views.ProfileFragment;
import com.example.myduties.views.main_menu_views.StopsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);
        mainBinding.navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.mbn_profile){
                    switchToFragment(getString(R.string.profile), new ProfileFragment());
                }
                else if(item.getItemId() == R.id.mbn_my_tasks){
                    switchToFragment(getString(R.string.my_tasks), new MyTasksFragment());
                }
                else if(item.getItemId() == R.id.mbn_stops){
                    switchToFragment(getString(R.string.stops), new StopsFragment());
                }

                return true;
            }
        });
        mainBinding.navigationView.setSelectedItemId(R.id.mbn_my_tasks);

    }

    private void switchToFragment(String title, Fragment fragment){
        mainBinding.toolbar.setTitle(title);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_main, fragment);
        transaction.commit();
    }
}