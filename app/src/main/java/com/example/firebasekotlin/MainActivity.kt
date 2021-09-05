package com.example.firebasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var button: FloatingActionButton
    private lateinit var recylerview : RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recylerview = findViewById(R.id.RecyclerView)
        adapter = Adapter()
        adapter.adduser(FackData.facedata())

        button = findViewById(R.id.AddButtonID)
        button.setOnClickListener {
            val intent = Intent(this, AddUser::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }



        recylerview.setHasFixedSize(true)
        recylerview.adapter = adapter


    }


}