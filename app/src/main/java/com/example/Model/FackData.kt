package com.example.Model

import com.example.Model.User

class FackData {

    companion object{

        fun facedata () : ArrayList<User>{

            val data = ArrayList<User>()

            data.add(
                User("Sajib", "sajibroy206@gmail.com", "11")
            )

            data.add(
                User("Rajib", "rajib@gmail.com", "11")
            )


            return data
        }

    }
}