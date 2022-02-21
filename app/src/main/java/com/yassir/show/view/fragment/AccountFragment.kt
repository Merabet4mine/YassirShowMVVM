package com.yassir.show.view.fragment

import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.yassir.show.R
import com.yassir.show.databinding.FragmentAccountBinding
import com.yassir.show.view.base.Activity
import com.yassir.show.view.base.FragmentBinding
import com.yassir.show.view.utils.loadImage
import com.yassir.show.view.utils.onClick
import com.yassir.show.viwmodel.fragment.AccountViewModel

class AccountFragment : FragmentBinding<FragmentAccountBinding>(R.layout.fragment_account){

    private val viewModel : AccountViewModel by viewModels()

    override fun onCreateView() {
        val activity = context_ as Activity
        val _picture = find<ImageView>(R.id._picture)
        viewModel.account().observe(activity){
            _picture.loadImage(it.picture)
            binding.account = it
        }
        _picture.onClick {
            viewModel.account("")
        }
    }
}