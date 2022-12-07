package com.example.moviejetpackcompose.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.data.MovieRepository
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieJetpackComposeTheme {
                DetailScreen(
                    detailId = 1,
                    navigateBack = {},
                )
            }
        }
    }

    @After
    fun afterTest() {
        MovieRepository.getInstance().removeFromFavorite(1)
    }

    @Test
    fun detail_displaysFavoriteIcon() {
        val favoriteContentDesc = composeTestRule.activity.getString(R.string.add_to_favorite_fab_content_desc)
        composeTestRule.onNodeWithContentDescription(favoriteContentDesc).assertIsDisplayed()
    }

    @Test
    fun clickFavorite_displaysUnfavoriteIcon() {
        val favoriteContentDesc = composeTestRule.activity.getString(R.string.add_to_favorite_fab_content_desc)
        composeTestRule.onNodeWithContentDescription(favoriteContentDesc).performClick()
        val unfavoriteContentDesc = composeTestRule.activity.getString(R.string.remove_from_favorite_fab_content_desc)
        composeTestRule.onNodeWithContentDescription(unfavoriteContentDesc).assertIsDisplayed()
    }

    @Test
    fun clickUnfavorite_displaysFavoriteIcon() {
        val favoriteContentDesc = composeTestRule.activity.getString(R.string.add_to_favorite_fab_content_desc)
        composeTestRule.onNodeWithContentDescription(favoriteContentDesc).performClick()
        val unfavoriteContentDesc = composeTestRule.activity.getString(R.string.remove_from_favorite_fab_content_desc)
        composeTestRule.onNodeWithContentDescription(unfavoriteContentDesc).performClick()
        composeTestRule.onNodeWithContentDescription(favoriteContentDesc).assertIsDisplayed()
    }

    @Test
    fun clickFavorite_displaysFavoriteSnackbar() {
        val favoriteSnackbar = composeTestRule.activity.getString(R.string.add_to_favorite_snackbar_text)
        val favoriteContentDesc = composeTestRule.activity.getString(R.string.add_to_favorite_fab_content_desc)
        composeTestRule.onNodeWithContentDescription(favoriteContentDesc).performClick()
        composeTestRule.onNodeWithText(favoriteSnackbar).assertIsDisplayed()
    }

    @Test
    fun clickUnfavorite_displaysUnfavoriteSnackbar() {
        val favoriteContentDesc = composeTestRule.activity.getString(R.string.add_to_favorite_fab_content_desc)
        val snackbarActionButtonText = composeTestRule.activity.getString(R.string.ok_snackbar_text)
        composeTestRule.onNodeWithContentDescription(favoriteContentDesc).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(snackbarActionButtonText).performClick()
        val unfavoriteContentDesc = composeTestRule.activity.getString(R.string.remove_from_favorite_fab_content_desc)
        val unfavoriteSnackbar = composeTestRule.activity.getString(R.string.remove_from_favorite_snackbar_text)
        composeTestRule.onNodeWithContentDescription(unfavoriteContentDesc).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(unfavoriteSnackbar).assertIsDisplayed()
    }
}