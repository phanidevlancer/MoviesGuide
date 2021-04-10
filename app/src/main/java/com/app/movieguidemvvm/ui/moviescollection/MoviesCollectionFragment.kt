package com.app.movieguidemvvm.ui.moviescollection

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.movieguidemvvm.R
import com.app.movieguidemvvm.databinding.FragmentMoviesCollectionBinding
import com.app.movieguidemvvm.network.MoviesGuideApiInterface
import com.app.movieguidemvvm.repository.MoviesCollectionRepository
import com.app.movieguidemvvm.ui.adapter.MoviesCollectionRecyclerAdapater


class MoviesCollectionFragment : Fragment() {

    private lateinit var moviesCollectionRecyclerAdapater: MoviesCollectionRecyclerAdapater
    private lateinit var binding : FragmentMoviesCollectionBinding
    private lateinit var viewModel: MoviesCollectionViewModel
    private lateinit var factory: MoviesCollectionViewModelFactory


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movies_collection, container, false)
        factory = MoviesCollectionViewModelFactory(MoviesCollectionRepository(MoviesGuideApiInterface.invoke()))
        viewModel = ViewModelProvider(this,factory).get(MoviesCollectionViewModel::class.java)
        moviesCollectionRecyclerAdapater = MoviesCollectionRecyclerAdapater(context as Activity)
        initRecyclerView()
        getMoviesCollection()
        return binding.root
    }

    private fun getMoviesCollection() {
        viewModel.getMoviesColloectionData("friends")
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            moviesCollectionRecyclerAdapater.updateRecyclerData(it)
        })
    }

    private fun initRecyclerView() {
        binding.moviesCollectionRcv.adapter = moviesCollectionRecyclerAdapater
    }

}