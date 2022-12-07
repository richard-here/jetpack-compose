package com.example.moviejetpackcompose.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.data.FakeMovieDataSource
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieJetpackComposeTheme {
                HomeScreen(
                    navigateToDetail = {}
                )
            }
        }
    }

    @Test
    fun home_displaysList() {
        FakeMovieDataSource.dummyMovies.forEachIndexed { index, movie ->
            composeTestRule.onNodeWithTag("MovieList").performScrollToIndex(index)
            composeTestRule.onNodeWithText(movie.title).assertIsDisplayed()
        }
    }

    @Test
    fun home_searchNonExistentMovie_displaysNotFoundText() {
        val searchPlaceholder = composeTestRule.activity.getString(R.string.search_bar_placeholder_text)
        val notFoundText = composeTestRule.activity.getString(R.string.no_movie_found_text)
        composeTestRule.onNodeWithText(searchPlaceholder).performTextInput("nonexistent movie")
        composeTestRule.onNodeWithText(notFoundText).assertIsDisplayed()
    }

    @Test
    fun home_searchMovie_displaysMovieWithSubstring() {
        val searchPlaceholder = composeTestRule.activity.getString(R.string.search_bar_placeholder_text)
        composeTestRule.onNodeWithText(searchPlaceholder).performTextInput("cop")
        composeTestRule
            .onNodeWithText("cop")
            .assertIsDisplayed()
    }
}