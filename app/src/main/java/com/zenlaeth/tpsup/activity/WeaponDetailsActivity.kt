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
import com.zenlaeth.tpsup.adapter.WeaponAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.WeaponBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeaponDetailsActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weapon_details)

        val weaponImageIv: ImageView = findViewById(R.id.image_item)
        val weaponNameTv: TextView = findViewById(R.id.detail_name)
        val weaponTypeTv: TextView = findViewById(R.id.detail_type)
        val weaponRankTv: TextView = findViewById(R.id.detail_rank)
        val weaponSkillsTv: TextView = findViewById(R.id.detail_skills)
        val weaponDefenseTv: TextView = findViewById(R.id.detail_defense)
        val weaponResistancesTv: TextView = findViewById(R.id.detail_resistances)
        val weaponCraftingTv: TextView = findViewById(R.id.detail_crafting)

        val weaponItemId = intent.getStringExtra(WeaponAdapter.ViewHolder.weaponIdKey)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getWeapon(weaponItemId)

        call.enqueue(object : Callback<WeaponBean> {
            override fun onResponse(call: Call<WeaponBean>, response: Response<WeaponBean>) {
                val response = response.body()

                // utiliser glide pour recuperer l'image à partir de son lien -> composant
                if(response?.assets != null) {
                    Glide.with(this@WeaponDetailsActivity).load(Uri.parse(response?.assets.image))
                        .into(weaponImageIv)
                    Picasso.get().load(response?.assets.image).into(weaponImageIv)
                }

                weaponNameTv.text = response?.name
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

            override fun onFailure(call: Call<WeaponBean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }
}