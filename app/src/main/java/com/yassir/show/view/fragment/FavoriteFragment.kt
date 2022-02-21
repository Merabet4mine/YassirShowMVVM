package com.yassir.show.view.fragment

import androidx.fragment.app.viewModels
import com.yassir.show.R
import com.yassir.show.view.base.Fragment
import com.yassir.show.viwmodel.fragment.HomeViewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite){

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView() {}
}