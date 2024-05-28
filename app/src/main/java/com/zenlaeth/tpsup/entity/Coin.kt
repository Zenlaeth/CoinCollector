package com.zenlaeth.tpsup.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
data class Coin (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "rarity") val rarity: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "value") val value: Int,
) {
    // Secondary constructor to ignore id
    constructor(year: Int, rarity: String, quantity: Int, value: Int) : this(
        id = 0,
        year = year,
        rarity = rarity,
        quantity = quantity,
        value = value
    )
}
