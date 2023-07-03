package com.zenlaeth.tpsup.entity

data class Project(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val location: String = "",
    val price: Int = 0,
    val image: String = "https://cdn.pixabay.com/photo/2016/11/29/09/32/concept-1868728_960_720.jpg",
)