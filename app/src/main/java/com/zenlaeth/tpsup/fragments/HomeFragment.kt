package com.zenlaeth.tpsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.activity.MainApplication
import com.zenlaeth.tpsup.adapter.CoinAdapter
import kotlinx.coroutines.launch

class HomeFragment(private val context: HomeActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_home, container, false)
        val vRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        vRecyclerView.apply {
            lifecycleScope.launch {
                val coinDao = MainApplication.coinCollectorDatabase.getCoinDao()
                val coinsList = coinDao.getCoinList()

                vRecyclerView.adapter = CoinAdapter(context as HomeActivity, coinsList, R.layout.item_vertical)
            }
        }

        return view
    }
}