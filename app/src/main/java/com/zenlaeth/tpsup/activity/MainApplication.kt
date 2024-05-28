package com.zenlaeth.tpsup.activity

import android.app.Application
import androidx.room.Room
import com.zenlaeth.tpsup.database.CoinCollectorDatabase
import com.zenlaeth.tpsup.entity.Coin

class MainApplication : Application() {
    companion object {
        lateinit var coinCollectorDatabase: CoinCollectorDatabase
    }

    override fun onCreate() {
        super.onCreate()

//        deleteDatabase(CoinCollectorDatabase.NAME)

        coinCollectorDatabase = Room.databaseBuilder(
            applicationContext,
            CoinCollectorDatabase::class.java,
            CoinCollectorDatabase.NAME
        )
        .allowMainThreadQueries()
        .build()
    }
}