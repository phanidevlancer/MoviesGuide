package com.app.movieguidemvvm.ui.movieinformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.movieguidemvvm.repository.MovieInformationRepository
import com.app.movieguidemvvm.repository.MoviesCollectionRepository

class MovieInformationViewModelFactory(private val repository: MovieInformationRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieInformationViewModel(repository) as T
    }

}