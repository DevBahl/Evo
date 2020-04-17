package com.dbsrm.evo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home  //fgfhgh
    : Fragment() {
    var v: View? = null
    private var mrecyclerview: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.recyclerview, container, false)
        mrecyclerview = v!!.findViewById(R.id.recycle1)
        val adapter = Adapter()
        mrecyclerview!!.setAdapter(adapter)
        mrecyclerview!!.setLayoutManager(LinearLayoutManager(activity))
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}