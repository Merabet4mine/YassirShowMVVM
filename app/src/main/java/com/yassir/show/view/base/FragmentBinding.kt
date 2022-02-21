package com.yassir.show.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class FragmentBinding<T:ViewDataBinding>(val layout:Int) : Fragment(layout) {

    // OBJECT -------------------------------------------
    protected lateinit var binding:T
    protected val context_ get() = binding.root.context

    // --------------------------------------------------
    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layout, container, false)
        onCreateView()
        return binding.root
    }

    // --------------------------------------------------
    abstract fun onCreateView()

    // --------------------------------------------------
    protected fun <T:View> find(id:Int) = binding.root.findViewById<T>(id)!!

}