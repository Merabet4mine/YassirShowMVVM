package com.yassir.show.view.adapter

import com.yassir.show.R
import android.content.Context
import android.widget.TextView
import android.widget.ImageView
import android.annotation.SuppressLint
import com.yassir.show.model.data.CreditResponse
import com.yassir.show.model.repository.API
import com.yassir.show.view.base.Adapter
import com.yassir.show.view.utils.loadImage

@SuppressLint("NotifyDataSetChanged")
class CastAdapter(val context:Context, casts:List<CreditResponse.Cast>) : Adapter<Adapter.Holder, CreditResponse.Cast, String>() {

    // --------------------------------------------------
    init {
        contentList.addAll(casts.map {
            if (it.id < 0) Item(LoadHolder(context), it)
            else Item(CastHolder(context), it)
        })
    }

    // --------------------------------------------------
    override var onBindView: (Int) -> Unit = { position ->
        val (holder, item) = contentList[position]
        when(holder){
            is LoadHolder -> {}
            is CastHolder -> { holder(item) }
        }
    }

    // HOLDERS ==========================================
    class CastHolder(context:Context) : Adapter.Holder(context, R.layout.holder_cast) {
        // --------------------------------------------------
        private val _profile_path = find<ImageView>(R.id._profile_path)
        private val _name = find<TextView>(R.id._name)
        private val _character = find<TextView>(R.id._character)
        // --------------------------------------------------
        operator fun invoke(cast:CreditResponse.Cast) {
            //_profile_path.loadImage(Api.image(cast.profilePath ?: ""), R.drawable.default_poster)
            _profile_path.loadImage(API.image(cast.profilePath.toString()))
            _name.text = cast.name
            _character.text = cast.character
        }
    }
    class LoadHolder(context:Context) : Adapter.Holder(context, R.layout.holder_load)
}