package com.yassir.show.viwmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yassir.show.model.repository.API
import com.yassir.show.model.data.MovieListResponse
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    // ------------------------------
    private val popular  = MutableLiveData<List<MovieListResponse.Movie>>()
    private val topRated = MutableLiveData<List<MovieListResponse.Movie>>()
    private val upcoming = MutableLiveData<List<MovieListResponse.Movie>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val popularBody = API.MOVIE.popular().execute().body()
            val topRatedBody = API.MOVIE.topRated().execute().body()
            val upcomingBody = API.MOVIE.upcoming().execute().body()
            withContext(Dispatchers.Main) {
                popularBody?.apply { popular.value   = this.results }
                topRatedBody?.apply { topRated.value = this.results }
                upcomingBody?.apply { upcoming.value = this.results }
            }
        }
    }

    // ------------------------------
    fun popular()  : LiveData<List<MovieListResponse.Movie>> = popular
    fun topRated() : LiveData<List<MovieListResponse.Movie>> = topRated
    fun upcoming() : LiveData<List<MovieListResponse.Movie>> = upcoming

}