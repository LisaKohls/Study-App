package de.hdmstuttgart.movietracker;


import android.widget.Button;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Assignment1Test {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void assignment1Test() {
        ViewInteraction appCompatButton = onView(allOf(instanceOf(Button.class), withId(R.id.button)))
                .check(matches(isDisplayed()));

        appCompatButton.perform(click());
        appCompatButton.perform(click());
        appCompatButton.perform(click());
        appCompatButton.perform(click());

        ViewInteraction textview = onView(allOf(instanceOf(TextView.class), withText("Clicked 4 times")))
                .check(matches(isDisplayed()));

        appCompatButton.perform(click());

        ViewInteraction textview2 = onView(allOf(instanceOf(TextView.class), withText("clicks: 5")))
                .check(matches(isDisplayed()));

    }
}