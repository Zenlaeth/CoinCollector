package com.zenlaeth.tpsup.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.adapter.MonsterAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.MonsterBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonsterDetailsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_details)

        /*val image = intent.getStringExtra(ArticleAdapter.ViewHolder.articleImageKey)*/
        val monsterImageIv: ImageView = findViewById(R.id.article_image_item)
        val monsterNameTv: TextView = findViewById(R.id.article_detail_name)
        val monsterDescriptionTv: TextView = findViewById(R.id.article_detail_description)

        val articleItemId = intent.getStringExtra(MonsterAdapter.ViewHolder.monsterIdKey)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getMonster(articleItemId)

        call.enqueue(object : Callback<MonsterBean> {
            override fun onResponse(call: Call<MonsterBean>, response: Response<MonsterBean>) {
                val response = response.body()

                monsterNameTv.text = response?.name

                monsterDescriptionTv.text = response?.description
            }

            override fun onFailure(call: Call<MonsterBean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }
}