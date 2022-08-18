package com.example.chatapplication5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

  private  lateinit var edtEmail:EditText
   private lateinit var edtPassword:EditText
  private  lateinit var btnLogin:Button
    private lateinit var btnSignUp:Button

    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()


        supportActionBar?.hide()

        edtEmail=findViewById(R.id.edtEmail_id)
        edtPassword=findViewById(R.id.edtPassword_id)
        btnLogin=findViewById(R.id.loginbtn_id)
        btnSignUp=findViewById(R.id.registerbtn_id)


        btnSignUp.setOnClickListener{
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {
            val email=edtEmail.text.toString()
            val password=edtPassword.text.toString()
            login(email,password);  }
    }
    private fun login(email:String, password:String){
        //login code


        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login,"Invalid email and password",Toast.LENGTH_LONG).show()
                }
            }


    }
}