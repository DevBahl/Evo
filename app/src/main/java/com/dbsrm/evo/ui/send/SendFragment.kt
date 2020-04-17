package com.dbsrm.evo.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dbsrm.evo.R

class SendFragment : Fragment() {
    private var sendViewModel: SendViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sendViewModel = ViewModelProviders.of(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView = root.findViewById<TextView>(R.id.text_send)
        //  sendViewModel.getText().observe(this, Observer { s -> textView.text = s })
        return root
    }
}