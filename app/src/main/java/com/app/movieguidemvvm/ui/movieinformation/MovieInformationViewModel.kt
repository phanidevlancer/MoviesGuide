package com.app.movieguidemvvm.ui.movieinformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieguidemvvm.model.MovieInformationResponse
import com.app.movieguidemvvm.repository.MovieInformationRepository
import com.app.movieguidemvvm.repository.MoviesCollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieInformationViewModel(private val repository: MovieInformationRepository) : ViewModel() {

    private var movieInformationData : MutableLiveData<MovieInformationResponse>

    val movieInformation: LiveData<MovieInformationResponse>
        get() = movieInformationData

    private lateinit var movieInformationResponse: MovieInformationResponse

    init {
        movieInformationData = MutableLiveData()
    }

    fun getMoviesInformation(movieID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieInformationResponse = repository.getMovieInformationData(movieID)
        }
    }
}