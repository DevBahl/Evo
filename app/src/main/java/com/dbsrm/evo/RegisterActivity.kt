package com.dbsrm.evo

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn.setOnClickListener {
            registerTheUser()
        }
        select_photo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)
        }
    }

    var selectPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null)  {
            selectPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectPhotoUri)

            photo_upload_register.setImageBitmap(bitmap)

            select_photo.alpha = 0f
            val bitmapDrawable = BitmapDrawable(bitmap)
            select_photo.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun registerTheUser(){
        val email = register_mail.text.toString()
        val password = register_password.text.toString()
        val username = register_name.text.toString()
        val photo = photo_upload_register
        if(username.isEmpty()||email.isEmpty()||password.isEmpty()||photo.getDrawable() == null){
            Toast.makeText(this,"Please fill all the Details", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                uploadImageToFirebase()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed To create User ${it.message}",Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImageToFirebase(){
        if(selectPhotoUri == null) return

        val fname = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/profileImages/$fname")

        ref.putFile(selectPhotoUri!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener{
                    saveUserOnFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed to Upload Image ${it.message}",Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveUserOnFirebaseDatabase(profileImageUrl: String){
        val uid = FirebaseAuth.getInstance().uid ?: return
        val ref = FirebaseDatabase.getInstance().getReference("/allUsers/$uid")

        val alluser = AllUser(uid,register_name.text.toString(),profileImageUrl)

        ref.setValue(alluser)
            .addOnSuccessListener {
                val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or (Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
    }
}
class AllUser(val uid:String, val name:String, val profileImageUrl: String)

