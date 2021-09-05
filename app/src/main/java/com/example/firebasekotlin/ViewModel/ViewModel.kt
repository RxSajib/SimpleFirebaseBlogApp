package com.example.firebasekotlin.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.firebasekotlin.Network.UserPOST

class ViewModel(application: Application) : AndroidViewModel(application) {


    var postdata = UserPOST(application)

    fun setdata(name: String, email: String, id : String) : LiveData<Boolean>{
       return postdata.uploaddata(name, email, id)
    }

}