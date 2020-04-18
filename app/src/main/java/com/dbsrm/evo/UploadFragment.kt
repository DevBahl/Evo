package com.dbsrm.evo

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_upload.*
import java.util.*
import kotlin.concurrent.fixedRateTimer


/**
 * A simple [Fragment] subclass.
 */
class UploadFragment : Fragment() {

    var v : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.fragment_upload, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newPostUpload_progressBar.visibility = View.INVISIBLE
        uploadnew_photo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)
        }
        btn_upload_newPost.setOnClickListener {
            uploadImageToFirebase()
        }
    }

    var selectPhotoUri1: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var resolver = activity!!.contentResolver

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null)  {
            selectPhotoUri1 = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(resolver,selectPhotoUri1)

            show_new_uploaded_image.setImageBitmap(bitmap)

            uploadnew_photo.alpha = 0f
            val bitmapDrawable = BitmapDrawable(bitmap)
            uploadnew_photo.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun uploadImageToFirebase(){
        val Writedescription = write_description.text.toString()
        newPostUpload_progressBar.visibility = View.VISIBLE
        if(selectPhotoUri1 == null) {
            newPostUpload_progressBar.visibility = View.INVISIBLE
            return
        }else if(Writedescription.isEmpty()){
            newPostUpload_progressBar.visibility = View.INVISIBLE
            return
        }

        val uploadedImageName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/WallImages/$uploadedImageName")

        ref.putFile(selectPhotoUri1!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener{
                    savePostToFirebase(it.toString())
                }
            }
            .addOnFailureListener {
                newPostUpload_progressBar.visibility = View.INVISIBLE
                Toast.makeText(activity,"Failed to Upload Image ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun savePostToFirebase(UploadedPostImage: String){
        val ref = FirebaseDatabase.getInstance().getReference("/Posts").push()
        val post = Post(write_description.text.toString(),UploadedPostImage)
        ref.setValue(post)
            .addOnSuccessListener {
                newPostUpload_progressBar.visibility = View.INVISIBLE
                 val home = Home()
                 fragmentManager?.beginTransaction()?.replace(R.id.container,home)?.commit()
                Toast.makeText(activity,"Post Uploaded Successfully",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                newPostUpload_progressBar.visibility = View.INVISIBLE
                Toast.makeText(activity,"Failed to Upload Image ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
class Post(val description: String, val UploadedPostImage: String)
{
    constructor(): this("","")
}
