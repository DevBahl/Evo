package com.dbsrm.evo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_offers.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    var recycle1: RecyclerView? = null
    var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView!!.setOnNavigationItemSelectedListener(this)
        bottomNavigationView!!.setSelectedItemId(R.id.navigation_home)

        /*FloatingActionButton fab = findViewById(R.id.fab);
        recycle1 = findViewById(R.id.recycle1);
        loadImagesDynamically();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddImageActivity.class));
            }
        });*/

        signout_btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or (Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        checkCurrentUser()
    }



    var homeFragment = Home()
    var calenderFragment = Calender()
    var attendanceFragment = Attendance()
    var profileFragment = Profile()
    var offersFragment = offers()
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.navigation_calender -> {
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, attendanceFragment).commit()
                return true
            }
            R.id.navigation_attendance -> {
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, calenderFragment).commit()
                return true
            }
            R.id.navigation_profile -> {
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, profileFragment).commit()
                return true
            }
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, homeFragment).commit()
                return true
            }
            R.id.navigation_offers -> {
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, offersFragment).commit()
                return true
            }
        }
        return false
    } /* private void loadImagesDynamically() {
        final ArrayList<ImageData> images = new ArrayList<>();
        CollectionReference db = FirebaseFirestore.getInstance().collection("images");
        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots != null) {
                    images.clear();
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        ImageData imageData = snapshot.toObject(ImageData.class);
                        images.add(imageData);
                    }
                    recycle1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycle1.setAdapter(new ImageAdapter(images));
                }

            }
        });
    }*/

    companion object {
        private const val CATEGORY_SAMPLE = "com.prolificinteractive.materialcalendarview.sample.SAMPLE"
    }

    private fun checkCurrentUser(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid == null){
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
