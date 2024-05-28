package com.zenlaeth.tpsup.fragments

import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.activity.MainApplication
import com.zenlaeth.tpsup.entity.Coin
import java.util.*

class AddCoinFragment(private val context: HomeActivity) : Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_coin, container, false)
        val coinDao = MainApplication.coinCollectorDatabase.getCoinDao()

        view.findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val year = view.findViewById<EditText>(R.id.year).text.toString().toInt()
            val rarity = view.findViewById<EditText>(R.id.rarity).text.toString()
            val quantity = view.findViewById<EditText>(R.id.quantity).text.toString().toInt()
            val value = view.findViewById<EditText>(R.id.value).text.toString().toInt()

            val coin = Coin(year, rarity, quantity, value)
            coinDao.insertCoin(coin)
        }

        return view
    }
}