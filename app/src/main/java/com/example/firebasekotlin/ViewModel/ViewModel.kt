package com.example.firebasekotlin.ViewModel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.Model.User
import com.example.firebasekotlin.Network.ImagePOST
import com.example.firebasekotlin.Network.LogoutPOST
import com.example.firebasekotlin.Network.UserGET
import com.example.firebasekotlin.Network.UserPOST

class ViewModel(application: Application) : AndroidViewModel(application) {


    var postdata = UserPOST(application)
    var imagepost = ImagePOST(application)
    var logOut = LogoutPOST(application)
    var getdata = UserGET(application)

    fun setdata(name: String, email: String, id : String) : LiveData<Boolean>{
       return postdata.uploaddata(name, email, id)
    }

    fun uploadimage(imageuri : Uri) : LiveData<String>{
        return imagepost.uploadimagedata(imageuri)
    }

    fun logoutaccount() : LiveData<Boolean>{
        return logOut.logoutAccount()
    }

    fun getuserdata() : LiveData<List<User>>{
        return getdata.getpost()
    }

}