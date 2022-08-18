package com.example.chatapplication5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, val userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
      //   TODO("Not yet implemented")
         val view:View=LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
         return UserViewHolder(view)
     }

     override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       //  TODO("Not yet implemented")
         val currentUser=userList[position]
         holder.textName.text=currentUser.name
         holder.itemView.setOnClickListener{
             val intent=Intent(context,ChatActivity::class.java)

             intent.putExtra("name",currentUser.name)
             intent.putExtra("uid", currentUser.uid)

             context.startActivity(intent)
         }
     }

     override fun getItemCount(): Int {
        // TODO("Not yet implemented")
         return userList.size
     }



     class UserViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
            val  textName=itemView.findViewById<TextView>(R.id.user_id)

     }
 }

private fun Intent.putExtra(s: String, currentUser: User) {

}
