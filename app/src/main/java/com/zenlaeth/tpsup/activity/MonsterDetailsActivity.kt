package com.zenlaeth.tpsup.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
//        val monsterImageIv: ImageView = findViewById(R.id.image_item)
        val monsterNameTv: TextView = findViewById(R.id.detail_name)
        val monsterTypeTv: TextView = findViewById(R.id.detail_type)
        val monsterSpeciesTv: TextView = findViewById(R.id.detail_species)
        val monsterDescriptionTv: TextView = findViewById(R.id.detail_description)
        val monsterLocationsTv: TextView = findViewById(R.id.detail_locations)
        val monsterWeaknessesTv: TextView = findViewById(R.id.detail_weaknesses)
        val monsterResistancesTv: TextView = findViewById(R.id.detail_resistances)
        val monsterRewardsTv: TextView = findViewById(R.id.detail_rewards)
        val addBCart: Button = findViewById(R.id.addBCart)

        val monsterId = intent.getStringExtra(MonsterAdapter.ViewHolder.monsterIdKey)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getMonster(monsterId)

        call.enqueue(object : Callback<MonsterBean> {
            override fun onResponse(call: Call<MonsterBean>, response: Response<MonsterBean>) {
                val response = response.body()

                monsterNameTv.text = response?.name
                monsterTypeTv.text = response?.type?.capitalize()
                monsterSpeciesTv.text = response?.species?.capitalize()
                monsterDescriptionTv.text = response?.description?.capitalize()

                if(!response?.locations.isNullOrEmpty()) {
                    var listLocations = mutableListOf<String>()
                    response?.locations?.forEach(){
                        listLocations.add(it.name.capitalize())
                    }
                    monsterLocationsTv.text = listLocations.joinToString(separator=", ")
                }
                else {
                    monsterLocationsTv.text = "None"
                }

                if(!response?.weaknesses.isNullOrEmpty()) {
                    var listWeaknesses = mutableListOf<String>()
                    response?.weaknesses?.forEach(){
                        listWeaknesses.add(it.element.capitalize())
                    }
                    monsterWeaknessesTv.text = listWeaknesses.joinToString(separator=", ")
                }
                else {
                    monsterWeaknessesTv.text = "None"
                }

                if(!response?.resistances.isNullOrEmpty()) {
                    var listResistances = mutableListOf<String>()
                    response?.resistances?.forEach(){
                        listResistances.add(it.element.capitalize())
                    }
                    monsterResistancesTv.text = listResistances.joinToString(separator=", ")
                }
                else {
                    monsterResistancesTv.text = "None"
                }

                if(!response?.rewards.isNullOrEmpty()) {
                    var listRewards = mutableListOf<String>()
                    response?.rewards?.forEach(){
                        listRewards.add(it.item.name.capitalize())
                    }
                    monsterRewardsTv.text = listRewards.joinToString(separator=", ")
                }
                else {
                    monsterRewardsTv.text = "None"
                }
            }

            override fun onFailure(call: Call<MonsterBean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })

        addBCart.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, CreateSetActivity::class.java)
            intent.putExtra("MonsterId", monsterId)
            this.startActivity(intent);
        }
    }
}