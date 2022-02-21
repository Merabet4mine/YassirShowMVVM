package com.yassir.show.viwmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yassir.show.model.data.UserResponse
import com.yassir.show.model.repository.GitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountViewModel : ViewModel(){

    private val user = MutableLiveData<UserResponse>().also { rand() }
    fun user() : LiveData<UserResponse> = user
    fun rand() {
        val users = listOf("Zakovitch", "Merabet4mine", "ayoubElhoucine", "0rAX0", "abderraouf-safsaf", "alameya", "Ahmed-Ghiloubi", "azamouchi")
        CoroutineScope(Dispatchers.IO).launch {
            val response = GitHub.USER.user(users.random()).execute().body()
            withContext(Dispatchers.Main) { user.value = response!! }
        }
    }

}