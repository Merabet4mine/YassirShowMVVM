package com.yassir.show.viwmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yassir.show.R

class MainViewModel : ViewModel() {

    // ------------------------------
    private val current = MutableLiveData<Int>().apply { value = 0 }


    // ------------------------------
    fun navigate(to:Int) : Int {
        val action = when(current.value) {
            0 -> when(to) {
                1 -> R.id.action_homeFragment_to_favoriteFragment
                2 -> R.id.action_homeFragment_to_accountFragment
                else -> -1
            }
            1 -> when(to) {
                0 -> R.id.action_favoriteFragment_to_homeFragment
                2 -> R.id.action_favoriteFragment_to_accountFragment3
                else -> -1
            }
            2 -> when(to) {
                0 -> R.id.action_accountFragment_to_homeFragment
                1 -> R.id.action_accountFragment_to_favoriteFragment
                else -> -1
            }
            else -> -1
        }
        if (action != -1) current.value = to
        return action
    }


}