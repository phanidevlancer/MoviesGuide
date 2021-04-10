package com.app.movieguidemvvm.repository

import com.app.movieguidemvvm.model.MovieInformationResponse
import com.app.movieguidemvvm.model.MoviesCollectionResponse
import com.app.movieguidemvvm.network.MoviesGuideApiInterface
import com.app.movieguidemvvm.network.SafeApiRequest

class MovieInformationRepository(private val api: MoviesGuideApiInterface
) : SafeApiRequest() {

    suspend fun getMovieInformationData(
        searchQuery: String,
    ): MovieInformationResponse {
        return apiRequest {
            api.getMoviesInformationData(searchQuery)
        }
    }
}