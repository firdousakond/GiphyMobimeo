package com.giphy.mobimeo.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.giphy.mobimeo.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GiphyActivityTest {

    @Test
    fun recyclerviewZeroItemCheck() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.rvGif)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            assertEquals(0, recyclerView.adapter?.itemCount)
        }
    }

    @Test
    fun loadsTheTestResultsWhenSearchForGiphy() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.rvGif)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            assertEquals(0, recyclerView.adapter?.itemCount)
        }

        onView(withId(R.id.etSearch)).perform(
            typeText("avenger"),
            pressImeActionButton()
        )
        Thread.sleep(5000)
        onView(withId(R.id.rvGif)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            assertTrue(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun verifyRecyclerItemClick() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.etSearch)).perform(
            typeText("avenger"),
            pressImeActionButton()
        )
        Thread.sleep(5000)
        onView(withId(R.id.rvGif)).check { _, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
        }
        onView(withId(R.id.rvGif)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
    }
}