package com.example.chatapplication5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
  private  lateinit var edtName: EditText
  private  lateinit var edtEmail: EditText
   private lateinit var edtPassword: EditText
  private  lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

       supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()


        edtName=findViewById(R.id.edtName_id)
        edtEmail=findViewById(R.id.Sign_up_Email_id)
        edtPassword=findViewById(R.id.Sign_up_Password_id)
        btnSignUp=findViewById(R.id.Sign_UP_btn_id)

        btnSignUp.setOnClickListener{
            val name:String=edtName.text.toString()
            val email:String=edtEmail.text.toString()
            val password:String=edtPassword.text.toString()
            signUp(name,email, password)
        }


    }

    private fun signUp(name:String,email:String, password:String){

        //logic for signUp

          mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Some Error occurred", Toast.LENGTH_LONG).show()
                }
            }


    }


    private fun addUserToDatabase(name: String,email: String,uid:String){

        mDbRef=FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }
}