package com.example.moviejetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import com.example.moviejetpackcompose.ui.theme.Shapes

@Composable
fun MovieItem(
    title: String,
    releaseYear: Int,
    genres: String,
    userScore: Int,
    creator: String,
    image: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = Shapes.medium,
        elevation = 4.dp,
        modifier = modifier,
    ) {
        Column(
            modifier = modifier,
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(Shapes.large)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = "(${releaseYear})",
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                    )
                )
                Text(
                    text = genres,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp,
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier,
                ) {
                    Text(
                        text = creator,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                    Text(
                        text = stringResource(R.string.movie_item_creator),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp,
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val color = MaterialTheme.colors.primary
                    Text(
                        text = stringResource(R.string.user_score_percentage, userScore),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .padding(12.dp)
                            .drawBehind {
                                drawCircle(
                                    color = color,
                                    radius = this.size.maxDimension,
                                )
                            },
                        color = MaterialTheme.colors.onPrimary,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.user_score_text),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 14.sp,
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    widthDp = 200,
    heightDp = 500,
)
fun MovieItemPreview() {
    MovieJetpackComposeTheme {
        MovieItem(
            "The Astronaut",
            2022,
            "Fantasy & Sci-Fi",
            90,
            "Creator A",
            R.drawable.example_movie_poster,
        )
    }
}