package com.example.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class Tabs_ActivityTest {

    String name = "Brad";
    String age = "27";
    String location = "Seattle, WA";
    String job = "Server";
    String desc = "I like long walks";


    //create rule for the second page :http://blog.xebia.com/android-intent-extras-espresso-rules/
    @Rule
    public ActivityTestRule<Tabs_Activity> activityTestRule = new ActivityTestRule<Tabs_Activity>(Tabs_Activity.class) {

        @Override
        protected Intent getActivityIntent() {

            Intent intent = new Intent();

            intent.putExtra(Constants.KEY_NAME, name);
            intent.putExtra(Constants.KEY_AGE, age);
            intent.putExtra(Constants.KEY_LOCATION, location);
            intent.putExtra(Constants.KEY_JOB, job);
            intent.putExtra(Constants.KEY_DESC, desc);

            return intent;
        }
    };

    //PASSED
    @Test
    public void rotateScreenTest(){
        //check the fields are filled.
        onView(withId(R.id.nameDisplayTextView)).check(matches(withText(name)));
        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(location)));
        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));

        //flip the screen
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //check the fields again
        onView(withId(R.id.nameDisplayTextView)).check(matches(withText(name)));
        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(location)));
        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));

        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
//

    @Test
    public void swipeTabsTest(){
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).perform(swipeRight());

        //onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItem(withId(R.id.favorite_button), click()));
        //onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

//        onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions
//                .actionOnItem(RecyclerViewActions.actionOnItem(withId(R.id.favorite_button))), click()));

        onView(ViewMatchers.withId(R.id.my_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(7, ViewActions.click()));

    }

}