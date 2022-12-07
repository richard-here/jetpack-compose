package com.example.moviejetpackcompose.ui.screen.about

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme

@Composable
fun AboutScreen(
    navigateBack: () -> Unit,
) {
    AboutContent(
        R.drawable.project_owner_image,
        name = stringResource(R.string.project_owner_name),
        email = stringResource(R.string.project_owner_email),
    )
}

@Composable
private fun AboutContent(
    @DrawableRes image: Int,
    name: String,
    email: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(256.dp)
                .clip(CircleShape)
                .shadow(8.dp, CircleShape)
        )
        Spacer(modifier = modifier.height(32.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.h1.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            text = email,
            style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 20.sp,
            )
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    heightDp = 600,
)
@Composable
fun AboutContentPreview() {
    MovieJetpackComposeTheme {
        AboutContent(
            image = R.drawable.project_owner_image,
            name = "Richard",
            email = "richard.herew@gmail.com",
        )
    }
}