package com.zenlaeth.tpsup.activity

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.adapter.ArmorAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.ArmorBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmorActivity  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_armor)

        // charger api
        val vRecyclerView: RecyclerView = findViewById(R.id.vertical_recycler_view)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getArmors()

        call.enqueue(object : Callback<MutableList<ArmorBean>> {
            override fun onResponse(
                call: Call<MutableList<ArmorBean>>,
                response: Response<MutableList<ArmorBean>>
            ) {
                if (response.isSuccessful) {
                    vRecyclerView.apply {
                        progressBar.isVisible = true

                        val body = response.body()?.filter { it.type == intent.getStringExtra("Type") }
                        val sortBody = body?.sortedBy { it.name }

                        vRecyclerView.adapter =
                            ArmorAdapter(context as ArmorActivity, sortBody!!, R.layout.item_vertical)
                        progressBar.isVisible = false
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<ArmorBean>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }
}