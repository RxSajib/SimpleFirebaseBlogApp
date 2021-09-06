package com.example.firebasekotlin.Network

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.Model.Constant
import com.example.Model.User
import com.google.firebase.database.*
import java.lang.Appendable

class UserGET {

    private lateinit var application : Application
    private lateinit var mutabledata : MutableLiveData<List<User>>
    private lateinit var database : DatabaseReference
    private lateinit var list : ArrayList<User>

    constructor(application: Application) {
        this.application = application
        mutabledata = MutableLiveData()
        database = FirebaseDatabase.getInstance().reference.child(Constant.User)
        list = ArrayList()
    }

    fun getpost() : LiveData<List<User>>{
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                if(snapshot.exists()){

                    for(data in snapshot.children){
                        var serverdata = data.getValue(User::class.java)
                        list.add(serverdata!!)
                        mutabledata.value = list
                    }
                }else{
                    mutabledata.value = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                mutabledata.value = null
            }

        })

        return mutabledata
    }
}