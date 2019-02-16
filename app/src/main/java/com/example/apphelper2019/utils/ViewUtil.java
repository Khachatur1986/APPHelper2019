package com.example.apphelper2019.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class ViewUtil {
    //https://inducesmile.com/android-programming/how-to-close-or-hide-soft-keyboard-in-android/
    public static void closeKeyboard(Activity activity) {
        try {
            InputMethodManager editTextInput = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            editTextInput.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("AndroidView", "closeKeyboard: " + e);
        }
    }
}
