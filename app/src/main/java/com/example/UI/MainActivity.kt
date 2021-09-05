package com.example.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.Model.Constant
import com.example.Model.User
import com.example.firebasekotlin.Adapter
import com.example.firebasekotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var button: FloatingActionButton
    private lateinit var recylerview : RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var database : DatabaseReference
    private lateinit var mydata : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mydata = ArrayList()

        database = FirebaseDatabase.getInstance().reference.child(Constant.User)
        recylerview = findViewById(R.id.RecyclerView)
        adapter = Adapter()




        button = findViewById(R.id.AddButtonID)
        button.setOnClickListener {
            val intent = Intent(this, AddBlog::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }



        recylerview.setHasFixedSize(true)


        getdata_toserver()

    }

    fun getdata_toserver(){


        database.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                mydata.clear()
                if(snapshot.exists()){


                    for(data in snapshot.children){
                       var serverdata = data.getValue(User::class.java)
                        mydata.add(serverdata!!)
                        Log.d("Size", mydata.size.toString())

                        adapter.adduser(mydata)
                    }

                    recylerview.adapter = adapter
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {



            }

        })
    }


}