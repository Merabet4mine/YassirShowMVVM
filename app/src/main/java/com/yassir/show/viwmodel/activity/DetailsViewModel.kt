package com.yassir.show.viwmodel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yassir.show.model.repository.API
import com.yassir.show.model.data.CreditResponse
import com.yassir.show.model.data.MovieListResponse
import com.yassir.show.model.data.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel : ViewModel() {

    // ------------------------------
    private val movie = MutableLiveData<MovieResponse>()
    private val cast = MutableLiveData<List<CreditResponse.Cast>>()
    private val recommendations = MutableLiveData<List<MovieListResponse.Movie>>()

    // ------------------------------
    fun movie(id:Int): LiveData<MovieResponse> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = API.MOVIE.details(id).execute().body() ?: MovieResponse()
            withContext(Dispatchers.Main) { movie.postValue(response) }
        }
        return movie
    }
    fun cast(id:Int): LiveData<List<CreditResponse.Cast>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = API.MOVIE.credits(id).execute().body()?.cast ?: listOf()
            withContext(Dispatchers.Main) { cast.value = response }
        }
        return cast
    }
    fun recommendations(id:Int): LiveData<List<MovieListResponse.Movie>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = API.MOVIE.recommendations(id).execute().body()?.results ?: listOf()
            withContext(Dispatchers.Main) { recommendations.value = response }
        }
        return recommendations
    }

}