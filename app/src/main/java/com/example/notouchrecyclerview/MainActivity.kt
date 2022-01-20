package com.example.notouchrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: ArrayList<Intro> = arrayListOf()

        val infoA: ArrayList<Info> = arrayListOf()
        val warnA: ArrayList<Warning> = arrayListOf()
        val infoB: ArrayList<Info> = arrayListOf()
        val warnB: ArrayList<Warning> = arrayListOf()


        infoA.add(Info("A", "M", "15", "AABB", "0912345678"))
        infoB.add(Info("B", "F", "25", "CCDD", "0987654321"))
        warnA.add(Warning("此為可疑人物 請小心謹慎"))
        warnB.add(Warning("此為可疑人物 請小心謹慎"))

        list.add(Intro("陌生人","A","0912345678", infoA, warnA))
        list.add(Intro("陌生人","B","0987654321", infoB, warnB))


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_intro)
        val introAdapter = IntroAdapter(list)
        recyclerView.apply {
            setHasFixedSize(false)
            adapter = introAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}