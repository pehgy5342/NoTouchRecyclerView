package com.example.notouchrecyclerview

data class Intro(
    val title: String,
    val name:String,
    val phone:String,
    val infoList: ArrayList<Info>,
    val waringList: ArrayList<Warning>
)
