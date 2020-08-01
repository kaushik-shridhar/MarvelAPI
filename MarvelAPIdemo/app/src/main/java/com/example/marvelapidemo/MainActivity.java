package com.example.marvelapidemo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.marvelapidemo.Fragments.CharacterFragment;
import com.example.marvelapidemo.Fragments.ComicFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CharacterFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.nav_characters:
                        selectorFragment = new CharacterFragment();
                        break;

                    case R.id.nav_comics:
                        selectorFragment = new ComicFragment();
                        break;
                }



                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();

                return true;
            }
        });
    }
}
