package com.farroos.movietvapp_submissionbajp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule

import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.utility.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val movieDummy = DataDummy.generateDummyMovie()
    private val tvShowDummy = DataDummy.generateDummyTvShow()

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieDummy.size
            )
        )
    }

    @Test
    fun loadTvShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShowDummy.size
            )
        )
    }


    @Test
    fun loadTvShowDetail() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.txt_actor)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_actor)).check(matches(withText(tvShowDummy[0].actor)))
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_description)).check(matches(withText(tvShowDummy[0].description)))
        onView(withId(R.id.txt_director)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_director)).check(matches(withText(tvShowDummy[0].director)))
        onView(withId(R.id.txt_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_genre)).check(matches(withText(tvShowDummy[0].genre)))
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(withText(tvShowDummy[0].title)))
        onView(withId(R.id.txt_year)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_year)).check(matches(withText(tvShowDummy[0].year)))
        onView(withId(R.id.txt_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_duration)).check(matches(withText(tvShowDummy[0].duration)))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(withContentDescription(R.string.gambar_poster_film)))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.txt_actor)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_actor)).check(matches(withText(movieDummy[0].actor)))
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_description)).check(matches(withText(movieDummy[0].description)))
        onView(withId(R.id.txt_director)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_director)).check(matches(withText(movieDummy[0].director)))
        onView(withId(R.id.txt_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_genre)).check(matches(withText(movieDummy[0].genre)))
        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(withText(movieDummy[0].title)))
        onView(withId(R.id.txt_year)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_year)).check(matches(withText(movieDummy[0].year)))
        onView(withId(R.id.txt_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_duration)).check(matches(withText(movieDummy[0].duration)))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(withContentDescription(R.string.gambar_poster_film)))
    }

}