package com.yassir.show.view.activity

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.yassir.show.R
import com.yassir.show.view.base.Activity
import com.yassir.show.view.utils.onClick
import com.yassir.show.viwmodel.activity.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(R.layout.activity_main) {

    // NAVIGATION BAR --------------------------------
    private val viewModel : MainViewModel by viewModels()
    private lateinit var navController : NavController

    override fun onCreate() {
        navController = Navigation.findNavController(this, R.id._fragment)

        listOf(_home, _favorite, _account).forEachIndexed { index, view ->
            view.onClick {
                val action = viewModel.navigate(index)
                if (action != -1) navController.navigate(action)
            }
        }
    }

    override fun onBackPressed() {}
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}