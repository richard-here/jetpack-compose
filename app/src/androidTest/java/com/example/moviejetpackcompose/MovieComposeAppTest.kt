package com.example.moviejetpackcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.moviejetpackcompose.data.FakeMovieDataSource
import com.example.moviejetpackcompose.ui.navigation.Screen
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieComposeAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent { 
            MovieJetpackComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MovieComposeApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("MovieList").performScrollToIndex(5)
        composeTestRule.onNodeWithText(FakeMovieDataSource.dummyMovies[5].title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(FakeMovieDataSource.dummyMovies[5].title).assertIsDisplayed()
    }

    @Test
    fun navHost_topAppBarNavigation_working() {
        val aboutNavContentDesc = composeTestRule.activity.getString(R.string.about_nav_title)
        val favoriteNavContentDesc = composeTestRule.activity.getString(R.string.favorite_nav_title)
        val backNavContentDesc = composeTestRule.activity.getString(R.string.top_bar_back_icon_content_desc)
        composeTestRule.onNodeWithContentDescription(aboutNavContentDesc).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithContentDescription(backNavContentDesc).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithContentDescription(favoriteNavContentDesc).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
    }
}