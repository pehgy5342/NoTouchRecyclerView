package com.example.notouchrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WarningAdapter(private val waringList: ArrayList<Warning>) :
    RecyclerView.Adapter<WarningAdapter.WaringViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_warning,parent,false)
        return WaringViewHolder(view)
    }

    override fun onBindViewHolder(holder: WaringViewHolder, position: Int) {
        holder.bind(waringList[position])
    }

    override fun getItemCount(): Int = waringList.size


    inner class WaringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(warn: Warning) {
            val warning = itemView.findViewById<TextView>(R.id.txt_warning)
            warning.text = warn.waring
        }
    }
}