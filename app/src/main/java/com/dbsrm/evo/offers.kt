package com.dbsrm.evo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_offers.*

class offers : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        currentUserFetchDetails()
        return inflater.inflate(R.layout.fragment_offers, container, false)

    }

    private fun currentUserFetchDetails(){
        var currentUser: AllUser? = null
        val uid = FirebaseAuth.getInstance().uid
        val userEmail = FirebaseAuth.getInstance().currentUser?.email
        val ref = FirebaseDatabase.getInstance().getReference("/allUsers/$uid")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                profile_progressBar.visibility = View.VISIBLE
                currentUser = p0.getValue(AllUser::class.java) ?: return
                Log.d("CheckUser","${currentUser?.name}")
                Picasso.get().load(currentUser?.profileImageUrl).into(profile_photo_show)
                show_name.text = currentUser?.name.toString()
                show_email.text = userEmail.toString()
                profile_progressBar.visibility = View.INVISIBLE
            }
        })
    }
}