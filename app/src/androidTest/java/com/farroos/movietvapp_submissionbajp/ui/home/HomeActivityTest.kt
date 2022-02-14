package com.farroos.movietvapp_submissionbajp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule

import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.utility.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovie() {onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                2
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_realease)).check(matches(isDisplayed()))

        onView(withId(R.id.tgl_favorite)).perform(click())
        pressBack()
    }

    @Test
    fun loadTvShow() {
        onView(withText(R.string.tab_2)).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                2
            )
        )
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_realease)).check(matches(isDisplayed()))

        onView(withId(R.id.tgl_favorite)).perform(click())
        pressBack()
    }

    @Test
    fun loadFavoriteMovie(){
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.rcv_favorite_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_realease)).check(matches(isDisplayed()))

        onView(withId(R.id.tgl_favorite)).perform(click())
        pressBack()
    }


    @Test
    fun loadFavoriteTvShow(){
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withText(R.string.tab_4)).perform(click())
        onView(withId(R.id.rcv_favorite_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_realease)).check(matches(isDisplayed()))

        onView(withId(R.id.tgl_favorite)).perform(click())
        pressBack()
    }
}