package com.yassir.show.viwmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yassir.show.model.repository.API
import com.yassir.show.model.data.MovieListResponse
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    // ------------------------------
    private val popular  = MutableLiveData<List<MovieListResponse.Movie>>()
    private val topRated = MutableLiveData<List<MovieListResponse.Movie>>()
    private val upcoming = MutableLiveData<List<MovieListResponse.Movie>>()

    init {
        viewModelScope.launch {
            val popularList  : ArrayList<MovieListResponse.Movie> = arrayListOf()
            val topRatedList : ArrayList<MovieListResponse.Movie> = arrayListOf()
            val upcomingList : ArrayList<MovieListResponse.Movie> = arrayListOf()

            withContext(Dispatchers.IO){
                popularList.addAll(API.MOVIE.popular().execute().body()?.results ?: listOf())
                topRatedList.addAll(API.MOVIE.topRated().execute().body()?.results ?: listOf())
                upcomingList.addAll(API.MOVIE.upcoming().execute().body()?.results ?: listOf())
            }

            popular.value  = popularList
            topRated.value = topRatedList
            upcoming.value = upcomingList
        }
    }

    // ------------------------------
    fun popular()  : LiveData<List<MovieListResponse.Movie>> = popular
    fun topRated() : LiveData<List<MovieListResponse.Movie>> = topRated
    fun upcoming() : LiveData<List<MovieListResponse.Movie>> = upcoming

}