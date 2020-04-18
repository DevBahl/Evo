package com.dbsrm.evo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_progressBar.visibility = View.INVISIBLE
        login_btn.setOnClickListener {
            loginUser()
        }
        donthaveacc_btn.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or (Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun loginUser(){
        val email = mail_login.text.toString()
        val password = password_login.text.toString()


        if (email.isEmpty() && password.isEmpty()){
            Toast.makeText(this,"Please Fill the Email And Password",Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                login_progressBar.visibility = View.VISIBLE
                val r = Runnable {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                Handler().postDelayed(r,2000)
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed to Login ${it.message}",Toast.LENGTH_SHORT).show()
            }
    }
}