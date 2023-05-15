package com.zenlaeth.tpsup.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.adapter.MonsterAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.MonsterBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment(private val context: HomeActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_home, container, false)

        // charger api
        val hRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        val vRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getMonsters()

        call.enqueue(object : Callback<MutableList<MonsterBean>> {
            override fun onResponse(call: Call<MutableList<MonsterBean>>, response: Response<MutableList<MonsterBean>>) {
                if(response.isSuccessful){
                    val sortBody = response.body()?.sortedBy { it.name }
                    hRecyclerView.apply {
                        hRecyclerView.adapter = MonsterAdapter(context as HomeActivity, sortBody!!, R.layout.item_horizontal)
                    }
                    vRecyclerView.apply {
                        vRecyclerView.adapter = MonsterAdapter(context as HomeActivity, sortBody!!, R.layout.item_vertical)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<MonsterBean>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })

        return view
    }
}