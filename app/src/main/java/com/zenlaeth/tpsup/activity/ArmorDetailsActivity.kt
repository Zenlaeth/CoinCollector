package com.zenlaeth.tpsup.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.adapter.ArmorAdapter
import com.zenlaeth.tpsup.adapter.MonsterAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator

class ArmorDetailsActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.armor_details)

        /*val image = intent.getStringExtra(ArticleAdapter.ViewHolder.articleImageKey)*/
//        val armorImageIv: ImageView = findViewById(R.id.image_item)
        val armorNameTv: TextView = findViewById(R.id.detail_name)
        val armorTypeTv: TextView = findViewById(R.id.detail_type)
        val armorSpeciesTv: TextView = findViewById(R.id.detail_species)
        val armorDescriptionTv: TextView = findViewById(R.id.detail_description)
        val armorLocationsTv: TextView = findViewById(R.id.detail_locations)
        val armorWeaknessesTv: TextView = findViewById(R.id.detail_defense)
        val armorResistancesTv: TextView = findViewById(R.id.detail_resistances)
        val armorRewardsTv: TextView = findViewById(R.id.detail_rewards)

        val armorItemId = intent.getStringExtra(ArmorAdapter.ViewHolder.armorIdKey)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getMonster(armorItemId)
    }
}