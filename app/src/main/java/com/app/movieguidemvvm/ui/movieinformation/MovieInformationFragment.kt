package com.app.movieguidemvvm.ui.movieinformation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.movieguidemvvm.R
import com.app.movieguidemvvm.databinding.FragmentMovieInformationBinding
import com.app.movieguidemvvm.model.MovieInformationResponse
import com.app.movieguidemvvm.network.MoviesGuideApiInterface
import com.app.movieguidemvvm.repository.MovieInformationRepository
import com.app.movieguidemvvm.utils.NetworkAvailablityUtils
import com.bumptech.glide.Glide


class MovieInformationFragment : Fragment() {

    private lateinit var binding: FragmentMovieInformationBinding
    private lateinit var viewModel: MovieInformationViewModel
    private lateinit var factory: MovieInformationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_information, container, false
        )
        factory = MovieInformationViewModelFactory(
            MovieInformationRepository(
                MoviesGuideApiInterface.invoke()
            )
        )
        viewModel = ViewModelProvider(this, factory).get(MovieInformationViewModel::class.java)

        val movieID: String = arguments?.getString("MovieId").toString()

        getMoviesInformation(movieID)


        return binding.root
    }

    private fun getMoviesInformation(movieID: String) {
        if(!NetworkAvailablityUtils.hasNetworkAvailable(context)){
            return
        }
        if(viewModel.movieInformation.value == null){
            viewModel.getMoviesInformation(movieID)
        }
        viewModel.movieInformation.observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    private fun updateUI(movieInformationResponse: MovieInformationResponse?) {
        if (movieInformationResponse != null) {
            binding.moviePlot.visibility = View.VISIBLE
            binding.movieTitle.visibility = View.VISIBLE
            binding.movieCover.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.moviePlot.text = movieInformationResponse.Plot
            binding.movieTitle.text = movieInformationResponse.Title
            Glide
                .with(context as Activity)
                .load(movieInformationResponse.Poster)
                .centerCrop()
                .into(binding.movieCover)
        }
    }



}