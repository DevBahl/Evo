package com.dbsrm.evo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recyclerview.*


class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var v: View? = null
    private var mrecyclerview: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.recyclerview, container, false)
        mrecyclerview = v?.findViewById(R.id.recycle1)
        val adapter = Adapter()
        mrecyclerview?.setAdapter(adapter)
        mrecyclerview?.setLayoutManager(LinearLayoutManager(activity))
        return v

      //  recycle1.adapter = adapter
     //   adapter.add(storyWall())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        float_upload_btn.setOnClickListener {
            val uploadFragment = UploadFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.container,uploadFragment)?.commit()
        }
    }
 //   val adapter = GroupAdapter<GroupieViewHolder>()


   /* class storyWall(): Item<GroupieViewHolder>(){
        override fun getLayout(): Int {
            return R.layout.cardview
        }

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        }

    }*/
}

