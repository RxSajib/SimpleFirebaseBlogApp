package com.example.firebasekotlin.Network

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.Model.Constant
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserPOST {

    lateinit var application : Application
    lateinit var database : DatabaseReference
    lateinit var data: MutableLiveData<Boolean>

    constructor(application: Application) {
        this.application = application
        database = FirebaseDatabase.getInstance().reference
        data = MutableLiveData()
    }

    fun uploaddata( name : String, email : String, id : String) : LiveData<Boolean>{

        var timestamp = System.currentTimeMillis()
        var map = HashMap<String, Any>()
        map.put(Constant.Username, name)
        map.put(Constant.UserEmail, email)
        map.put(Constant.UserID, id)

        database.child(Constant.User).child(timestamp.toString())
            .updateChildren(map)
            .addOnCompleteListener {
                data.value = true

            }.addOnFailureListener {
                data.value = false
            }

        return data
    }
}