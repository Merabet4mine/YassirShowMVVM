package com.yassir.show.viwmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yassir.show.model.data.Account

class AccountViewModel : ViewModel(){

    private val account = MutableLiveData<Account>().apply { value = Account(1, "Professional", "https://mir-s3-cdn-cf.behance.net/user/230/674fed652782717.6193f360f2af8.png", "Merabet Amine") }

    fun account(): LiveData<Account> = account

    fun account(acc:String) {
        account.value = account.value!!.copy(pack = "Free", username = "Mohamed Amine")
    }
}