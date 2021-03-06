package com.example.firebasekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Model.User
import com.squareup.picasso.Picasso

class Adapter : RecyclerView.Adapter<UserHolder>() {

      var userlis : List<User> = ArrayList()

     fun adduser(mylis : ArrayList<User>){
         userlis  = mylis
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_iteam, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.image.visibility = View.GONE

        holder.email.setText(userlis.get(position).UserEmail)
        holder.name.setText(userlis.get(position).UserName)
        holder.id.setText(userlis.get(position).Details.toString())


        if(userlis.get(position).Image != null){
            holder.image.visibility = View.VISIBLE
            Picasso.get().load(userlis.get(position).Image).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        if(userlis == null){
            return 0
        }else{
            return userlis.size
        }
    }
}


class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name  = itemView.findViewById<TextView>(R.id.UserName)
    var email = itemView.findViewById<TextView>(R.id.UserEmail)
    var id = itemView.findViewById<TextView>(R.id.UserID)
    var image = itemView.findViewById<ImageView>(R.id.Image)
}