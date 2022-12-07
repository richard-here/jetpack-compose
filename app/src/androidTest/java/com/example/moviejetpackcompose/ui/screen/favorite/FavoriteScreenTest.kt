package com.example.moviejetpackcompose.ui.screen.favorite

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.data.MovieRepository
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import org.junit.Rule
import org.junit.Test

class FavoriteScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun favorite_displaysFavoriteMovies() {
        MovieRepository.getInstance().addToFavorite(1)
        MovieRepository.getInstance().addToFavorite(2)
        composeTestRule.setContent {
            MovieJetpackComposeTheme {
                FavoriteScreen(
                    navigateToDetail = {},
                )
            }
        }
        val movieTitles = MovieRepository.getInstance().getFavoriteMovies()
        movieTitles.forEachIndexed { index, movie ->
            composeTestRule.onNodeWithTag("FavoriteList").performScrollToIndex(index)
            composeTestRule.onNodeWithText(movie.title).assertIsDisplayed()
        }
    }

    @Test
    fun favorite_displaysNoMovies() {
        composeTestRule.setContent {
            MovieJetpackComposeTheme {
                FavoriteScreen(
                    navigateToDetail = {},
                )
            }
        }
        val notFoundText = composeTestRule.activity.getString(R.string.no_movie_found_text)
        composeTestRule.onNodeWithText(notFoundText).assertIsDisplayed()
    }
}