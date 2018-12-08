package com.dicoding.ridho.kotlinfinalsubmission


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.dicoding.ridho.kotlinfinalsubmission.R.id.*
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        onView(allOf(withId(rv_match), isDisplayed())).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(allOf(withId(rv_match), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(5000)
        onView(withId(tvDateEvent)).check(matches(isDisplayed()))
        onView(withId(tvStrHomeTeam)).check(matches(isDisplayed()))
        onView(withId(tvStrAwayTeam)).check(matches(isDisplayed()))
        onView(withId(imgHomeTeam)).check(matches(isDisplayed()))
        onView(withId(imgAwayTeam)).check(matches(isDisplayed()))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        pressBack()
        Thread.sleep(2000)
        onView(withText("LAST")).perform(click())
        onView(allOf(withId(rv_match), isDisplayed())).check(matches(isDisplayed()))
        onView(allOf(withId(rv_match), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        Thread.sleep(5000)
        onView(withId(tvDateEvent)).check(matches(isDisplayed()))
        onView(withId(tvStrHomeTeam)).check(matches(isDisplayed()))
        onView(withId(tvStrAwayTeam)).check(matches(isDisplayed()))
        onView(withId(imgHomeTeam)).check(matches(isDisplayed()))
        onView(withId(imgAwayTeam)).check(matches(isDisplayed()))
        onView(withId(tvHomeScore)).check(matches(isDisplayed()))
        onView(withId(tvAwayScore)).check(matches(isDisplayed()))
        onView(withId(tvGoalHomeDetails)).check(matches(isDisplayed()))
        onView(withId(tvGoalAwayDetails)).check(matches(isDisplayed()))
        onView(withId(tvHomeShot)).check(matches(isDisplayed()))
        onView(withId(tvAwayShot)).check(matches(isDisplayed()))
        onView(withId(tvHomeGoalKeeper)).check(matches(isDisplayed()))
        onView(withId(tvAwayGoalKeeper)).check(matches(isDisplayed()))
        onView(withId(tvHomeDefense)).check(matches(isDisplayed()))
        onView(withId(tvAwayDefense)).check(matches(isDisplayed()))
        onView(withId(tvHomeMidfield)).check(matches(isDisplayed()))
        onView(withId(tvAwayMidfield)).check(matches(isDisplayed()))
        onView(withId(tvAwaySubstitutes)).perform(ViewActions.scrollTo())
        onView(withId(tvHomeForward)).check(matches(isDisplayed()))
        onView(withId(tvAwayForward)).check(matches(isDisplayed()))
        onView(withId(tvHomeSubstitutes)).check(matches(isDisplayed()))
        onView(withId(tvAwaySubstitutes)).check(matches(isDisplayed()))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        pressBack()
        Thread.sleep(5000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        onView(allOf(withId(rv_match), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        pressBack()

        Thread.sleep(5000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        Thread.sleep(5000)
        onView(allOf(withId(rv_team), isDisplayed())).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withText("Arsenal")).perform(click())
        Thread.sleep(2000)
        onView(withText("OVERVIEW")).perform(click())
        Thread.sleep(2000)
        onView(withText("PLAYERS")).perform(click())
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )))
        Thread.sleep(4000)
        onView(withText("Aaron Ramsey")).perform(click())
        Thread.sleep(3000)

    }

}