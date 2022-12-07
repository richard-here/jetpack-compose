package com.example.moviejetpackcompose.data

import com.example.moviejetpackcompose.model.Movie

class MovieRepository {
    private val movies = mutableListOf<Movie>()
    private val favoriteMovies = mutableListOf<Movie>()

    init {
        if (movies.isEmpty()) {
            FakeMovieDataSource.dummyMovies.forEach {
                movies.add(it)
            }
        }
    }

    fun getAllMovies() : MutableList<Movie> {
        return movies
    }

    fun getMovieById(detailId: Long) : Movie {
        return movies.first {
            it.id == detailId
        }
    }

    fun searchMovie(searchTerm: String) : List<Movie> {
        return movies.filter {
            it.title.contains(searchTerm, ignoreCase = true)
        }
    }

    fun getFavoriteMovies() : MutableList<Movie> {
        return favoriteMovies
    }

    fun isMovieFavorite(id: Long) : Boolean {
        return favoriteMovies.find { it.id == id } != null
    }

    fun addToFavorite(id: Long) {
        if (!isMovieFavorite(id)) {
            movies.find { it.id == id }?.let {
                favoriteMovies.add(it)
            }
        }
    }

    fun removeFromFavorite(id: Long) {
        favoriteMovies.find { it.id == id }?.let {
            favoriteMovies.remove(it)
        }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository().apply {
                    instance = this
                }
            }
    }
}