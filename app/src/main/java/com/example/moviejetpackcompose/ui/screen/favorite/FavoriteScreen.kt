package com.example.moviejetpackcompose.ui.screen.favorite

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.di.Injection
import com.example.moviejetpackcompose.model.Movie
import com.example.moviejetpackcompose.ui.ViewModelFactory
import com.example.moviejetpackcompose.ui.common.UiState
import com.example.moviejetpackcompose.ui.components.ErrorSection
import com.example.moviejetpackcompose.ui.components.MovieItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteMovies()
            }
            is UiState.Success -> {
                FavoriteContent(
                    movies = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {
                ErrorSection()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteContent(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("FavoriteList"),
    ) {
        if (movies.isEmpty()) {
            item(
                span = { GridItemSpan(2) },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val notFoundText = stringResource(R.string.no_movie_found_text)
                    Text(
                        text = notFoundText,
                        modifier = Modifier
                    )
                }
            }
        } else {
            items(movies, key = { it.id }) { data ->
                MovieItem(
                    title = data.title,
                    releaseYear = data.releaseYear,
                    genres = data.genres.joinToString(", "),
                    userScore = data.userScore,
                    creator = data.creator,
                    image = data.image,
                    modifier = modifier
                        .clickable {
                            navigateToDetail(data.id)
                        }
                        .animateItemPlacement(tween(durationMillis = 100))
                )
            }
        }
    }
}