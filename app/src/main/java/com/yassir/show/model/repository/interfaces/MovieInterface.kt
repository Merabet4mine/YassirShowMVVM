package com.yassir.show.model.repository.interfaces

import com.yassir.show.model.data.CreditResponse
import com.yassir.show.model.data.MovieListResponse
import com.yassir.show.model.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieInterface {

    @GET("movie/popular")
    fun popular() : Call<MovieListResponse>

    @GET("movie/top_rated")
    fun topRated() : Call<MovieListResponse>

    @GET("movie/upcoming")
    fun upcoming() : Call<MovieListResponse>

    @GET("movie/{movie_id}/recommendations")
    fun recommendations(@Path("movie_id") id:Int) : Call<MovieListResponse>



    @GET("movie/{movie_id}")
    fun details(@Path("movie_id") id:Int) : Call<MovieResponse>

    @GET("movie/latest")
    fun latest() : Call<MovieResponse>


    @GET("movie/{movie_id}/credits")
    fun credits(@Path("movie_id") id:Int) : Call<CreditResponse>

}