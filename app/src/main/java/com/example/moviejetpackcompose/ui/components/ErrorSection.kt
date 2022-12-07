package com.example.moviejetpackcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme

@Composable
fun ErrorSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.error_section_text))
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorSectionPreview() {
    MovieJetpackComposeTheme {
        ErrorSection()
    }
}