package com.yassir.show.view.activity

import android.annotation.SuppressLint
import com.yassir.show.R
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.yassir.show.model.repository.API
import com.yassir.show.view.adapter.CastAdapter
import com.yassir.show.view.adapter.MoviesAdapter
import com.yassir.show.view.adapter.SmallAdapter
import com.yassir.show.view.base.Activity
import com.yassir.show.view.utils.linearLayout
import com.yassir.show.view.utils.loadImage
import com.yassir.show.view.utils.onClick
import com.yassir.show.viwmodel.activity.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : Activity(R.layout.activity_details) {

    private val viewModel : DetailsViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate() {
        _back.onClick { finish() }
        val movie_id = intent.extras!!.getInt("movie_id")

        _genres.linearLayout(RecyclerView.HORIZONTAL)
        _cast.linearLayout(RecyclerView.HORIZONTAL)
        _recommendations.linearLayout(RecyclerView.HORIZONTAL)

        viewModel.movie(movie_id).observe(this){ movie ->
            if (!movie.video) _play.visibility = View.GONE
            // else _play.onClick { SheetDemo(this).onCreate() }
            _poster.loadImage(API.image(movie.posterPath.toString(), 500))
            _spoken_languages.run {
                linearLayout(RecyclerView.HORIZONTAL)
                adapter = SmallAdapter(this@DetailsActivity, movie.spokenLanguages.map { it.name })
            }
            _title.text = movie.title
            if (!movie.adult) _adult.visibility = View.GONE
            _release_date.text = movie.releaseDate
            _runtime.text = "${movie.runtime/60}h ${movie.runtime%60}m"
            _vote_average.text = movie.voteAverage.toString()
            _vote_count.text = "(${movie.voteCount})"
            _genres.adapter = SmallAdapter(this@DetailsActivity, movie.genres.map { it.name }, true)
            _overview.text = movie.overview
            viewModel.cast(movie_id).observe(this){ _cast.adapter = CastAdapter(this, it) }
            viewModel.recommendations(movie_id).observe(this){ _recommendations.adapter = MoviesAdapter(this, it) }
            production_companies.text = movie.productionCompanies.map { it.name }.joinToString("  •  ")
        }
    }
}