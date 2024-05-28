package com.zenlaeth.tpsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.activity.MainApplication

class GraphFragment(private val context: HomeActivity) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                val view =  inflater?.inflate(R.layout.fragment_graph, container, false)
                var totalValue: TextView = view.findViewById(R.id.total_value)
                val coinDao = MainApplication.coinCollectorDatabase.getCoinDao()

                totalValue.text = coinDao.getTotalCoinsValue().toString()

                return view
        }
}