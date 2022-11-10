package com.zhaoshuo.recode.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.zhaoshuo.recode.R
import com.zhaoshuo.recode.logic.model.RecordResponse



 class RecordAdapter(private val fragment: RecodesFragment) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

     var recordList: MutableList<RecordResponse.Item> = mutableListOf()

     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val info: TextView = view.findViewById(R.id.info)
        val status: TextView = view.findViewById(R.id.status)
        val headImage:ImageView=view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
        val hospital: TextView = view.findViewById(R.id.hospital)
        val date: TextView = view.findViewById(R.id.date)
        val leftBtn: TextView = view.findViewById(R.id.leftBtn)
        val border:View=view.findViewById(R.id.view3)
        val rightBtn: TextView = view.findViewById(R.id.rightBtn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recode_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val record = recordList[position]
        }
        return holder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = recordList[position]
        holder.status.text=record.status
        holder.name.text= record.practitioner?.name ?: ""
        holder.hospital.text=record.practitioner?.hospitalName?:""
        holder.date.text=record.start

    }

    override fun getItemCount() = recordList.size

}