package com.yassir.show.view.fragment

import android.annotation.SuppressLint
import com.yassir.show.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.yassir.show.model.data.MovieListResponse
import com.yassir.show.view.adapter.MoviesAdapter
import com.yassir.show.view.base.Activity
import com.yassir.show.view.base.Fragment
import com.yassir.show.view.utils.linearLayout
import com.yassir.show.viwmodel.fragment.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home){

    private val viewModel : HomeViewModel by viewModels()

    private val _popular  get() = find<RecyclerView>(R.id._popular)
    private val _topRated get() = find<RecyclerView>(R.id._top_rated)
    private val _upcoming get() = find<RecyclerView>(R.id._upcoming)

    override fun onCreateView() {
        val activity = context_ as Activity

        operator fun RecyclerView.invoke(liveData:LiveData<List<MovieListResponse.Movie>>){
            linearLayout(RecyclerView.HORIZONTAL)
            adapter = MoviesAdapter(context_, listOf())
            liveData.observe(activity) { list -> adapter = MoviesAdapter(context_, list) }
        }
        _popular(viewModel.popular())
        _topRated(viewModel.topRated())
        _upcoming(viewModel.upcoming())
    }

}