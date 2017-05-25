package net.onyxmueller.android.fruity;


import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import net.onyxmueller.android.fruity.data.Fruit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FruitDetailsActivityTest {

    @Rule
    public IntentsTestRule<FruitDetailsActivity> activityTestRule = new IntentsTestRule<>(FruitDetailsActivity.class, true, false);

    @Test
    public void displaysFruitInformation() {
        Intent intent = new Intent();

        Fruit fruit = new Fruit("commonName", "scientificName", "ic_apple", "apples.webp", 100.0, 13.81,
                2.40, 10.00, 0.30);
        Bundle extras = FruitDetailsActivity.newExtras(fruit);
        intent.putExtras(extras);

        activityTestRule.launchActivity(intent);

        onView(withId(R.id.name)).check(matches(withText("commonName")));
        onView(withId(R.id.scientific_name)).check(matches(withText("scientificName")));
    }
}
