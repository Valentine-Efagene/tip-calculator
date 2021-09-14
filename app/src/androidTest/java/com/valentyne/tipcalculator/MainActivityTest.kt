package com.valentyne.tipcalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var stringToBeTyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify valid string
        stringToBeTyped = "101"
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.cost_of_service))
            .perform(clearText(), typeText(stringToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.calculate_button)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.cost_of_service))
            .check(matches(withText(stringToBeTyped)))
    }

    @Test
    fun calculateTipFlow() {
        onView(withId(R.id.cost_of_service))
            .perform(clearText(), typeText(stringToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.calculate_button)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.cost_of_service))
            .check(matches(withText(stringToBeTyped)))

        // Select GOOD
        onView(withId(R.id.option_eighteen_percent)).perform(click())

        // Calculate tip
        onView(withId(R.id.calculate_button)).perform(click())

        // Check that the tip value has been updated.
        onView(withId(R.id.tip_result))
            .check(matches(withText("18.18")))

        // Round tip
        onView(withId(R.id.round_up_switch)).perform(click())

        // Check that the tip value has been updated.
        onView(withId(R.id.tip_result))
            .check(matches(withText("18.0")))
    }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}