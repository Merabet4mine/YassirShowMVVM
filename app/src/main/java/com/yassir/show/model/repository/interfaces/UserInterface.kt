package com.yassir.show.model.repository.interfaces

import com.yassir.show.model.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInterface {

    @GET("users/{user}")
    fun user(@Path("user") username:String) : Call<UserResponse>

}