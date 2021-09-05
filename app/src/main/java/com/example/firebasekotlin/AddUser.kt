package com.example.firebasekotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUser : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var username : EditText
    private lateinit var userid : EditText
    private lateinit var savebutton : Button
    private lateinit var progressDialog: ProgressDialog
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)


        progressDialog = ProgressDialog(this)
        database = FirebaseDatabase.getInstance().reference.child(Constant.User)



        init_view()

    }

    fun init_view(){
        email = findViewById(R.id.EmailInput)
        username = findViewById(R.id.UserNameInput)
        userid = findViewById(R.id.IDInput)
        savebutton = findViewById(R.id.SaveButton)


        savebutton.setOnClickListener {
            var emailtext = email.text.toString().trim()
            var usernametext = username.text.toString().trim()
            var useridtext = userid.text.toString().trim()

            progressDialog.setMessage("Please wait")


            if(emailtext.isEmpty()){
                Toast.makeText(applicationContext, "Email require", Toast.LENGTH_LONG).show()
            }else if(usernametext.isEmpty()){
                Toast.makeText(applicationContext, "Username require", Toast.LENGTH_LONG).show()
            }else if(useridtext.isEmpty()){
                Toast.makeText(applicationContext, "UserID require", Toast.LENGTH_LONG).show()
            }

            else{
                progressDialog.show()

                val timestamp = System.currentTimeMillis()
                var map = HashMap<String, Any>()
                map.put(Constant.Username, usernametext)
                map.put(Constant.UserEmail, emailtext)
                map.put(Constant.UserID, useridtext)

                database.child(timestamp.toString())
                    .updateChildren(map)
                    .addOnCompleteListener {

                    }.addOnFailureListener {
                        Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                    }
            }
        }
    }
}