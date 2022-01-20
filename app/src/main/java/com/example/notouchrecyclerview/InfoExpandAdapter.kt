package com.example.notouchrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class InfoExpandAdapter : RecyclerView.Adapter<AllExpandViewHolder>() {
    var intro: MutableList<Any> = arrayListOf()
        set(value) {
            field = value
            differ.submitList(value)
        }
    private val differ = AsyncListDiffer(this, InfoDiffCallback)

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is Info -> {
                R.layout.item_info
            }
            is Warning -> {
                R.layout.item_warning
            }
            else -> throw IllegalStateException("Unknown view type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllExpandViewHolder {
        return when (viewType) {
            R.layout.item_info -> {
                AllExpandViewHolder.InfoExpandViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
                )
            }
            R.layout.item_warning -> {
                AllExpandViewHolder.WaringViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_warning, parent, false)
                )
            }
            else -> throw IllegalStateException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: AllExpandViewHolder, position: Int) {
        when (holder) {
            is AllExpandViewHolder.InfoExpandViewHolder -> {
                holder.bind(differ.currentList[position] as Info)
            }
            is AllExpandViewHolder.WaringViewHolder -> {
                holder.bind(differ.currentList[position] as Warning)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

}

sealed class AllExpandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class InfoExpandViewHolder(itemView: View) : AllExpandViewHolder(itemView) {
        fun bind(info: Info) {
            val name = itemView.findViewById<TextView>(R.id.txt_name)
            val sex = itemView.findViewById<TextView>(R.id.txt_sex)
            val age = itemView.findViewById<TextView>(R.id.txt_age)
            val address = itemView.findViewById<TextView>(R.id.txt_address)
            val phone = itemView.findViewById<TextView>(R.id.txt_phone)

            name.text = "姓名：" + info.name
            sex.text = "性別："+info.sex
            age.text = "年齡："+info.age
            address.text = "地址："+info.address
            phone.text = "電話："+info.phone
        }
    }

    class WaringViewHolder(itemView: View) : AllExpandViewHolder(itemView) {
        fun bind(warn: Warning) {
            val warning = itemView.findViewById<TextView>(R.id.txt_warning)
            warning.text = warn.waring
        }
    }
}

object InfoDiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is Info && newItem is Info -> oldItem.hashCode() == newItem.hashCode()
            oldItem is Warning && newItem is Warning -> oldItem.waring == newItem.waring
            else -> false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is Info && newItem is Info -> oldItem == newItem
            oldItem is Warning && newItem is Warning -> oldItem == newItem
            else -> true
        }
    }
}
