package com.example.firebasekotlin.Network

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class LogoutPOST {

    private lateinit var application : Application
    private lateinit var auth : FirebaseAuth
    private lateinit var data : MutableLiveData<Boolean>

    constructor(application: Application) {
        this.application = application
        auth = FirebaseAuth.getInstance()
        data = MutableLiveData()
    }

    fun logoutAccount() : LiveData<Boolean>{
        auth.signOut()
        data.value = true

        return data
    }
}