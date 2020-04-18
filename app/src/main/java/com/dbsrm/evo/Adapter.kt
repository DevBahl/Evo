package com.dbsrm.evo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage

class Adapter : RecyclerView.Adapter<Adapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val v: View
        v = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return MyviewHolder(v)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        //  holder.tv_name.setText(mdata.get(position).getText1());
        //      holder.tv_phone.setText(mdata.get(position).getText2());
        //    holder.img.setImageResource(mdata.get(position).getmImageresource());
        holder.bindView(position)
    }

    override fun getItemCount(): Int {
        return not2.title.size
    }

    inner class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tv_name: TextView
        private val tv_phone: TextView? = null
        private val img: ImageView
        fun bindView(position: Int) {
            tv_name.text = not2.title[position]
            img.setImageResource(not2.picturepath[position])
        }

        override fun onClick(view: View) {}

        init {
            tv_name = itemView.findViewById<View>(R.id.uploaded_description) as TextView
            //   tv_phone=(TextView) itemView.findViewById(R.id.txt2);
            img = itemView.findViewById<View>(R.id.uploaded_image) as ImageView
            itemView.setOnClickListener(this)
        }
    }
}