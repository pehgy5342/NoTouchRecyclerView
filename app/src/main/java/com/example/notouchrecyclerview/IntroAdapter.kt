package com.example.notouchrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.expansionpanel.ExpansionLayout
import net.cachapa.expandablelayout.ExpandableLayout

class IntroAdapter(var introList: ArrayList<Intro>) :
    RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)
        return IntroViewHolder(view)

    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introList[position])
    }

    override fun getItemCount(): Int = introList.size

    inner class IntroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(intro: Intro) {
            val title = itemView.findViewById<TextView>(R.id.txt_title)
            val rv_info = itemView.findViewById<RecyclerView>(R.id.recyclerView_info)
            val rv_warning = itemView.findViewById<RecyclerView>(R.id.recyclerView_waring)
            val expand_info = itemView.findViewById<ExpansionLayout>(R.id.expand_info)
            val expand_intro = itemView.findViewById<ExpandableLayout>(R.id.expand_intro)
            val name = itemView.findViewById<TextView>(R.id.txt_name)
            val phone = itemView.findViewById<TextView>(R.id.txt_phone)

            expand_intro.expand(false)
            expand_info.collapse(false)

            title.text = intro.title
            name.text = "姓名："+intro.name
            phone.text = "電話："+intro.phone
            expand_info.invalidate()

            if (intro.waringList != null) {
                rv_warning.visibility = View.VISIBLE
                val warningAdapter = WarningAdapter(intro.waringList)
                rv_warning.apply {
                    setHasFixedSize(true)
                    adapter = warningAdapter
                    layoutManager = LinearLayoutManager(itemView.context)
                }
            } else {
                rv_warning.visibility = View.GONE
            }

            val infoAdapter = InfoExpandAdapter()
            infoAdapter.intro = intro.infoList as MutableList<Any>
            rv_info.apply {
                setHasFixedSize(true)
                adapter = infoAdapter
                layoutManager = LinearLayoutManager(itemView.context)
            }

            itemView.setOnClickListener {
                expand_info.toggle(true)
                expand_intro.toggle(true)
            }

        }
    }
}