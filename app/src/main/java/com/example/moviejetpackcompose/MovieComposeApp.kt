package com.example.moviejetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviejetpackcompose.ui.navigation.NavigationItem
import com.example.moviejetpackcompose.ui.navigation.Screen
import com.example.moviejetpackcompose.ui.screen.about.AboutScreen
import com.example.moviejetpackcompose.ui.screen.detail.DetailScreen
import com.example.moviejetpackcompose.ui.screen.favorite.FavoriteScreen
import com.example.moviejetpackcompose.ui.screen.home.HomeScreen

@Composable
fun MovieComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var isTopDestination by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            isTopDestination = currentRoute != Screen.Home.route
            TopBar(isTopDestination = isTopDestination, navController, modifier)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { detailId ->
                        navController.navigate(Screen.Detail.createRoute(detailId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("detailId") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("detailId") ?: -1L
                DetailScreen(
                    detailId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    isTopDestination: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.favorite_nav_title),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite,
            ),
            NavigationItem(
                title = stringResource(R.string.about_nav_title),
                icon = Icons.Default.Info,
                screen = Screen.About,
            ),
        )
        val homeTitle = stringResource(R.string.home_nav_title)
        var pageTitle by rememberSaveable { mutableStateOf(homeTitle) }

        TopAppBar(
            navigationIcon = if (isTopDestination) {
                {
                    IconButton(
                        onClick = {
                            pageTitle = homeTitle
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                R.string.top_bar_back_icon_content_desc
                            )
                        )
                    }
                }
            } else {
                null
            },
            title = {
                Text(text = pageTitle)
            },
            actions = if (!isTopDestination) {
                {
                    navigationItems.map { item ->
                        IconButton(
                            onClick = {
                                pageTitle = item.title
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        }
                    }
                }
            } else { {} }
        )
    }
}