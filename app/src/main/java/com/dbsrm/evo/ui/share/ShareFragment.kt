package com.dbsrm.evo.ui.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dbsrm.evo.R

class ShareFragment : Fragment() {
    private var shareViewModel: ShareViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_share, container, false)
        val textView = root.findViewById<TextView>(R.id.text_share)
        //   shareViewModel.getText().observe(this, Observer { s -> textView.text = s })
        return root
    }
}