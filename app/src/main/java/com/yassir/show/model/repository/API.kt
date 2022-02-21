package com.yassir.show.model.repository

import com.yassir.show.model.repository.interfaces.MovieInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.min


object API {

    // ----------------------------------------
    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"

    // ----------------------------------------
    private var movieInterface : MovieInterface

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url()
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", KEY).build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        movieInterface = retrofit.create(MovieInterface::class.java)
    }
    fun image(name:String, size:Int=200) = "https://image.tmdb.org/t/p/w${min(size, 500)}$name"

    // ----------------------------------------
    object MOVIE {
        fun popular()  = movieInterface.popular()
        fun topRated() = movieInterface.topRated()
        fun upcoming() = movieInterface.upcoming()
        fun latest() = movieInterface.latest()
        fun details(id:Int) = movieInterface.details(id)
        fun credits(id:Int) = movieInterface.credits(id)
        fun recommendations(id:Int) = movieInterface.recommendations(id)
    }

}