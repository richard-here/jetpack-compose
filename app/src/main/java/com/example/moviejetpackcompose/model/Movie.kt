package com.example.moviejetpackcompose.model

data class Movie(
    val id: Long,
    val title: String,
    val releaseYear: Int,
    val genres: List<String>,
    val userScore: Int,
    val overview: String,
    val creator: String,
    val image: Int
)