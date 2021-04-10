package com.app.movieguidemvvm.repository

import com.app.movieguidemvvm.model.MoviesCollectionResponse
import com.app.movieguidemvvm.network.MoviesGuideApiInterface
import com.app.movieguidemvvm.network.SafeApiRequest

class MoviesCollectionRepository(private val api: MoviesGuideApiInterface
) : SafeApiRequest() {

    suspend fun getMoviesCollection(
        searchQuery: String,
    ): MoviesCollectionResponse {
        return apiRequest {
            api.getMoviesColletionData(searchQuery) }
    }
}