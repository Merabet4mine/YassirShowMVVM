package com.yassir.show.view.adapter

import android.content.Intent
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.yassir.show.R
import com.yassir.show.model.repository.API
import com.yassir.show.model.data.MovieListResponse
import com.yassir.show.view.activity.DetailsActivity
import com.yassir.show.view.base.Adapter
import com.yassir.show.view.utils.loadImage
import com.yassir.show.view.utils.onClick


class MoviesAdapter(val context:Context, movies:List<MovieListResponse.Movie>) : Adapter<Adapter.Holder, MovieListResponse.Movie, String>() {

    init {
        movies.map { contentList.add(Item(MovieHolder(context), it)) }
        if(movies.isEmpty()) contentList.add(Item(LoadingHolder(context), MovieListResponse.Movie()))
    }
    override var onBindView: (Int) -> Unit = { position ->
        val (holder, item) = contentList[position]
        when(holder){
            is LoadingHolder -> { }
            is MovieHolder -> holder(item) {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("movie_id", item.id)
                context.startActivity(intent)
            }
        }
    }

    // --------------------------------------------------
    class LoadingHolder(context: Context) : Adapter.Holder(context, R.layout.holder_load)
    class MovieHolder(context: Context) : Adapter.Holder(context, R.layout.holder_movie) {
        // --------------------------------------------------
        private val _adult = find<CardView>(R.id._adult)
        private val _poster = find<ImageView>(R.id._poster)
        private val _title = find<TextView>(R.id._title)
        private val _vote_average = find<TextView>(R.id._vote_average)
        private val _release_date = find<TextView>(R.id._release_date)
        // --------------------------------------------------
        operator fun invoke(movie: MovieListResponse.Movie, callback:() -> Unit) {
            if (movie.adult) _adult.visibility = View.VISIBLE else _adult.visibility = View.GONE
            _poster.loadImage(API.image(movie.posterPath))
            _title.text = movie.title
            _vote_average.text = movie.voteAverage.toString()
            _release_date.text = movie.releaseDate.take(4)
            itemView.onClick { return@onClick callback() }
        }
    }
}