package com.zenlaeth.tpsup.entity

data class Set(
    val uid: String,
    val idHead: Int,
    val idChest: Int,
    val idGloves: Int,
    val idWaist: Int,
    val idLegs: Int,
    val idWeapon: Int,
    val idMonster: Int,
    val idUser: String,
    val setLabel: String = ""
)