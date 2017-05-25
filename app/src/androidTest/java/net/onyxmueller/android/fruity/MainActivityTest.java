package net.onyxmueller.android.fruity;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import net.onyxmueller.android.fruity.MainActivity;
import net.onyxmueller.android.fruity.QuizActivity;
import net.onyxmueller.android.fruity.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mainActivityActivityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void fabLaunchesQuizActivityWhenClicked() {
        onView(ViewMatchers.withId(R.id.fab)).perform(click());

        intended(hasComponent(QuizActivity.class.getName()));
    }
}
