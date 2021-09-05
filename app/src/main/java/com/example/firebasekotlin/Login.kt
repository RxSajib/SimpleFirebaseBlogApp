package com.example.firebasekotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var emailinput : EditText
    private lateinit var passwordinput : EditText
    private lateinit var loginbutton : Button

    private lateinit var auth : FirebaseAuth
    private lateinit var registertex : TextView
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        progressDialog = ProgressDialog(this)
        auth = FirebaseAuth.getInstance()


        init_view()
    }

    fun init_view(){

        emailinput = findViewById(R.id.EmailInput)
        passwordinput = findViewById(R.id.PasswordInput)
        loginbutton = findViewById(R.id.LoginButton)

        loginbutton.setOnClickListener {
            val emailtext = emailinput.text.toString().trim()
            val passwordtext = passwordinput.text.toString().trim()

            if(emailtext.isEmpty()){
                Toast.makeText(applicationContext, "Email require", Toast.LENGTH_LONG).show()
            }else if(passwordtext.isEmpty()){
                Toast.makeText(applicationContext, "Password require", Toast.LENGTH_LONG).show()
            }else{
                progressDialog.setMessage("Please wait")
                progressDialog.show()

                auth.signInWithEmailAndPassword(emailtext, passwordtext)
                    .addOnCompleteListener {
                        goto_homepage()
                        progressDialog.dismiss()
                    }.addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
            }
        }

        registertex = findViewById(R.id.Register)
        registertex.setOnClickListener {
            val intent = Intent(this, Rehistation::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    fun goto_homepage(){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        if(user != null){
            goto_homepage()
        }
    }
}