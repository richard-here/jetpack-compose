package com.example.moviejetpackcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.ui.theme.MovieJetpackComposeTheme
import com.example.moviejetpackcompose.ui.theme.Shapes

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = Shapes.medium,
        elevation = 4.dp,
        modifier = modifier,
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon_content_desc),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = stringResource(R.string.search_bar_placeholder_text))
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .heightIn(min = 48.dp)
                .clip(Shapes.medium),
            singleLine = true,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun SearchBarPreview() {
    MovieJetpackComposeTheme {
        SearchBar(
            query = "",
            onQueryChange = {}
        )
    }
}