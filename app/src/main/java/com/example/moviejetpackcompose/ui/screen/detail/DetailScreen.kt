package com.example.moviejetpackcompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.di.Injection
import com.example.moviejetpackcompose.ui.ViewModelFactory
import com.example.moviejetpackcompose.ui.common.UiState
import com.example.moviejetpackcompose.ui.components.ErrorSection
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    detailId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.checkIsFavorite(detailId)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val isFavorite by viewModel.isFavorite

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (isFavorite) {
                        viewModel.removeFromFavorite(detailId)
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = context.resources.getString(R.string.remove_from_favorite_snackbar_text),
                                actionLabel = context.resources.getString(R.string.ok_snackbar_text)
                            )
                        }
                    } else {
                        viewModel.addToFavorite(detailId)
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = context.resources.getString(R.string.add_to_favorite_snackbar_text),
                                actionLabel = context.resources.getString(R.string.ok_snackbar_text)
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            ) {
                Icon(
                    imageVector =
                        if (!isFavorite) Icons.Default.FavoriteBorder
                        else Icons.Default.Favorite,
                    contentDescription =
                        if (!isFavorite) stringResource(R.string.add_to_favorite_fab_content_desc)
                        else stringResource(R.string.remove_from_favorite_fab_content_desc),
                )
            }
        }
    ) { innerPadding ->
        viewModel.uiState.collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getMovieById(detailId)
                }
                is UiState.Success -> {
                    val data = uiState.data
                    DetailContent(
                        image = data.image,
                        title = data.title,
                        releaseYear = data.releaseYear,
                        genres = data.genres.joinToString(", "),
                        userScore = data.userScore,
                        overview = data.overview,
                        creator = data.creator,
                        onBackClick = navigateBack,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
                is UiState.Error -> {
                    ErrorSection()
                }
            }
        }
    }
}

@Composable
private fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    releaseYear: Int,
    genres: String,
    userScore: Int,
    overview: String,
    creator: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth()
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(32.dp),
            ) {
                val primary = MaterialTheme.colors.primary
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                        )
                    )
                    Spacer(modifier.width(10.dp))
                    Text(
                        text = "(${releaseYear})",
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp,
                        )
                    )
                }
                Text(
                    text = genres,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                    ),
                )
                Row(
                    modifier = modifier
                        .padding(vertical = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val color = MaterialTheme.colors.primary
                    Text(
                        text = stringResource(R.string.user_score_percentage, userScore),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = modifier
                            .padding(13.dp)
                            .drawBehind {
                                drawCircle(
                                    color = color,
                                    radius = this.size.maxDimension,
                                )
                            },
                        color = MaterialTheme.colors.onPrimary,
                    )
                    Spacer(modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.user_score_text),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
                Text(
                    text = creator,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Text(
                    text = stringResource(R.string.movie_item_creator),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                    ),
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.overview_text),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    ),
                )
                Text(
                    text = overview,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    MovieJetpackComposeTheme {
        DetailContent(
            image = R.drawable.example_movie_poster,
            title = "The Astronaut",
            releaseYear = 2022,
            genres = "Fantasy & Sci-Fi, Mystery",
            userScore = 83,
            overview = "Lorem ipsum",
            creator = "The Creator",
            onBackClick = {},
        )
    }
}