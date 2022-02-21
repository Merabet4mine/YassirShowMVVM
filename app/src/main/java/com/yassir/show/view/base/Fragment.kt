package com.yassir.show.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class Fragment(val layout:Int) : Fragment(layout) {

    // OBJECT -------------------------------------------
    internal lateinit var view : View
    internal var savedInstanceState : Bundle? = null
    protected val context_ get() = view.context

    // --------------------------------------------------
    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?): View? {
        view = inflater.inflate(layout, container, false)
        this.savedInstanceState = savedInstanceState
        onCreateView()
        return view
    }

    // --------------------------------------------------
    abstract fun onCreateView()

    // --------------------------------------------------
    protected fun <T:View> find(id:Int) = view.findViewById<T>(id)!!

}