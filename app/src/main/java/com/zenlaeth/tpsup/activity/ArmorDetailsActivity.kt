package com.zenlaeth.tpsup.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.adapter.ArmorAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.ArmorBean
import com.zenlaeth.tpsup.bean.MonsterBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmorDetailsActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.armor_details)

        val armorImageIv: ImageView = findViewById(R.id.image_item)
        val armorNameTv: TextView = findViewById(R.id.detail_name)
        val armorTypeTv: TextView = findViewById(R.id.detail_type)
        val armorRankTv: TextView = findViewById(R.id.detail_rank)
        val armorSkillsTv: TextView = findViewById(R.id.detail_skills)
        val armorDefenseTv: TextView = findViewById(R.id.detail_defense)
        val armorResistancesTv: TextView = findViewById(R.id.detail_resistances)
        val armorCraftingTv: TextView = findViewById(R.id.detail_crafting)

        val armorItemId = intent.getStringExtra(ArmorAdapter.ViewHolder.armorIdKey)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getArmor(armorItemId)

        call.enqueue(object : Callback<ArmorBean> {
            override fun onResponse(call: Call<ArmorBean>, response: Response<ArmorBean>) {
                val response = response.body()

                // utiliser glide pour recuperer l'image à partir de son lien -> composant
                if(response?.assets != null) {
                    Glide.with(this@ArmorDetailsActivity).load(Uri.parse(response?.assets.imageMale))
                        .into(armorImageIv)
                    Picasso.get().load(response?.assets.imageMale).into(armorImageIv)
                }

                armorNameTv.text = response?.name
//                monsterTypeTv.text = response?.type?.capitalize()
//                monsterSpeciesTv.text = response?.species?.capitalize()
//                monsterDescriptionTv.text = response?.description?.capitalize()
//
//                if(!response?.locations.isNullOrEmpty()) {
//                    var listLocations = mutableListOf<String>()
//                    response?.locations?.forEach(){
//                        listLocations.add(it.name.capitalize())
//                    }
//                    monsterLocationsTv.text = listLocations.joinToString(separator=", ")
//                }
//                else {
//                    monsterLocationsTv.text = "None"
//                }
//
//                if(!response?.weaknesses.isNullOrEmpty()) {
//                    var listWeaknesses = mutableListOf<String>()
//                    response?.weaknesses?.forEach(){
//                        listWeaknesses.add(it.element.capitalize())
//                    }
//                    monsterWeaknessesTv.text = listWeaknesses.joinToString(separator=", ")
//                }
//                else {
//                    monsterWeaknessesTv.text = "None"
//                }
//
//                if(!response?.resistances.isNullOrEmpty()) {
//                    var listResistances = mutableListOf<String>()
//                    response?.resistances?.forEach(){
//                        listResistances.add(it.element.capitalize())
//                    }
//                    monsterResistancesTv.text = listResistances.joinToString(separator=", ")
//                }
//                else {
//                    monsterResistancesTv.text = "None"
//                }
//
//                if(!response?.rewards.isNullOrEmpty()) {
//                    var listRewards = mutableListOf<String>()
//                    response?.rewards?.forEach(){
//                        listRewards.add(it.item.name.capitalize())
//                    }
//                    monsterRewardsTv.text = listRewards.joinToString(separator=", ")
//                }
//                else {
//                    monsterRewardsTv.text = "None"
//                }
            }

            override fun onFailure(call: Call<ArmorBean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }
}