package com.example.UI

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.firebasekotlin.R
import com.google.firebase.auth.FirebaseAuth

class Rehistation : AppCompatActivity() {

    private lateinit var logintext : TextView
    private lateinit var auth : FirebaseAuth
    private lateinit var email : EditText
    private lateinit var Password : EditText
    private lateinit var RegisterButton : Button
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rehistation)


        init_view()
    }

    fun init_view(){
        progressDialog = ProgressDialog(this)
        email = findViewById(R.id.EmailInput)
        Password = findViewById(R.id.PasswordInput)
        RegisterButton = findViewById(R.id.RegisterButon)

        auth = FirebaseAuth.getInstance();


        RegisterButton.setOnClickListener {
            val emailtext = email.text.toString().trim()
            val passwordtext = Password.text.toString().trim()

            if(emailtext.isEmpty()){
                Toast.makeText(applicationContext, "Email require", Toast.LENGTH_LONG).show()
            }else if(passwordtext.isEmpty()){
                Toast.makeText(applicationContext, "Password require", Toast.LENGTH_LONG).show()
            }
            else{
                progressDialog.setMessage("Please wait")
                progressDialog.show()
                auth.createUserWithEmailAndPassword(emailtext, passwordtext)
                    .addOnSuccessListener {
                        goto_homepage()
                        progressDialog.dismiss()
                    }.addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
            }
        }


        logintext = findViewById(R.id.Login)
        logintext.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }

    fun goto_homepage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}