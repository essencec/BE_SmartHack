package com.example.be_smarthack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class Navigation {

    public static void goMainActivity(Activity activity) {
        Intent i = new Intent(activity, MainActivity.class);
        activity.startActivity(i);
        activity.finish();
    }

    public static void goMessageListActivity(Activity activity) {
        Intent i = new Intent(activity, MessageListActivity.class);
        activity.startActivity(i);
    }

    public static void switchFragment(Context context, Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.replaceFragment(fragment);
        }

    }
}