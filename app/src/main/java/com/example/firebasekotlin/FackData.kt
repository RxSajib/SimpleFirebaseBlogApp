package com.example.firebasekotlin

class FackData {

    companion object{

        fun facedata () : ArrayList<User>{

            val data = ArrayList<User>()

            data.add(
                User("Sajib", "sajibroy206@gmail.com", 11)
            )

            data.add(
                User("Rajib", "rajib@gmail.com", 25)
            )


            return data
        }

    }
}