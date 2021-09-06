package com.example.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.Model.Constant
import com.example.Model.User
import com.example.firebasekotlin.Adapter
import com.example.firebasekotlin.R
import com.example.firebasekotlin.ViewModel.ViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var button: FloatingActionButton
    private lateinit var recylerview : RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var database : DatabaseReference
    private lateinit var mydata : ArrayList<User>
    private lateinit var toolbar: MaterialToolbar
    private lateinit var viewmodel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.MyToolbar)
        setSupportActionBar(toolbar)

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

        viewmodel = ViewModelProvider(this).get(ViewModel::class.java)

        viewmodel.getuserdata().observe(this, Observer {
            if(it !=null){
                adapter.adduser(it as ArrayList<User>)

                recylerview.adapter = adapter
                adapter.notifyDataSetChanged()
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.logoutmeu, menu)
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.LogOut ->{

                viewmodel.logoutaccount().observe(this, Observer {
                    val intent  = Intent(this, Login::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(applicationContext, "Logout success", Toast.LENGTH_LONG).show()
                })
            }
        }
        return true
    }

}