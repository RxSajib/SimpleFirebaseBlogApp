package com.example.UI

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.Model.Constant
import com.example.firebasekotlin.R
import com.example.firebasekotlin.ViewModel.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.makeramen.roundedimageview.RoundedImageView

private const val ImageCode = 200
class AddBlog : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var username : EditText
    private lateinit var userid : EditText
    private lateinit var savebutton : RelativeLayout
    private lateinit var progressDialog: ProgressDialog
    private lateinit var database : DatabaseReference
    private lateinit var viewModel: ViewModel
    private lateinit var image : RoundedImageView
    private lateinit var imagestores : FirebaseStorage
    private  var ImageUri : String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        progressDialog = ProgressDialog(this)
        database = FirebaseDatabase.getInstance().reference.child(Constant.User)



        init_view()

    }

    fun init_view(){
        imagestores = FirebaseStorage.getInstance()
        image = findViewById(R.id.PickImage)
        email = findViewById(R.id.EmailInput)
        username = findViewById(R.id.UserNameInput)
        userid = findViewById(R.id.IDInput)
        savebutton = findViewById(R.id.SaveButton)


        savebutton.setOnClickListener {
            var emailtext = email.text.toString().trim()
            var usernametext = username.text.toString().trim()
            var details = userid.text.toString().trim()

            progressDialog.setMessage("Please wait")


            if(emailtext.isEmpty()){
                Toast.makeText(applicationContext, "Email require", Toast.LENGTH_LONG).show()
            }else if(usernametext.isEmpty()){
                Toast.makeText(applicationContext, "Username require", Toast.LENGTH_LONG).show()
            }else if(details.isEmpty()){
                Toast.makeText(applicationContext, "UserID require", Toast.LENGTH_LONG).show()
            }

            else{
                progressDialog.show()

                val timestamp = System.currentTimeMillis()
                var map = HashMap<String, Any>()
                map.put(Constant.Username, usernametext)
                map.put(Constant.UserEmail, emailtext)
                map.put(Constant.Details, details)
                map.put(Constant.Timestamp, timestamp)

                if(ImageUri != null){
                    map.put(Constant.Image, ImageUri!!)
                }



                database.child(timestamp.toString())
                    .updateChildren(map)
                    .addOnCompleteListener {
                        progressDialog.dismiss()
                        email.setText(null)
                        username.setText(null)
                        userid.setText(null)

                        Toast.makeText(applicationContext, "Data is added", Toast.LENGTH_LONG).show()

                    }.addOnFailureListener {
                        Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                    }
            }
        }

        image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent, ImageCode)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ImageCode || resultCode == RESULT_OK){
            progressDialog.show()
            var uri = data?.data as Uri

            image.setImageURI(uri)
            viewModel.uploadimage(uri).observe(this, Observer {
                if(it != null){
                    progressDialog.dismiss()
                    ImageUri = it
                    Toast.makeText(applicationContext, "image upload success", Toast.LENGTH_LONG).show()

                }else{
                    progressDialog.dismiss()
                }
            })
        }
    }
}

