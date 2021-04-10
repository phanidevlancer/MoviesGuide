package com.app.movieguidemvvm.ui.movieinformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.movieguidemvvm.R
import com.app.movieguidemvvm.databinding.FragmentMovieInformationBinding
import com.app.movieguidemvvm.databinding.FragmentMoviesCollectionBinding
import com.app.movieguidemvvm.network.MoviesGuideApiInterface
import com.app.movieguidemvvm.repository.MovieInformationRepository
import com.app.movieguidemvvm.repository.MoviesCollectionRepository
import com.app.movieguidemvvm.ui.moviescollection.MoviesCollectionViewModel
import com.app.movieguidemvvm.ui.moviescollection.MoviesCollectionViewModelFactory


class MovieInformationFragment : Fragment() {

    private lateinit var binding : FragmentMovieInformationBinding
    private lateinit var viewModel: MovieInformationViewModel
    private lateinit var factory: MovieInformationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_information, container, false)
        factory = MovieInformationViewModelFactory(MovieInformationRepository(
                MoviesGuideApiInterface.invoke())
        )
        viewModel = ViewModelProvider(this,factory).get(MovieInformationViewModel::class.java)
        getMoviesInformation()

        return binding.root
    }

    private fun getMoviesInformation() {
        viewModel.getMoviesInformation("tt3787590")
        viewModel.movieInformation.observe(viewLifecycleOwner, Observer {

        })
    }

}