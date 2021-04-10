package com.app.movieguidemvvm.ui.moviescollection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.movieguidemvvm.repository.MoviesCollectionRepository

class MoviesCollectionViewModelFactory(private val moviesCollectionRepository: MoviesCollectionRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesCollectionViewModel(moviesCollectionRepository) as T
    }

}