package com.unava.dia.commentsdownloader.ui;

import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.model.Comment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onButtonGoClick() {
        onView(withId(R.id.editTextFrom)).perform(typeText("5"));
        onView(withId(R.id.editTextTo)).perform(typeText("15"));
        onView(withId(R.id.editTextTo)).perform(closeSoftKeyboard());

        onView(withId(R.id.buttonGo)).perform(click());
    }
}