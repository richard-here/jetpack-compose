package com.example.moviejetpackcompose.di

import com.example.moviejetpackcompose.data.MovieRepository

object Injection {
    fun provideRepository() : MovieRepository {
        return MovieRepository.getInstance()
    }
}