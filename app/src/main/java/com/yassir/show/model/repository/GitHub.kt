package com.yassir.show.model.repository

import com.yassir.show.model.repository.interfaces.UserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHub {

    // ----------------------------------------
    private val BASE_URL = "https://api.github.com/"
    // ----------------------------------------
    private var userInterface : UserInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userInterface = retrofit.create(UserInterface::class.java)
    }

    object USER {
        fun user(username:String) = userInterface.user(username)
    }
}