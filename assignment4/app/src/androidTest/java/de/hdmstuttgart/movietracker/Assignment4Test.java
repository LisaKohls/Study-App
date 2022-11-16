package de.hdmstuttgart.movietracker;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import de.hdmstuttgart.movietracker.ui.MainActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Assignment4Test {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void assignment4Test() {

        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.searchMovieTitleEditText))
                .perform(replaceText("Gold"), closeSoftKeyboard());

        onView(withId(R.id.searchMovieTitleButton))
                .perform(click());

        onView(withId(R.id.searchRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("Goldfinger")))));

        onView(withId(R.id.searchRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("Goldfinger")))));

        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.searchMovieTitleEditText))
                .perform(replaceText("uss"), closeSoftKeyboard());

        onView(withId(R.id.searchMovieTitleButton))
                .perform(click());

        onView(withId(R.id.searchRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("From Russia with Love")))));

        onView(withId(R.id.searchRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(1, hasDescendant(withText("From Russia with Love")))));

        onView(withId(R.id.homeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("From Russia with Love")))));

        activityScenarioRule.getScenario().close();

        ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("From Russia with Love")))));

    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
}