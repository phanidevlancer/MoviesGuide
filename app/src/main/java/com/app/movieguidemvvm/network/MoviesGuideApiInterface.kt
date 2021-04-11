package com.app.movieguidemvvm.network

import com.app.movieguidemvvm.model.MovieInformationResponse
import com.app.movieguidemvvm.model.MoviesCollectionResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesGuideApiInterface {

    @GET("?apikey=5cb4645b")
    suspend fun getMoviesColletionData(@Query("s") searchQuery: String): Response<MoviesCollectionResponse>

    @GET("?apikey=5cb4645b")
    suspend fun getMoviesInformationData(@Query("i") movieID: String): Response<MovieInformationResponse>

    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"
        operator fun invoke(): MoviesGuideApiInterface {
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesGuideApiInterface::class.java)
        }
    }
}