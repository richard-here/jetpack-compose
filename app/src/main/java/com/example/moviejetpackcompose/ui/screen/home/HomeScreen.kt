package com.example.moviejetpackcompose.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.moviejetpackcompose.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        val query by viewModel.query
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMovies()
            }
            is UiState.Success -> {
                HomeContent(
                    movies = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    search = viewModel::search,
                    query = query,
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
fun HomeContent(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    search: (String) -> Unit,
    query: String,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("MovieList"),
    ) {
        item(
            span = { GridItemSpan(2) },
        ) {
            SearchBar(
                query = query,
                onQueryChange = search,
                modifier = Modifier,
            )
        }
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