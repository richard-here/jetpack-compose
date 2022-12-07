package com.example.moviejetpackcompose.ui.screen.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviejetpackcompose.data.MovieRepository
import com.example.moviejetpackcompose.model.Movie
import com.example.moviejetpackcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<Movie>>
        get() = _uiState

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: MutableState<Boolean>
        get() = _isFavorite

    fun checkIsFavorite(id: Long) {
        viewModelScope.launch {
            _isFavorite.value = repository.isMovieFavorite(id)
        }
    }

    fun getMovieById(detailId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val movie = repository.getMovieById(detailId)
                _uiState.value = UiState.Success(movie)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error in finding movie with ID $detailId")
            }
        }
    }

    fun addToFavorite(id: Long) {
        viewModelScope.launch {
            repository.addToFavorite(id)
            checkIsFavorite(id)
        }
    }

    fun removeFromFavorite(id: Long) {
        viewModelScope.launch {
            repository.removeFromFavorite(id)
            checkIsFavorite(id)
        }
    }
}