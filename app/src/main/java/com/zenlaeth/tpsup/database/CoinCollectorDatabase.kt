package com.zenlaeth.tpsup.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zenlaeth.tpsup.dao.CoinDao;
import com.zenlaeth.tpsup.entity.Coin;

@Database(entities = [Coin::class], version = 1)
abstract class CoinCollectorDatabase : RoomDatabase() {

    companion object {
        const val NAME = "Coin_Collector_DB"
    }

    abstract fun getCoinDao() : CoinDao
}
