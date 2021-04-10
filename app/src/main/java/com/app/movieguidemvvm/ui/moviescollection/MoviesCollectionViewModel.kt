package com.app.movieguidemvvm.ui.moviescollection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieguidemvvm.model.MoviesCollectionResponse
import com.app.movieguidemvvm.repository.MoviesCollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesCollectionViewModel(private val repository: MoviesCollectionRepository) : ViewModel() {

    private var movieCollectionData : MutableLiveData<MoviesCollectionResponse>

    val moviesLiveData: LiveData<MoviesCollectionResponse>
        get() = movieCollectionData

    private lateinit var moviesCollectionResponse: MoviesCollectionResponse

    init {
        movieCollectionData = MutableLiveData()
    }

    fun getMoviesColloectionData(searchQurey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesCollectionResponse = repository.getMoviesCollection(searchQurey)
            movieCollectionData.postValue(moviesCollectionResponse)
        }
    }
}