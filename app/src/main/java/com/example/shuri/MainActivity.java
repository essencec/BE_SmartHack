package com.example.shuri;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shuri.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();

    Fragment mActiveFragment;

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        replaceFragment(new HomeFragment());
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(mBinding.containerFrameLayout.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}