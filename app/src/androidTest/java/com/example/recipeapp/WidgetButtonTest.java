package com.example.recipeapp;

import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

/*
 * Checks if button that saves ingredients to widget is working.
 * */

@RunWith(AndroidJUnit4ClassRunner.class)
public class WidgetButtonTest {

    public static final String TAG = WidgetButtonTest.class.getSimpleName();

    @Test
    public void clickRecyclerViewItem() {
        Espresso.onView(withId(R.id.save_to_widget))
                .perform(ViewActions.click());
        Log.e(TAG, "test was successful");
    }
}
