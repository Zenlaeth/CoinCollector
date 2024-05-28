package com.zenlaeth.tpsup.dao;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.zenlaeth.tpsup.entity.Coin;

@Dao
interface CoinDao {
    @Query("Select * from coin")
    fun getCoinList(): LiveData<List<Coin>>
    @Query("SELECT * FROM coin WHERE id=:id")
    fun getCoin(id: String): Coin
    @Query("SELECT SUM(value) FROM coin")
    fun getTotalCoinsValue(): Double
    @Insert
    fun insertCoin(coin: Coin);
    @Update
    fun updateCoin(coin: Coin);
    @Delete
    fun deleteCoin(coin: Coin);
}
