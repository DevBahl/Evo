package com.dbsrm.evo.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dbsrm.evo.R

class ToolsFragment : Fragment() {
    private var toolsViewModel: ToolsViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        toolsViewModel = ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        val textView = root.findViewById<TextView>(R.id.text_tools)
        //   toolsViewModel.getText().observe(this, Observer { s -> textView.text = s })
        return root
    }
}