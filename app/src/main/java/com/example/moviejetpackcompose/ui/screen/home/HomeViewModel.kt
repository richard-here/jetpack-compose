package com.example.moviejetpackcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviejetpackcompose.data.MovieRepository
import com.example.moviejetpackcompose.model.Movie
import com.example.moviejetpackcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Movie>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String>
        get() = _query

    fun getAllMovies() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAllMovies())
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _query.value = query
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.searchMovie(query))
        }
    }
}