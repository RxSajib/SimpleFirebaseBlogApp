package com.example.firebasekotlin.Network

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.Model.Constant
import com.google.firebase.storage.FirebaseStorage

class ImagePOST {

    private lateinit var application: Application
    private lateinit var imagedatabase : FirebaseStorage
    private lateinit var data : MutableLiveData<String>

    constructor(application: Application) {
        this.application = application
        data = MutableLiveData()
        imagedatabase = FirebaseStorage.getInstance()
    }


    fun uploadimagedata( imageuri : Uri) : LiveData<String>{
        val imagepath = imagedatabase.getReference().child(Constant.Image)
        imagepath.putFile(imageuri)
            .addOnSuccessListener {

                it.storage.downloadUrl.addOnSuccessListener {
                    data.value  = it.toString()
                }

            }.addOnFailureListener {
                data.value = null
            }

        return data
    }

}