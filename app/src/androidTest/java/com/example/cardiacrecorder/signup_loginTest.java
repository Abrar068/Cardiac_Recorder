package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import static java.lang.Thread.sleep;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class signup_loginTest {
    @Rule
    public ActivityScenarioRule<signup_login> activityRule =
            new ActivityScenarioRule<>(signup_login.class);

    @Test
    public void test_signup_or_login()
    {
        onView(withId(R.id.signupButton)).perform(click());
        onView(withId(R.id.sign)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.signlog)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.log)).check(matches(isDisplayed()));


    }
    @Test
    public void test_login() throws InterruptedException {
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.log)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextText)).perform(ViewActions.typeText("abc"));
        onView(withId(R.id.editTextTextPassword)).perform(ViewActions.typeText("123456"));
        Espresso.pressBack();

        onView(withId(R.id.button)).perform(click());
        sleep(1000);
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }




}