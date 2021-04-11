package com.app.movieguidemvvm.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.movieguidemvvm.R
import com.app.movieguidemvvm.model.MoviesCollectionResponse
import com.app.movieguidemvvm.model.Search
import com.bumptech.glide.Glide

class MoviesCollectionRecyclerAdapater(private val activity: Activity) : RecyclerView.Adapter<MoviesCollectionRecyclerAdapater.MovieItemViewHolder>() {


    private var moviesList: List<Search> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_row,parent,false)
        return MovieItemViewHolder(inflate)
    }


    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(moviesList[position],activity)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateRecyclerData(moviesCollectionResponse: MoviesCollectionResponse?) {
        if(moviesCollectionResponse!=null){
           moviesList = moviesCollectionResponse.Search
            notifyDataSetChanged()
        }
    }


    class MovieItemViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val root : ConstraintLayout = view.findViewById(R.id.root)
        val titleTv : TextView = view.findViewById(R.id.movieTitle)
        val movieCover : ImageView = view.findViewById(R.id.movieCover)
        val yearTv : TextView = view.findViewById(R.id.year)

        fun bind(movieData: Search,activity: Activity){
            root.tag = movieData
            titleTv.text = movieData.Title
            yearTv.text = movieData.Year
            Glide
                .with(activity)
                .load(movieData.Poster)
                .centerCrop()
                .into(movieCover);
        }
    }

}