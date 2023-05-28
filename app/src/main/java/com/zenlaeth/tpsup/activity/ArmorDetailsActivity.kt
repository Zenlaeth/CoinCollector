package com.zenlaeth.tpsup.activity

import android.net.Uri
import android.os.Bundle
import android.text.Html
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
import com.zenlaeth.tpsup.bean.Defense
import com.zenlaeth.tpsup.bean.MonsterBean
import com.zenlaeth.tpsup.databinding.ArmorDetailsBinding
import com.zenlaeth.tpsup.databinding.CreateSetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmorDetailsActivity: AppCompatActivity(){
    private lateinit var binding: ArmorDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArmorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val armorImageIv: ImageView = findViewById(R.id.image_item)
//        val armorNameTv: TextView = findViewById(R.id.detail_name)
//        val armorTypeTv: TextView = findViewById(R.id.detail_type)
//        val armorRankTv: TextView = findViewById(R.id.detail_rank)
//        val armorSkillsTv: TextView = findViewById(R.id.detail_skills)
//        val armorDefenseTv: TextView = findViewById(R.id.detail_defense)
//        val armorResistancesTv: TextView = findViewById(R.id.detail_resistances)
//        val armorCraftingTv: TextView = findViewById(R.id.detail_crafting)

        val armorItemId = intent.getStringExtra(ArmorAdapter.ViewHolder.armorIdKey)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getArmor(armorItemId)

        call.enqueue(object : Callback<ArmorBean> {
            override fun onResponse(call: Call<ArmorBean>, response: Response<ArmorBean>) {
                val response = response.body()

                // utiliser glide pour recuperer l'image à partir de son lien -> composant
                if(response?.assets != null) {
                    Glide.with(this@ArmorDetailsActivity).load(Uri.parse(response?.assets.imageMale))
                        .into(binding.imageItem)
                    Picasso.get().load(response?.assets.imageMale).into(binding.imageItem)
                }

                binding.detailName.text = response?.name?.capitalize()
                binding.detailType.text = response?.type?.capitalize()
                binding.detailRank.text = "\uD83C\uDF1F" + response?.rank?.capitalize()

                // skills
                if(!response?.skills.isNullOrEmpty()) {
                    var listSkills = mutableListOf<String>()
                    response?.skills?.forEach(){
                        listSkills.add("<u><b><i>" + it.skillName.capitalize() + "</i></b></u> :<br/>"+ it.description.capitalize())
                    }
                    binding.detailSkills.text = Html.fromHtml(listSkills.joinToString(separator="<br/><br/>"))
                }
                else {
                    binding.detailSkills.text = "None"
                }

                // defense
                var defenses = mapOf(
                    "base" to response?.defense?.base.toString(),
                    "max" to response?.defense?.max.toString(),
                    "augmented" to response?.defense?.augmented.toString()
                )
                var listDefenses = mutableListOf<String>()

                defenses?.forEach(){
                    listDefenses.add("\uD83D\uDEE1<u><b><i>" + it.key.capitalize() + "</i></b></u> : "+ it.value.capitalize())
                }

                binding.detailDefense.text = Html.fromHtml(listDefenses.joinToString(separator="<br/>"))

                // resistances
                var resistances = mapOf(
                    "fire" to response?.resistances?.fire.toString(),
                    "water" to response?.resistances?.water.toString(),
                    "ice" to response?.resistances?.ice.toString(),
                    "thunder" to response?.resistances?.thunder.toString(),
                    "dragon" to response?.resistances?.dragon.toString()
                )
                var listResistances = mutableListOf<String>()

                resistances?.forEach(){
                    listResistances.add("️<u><b><i>" + it.key.capitalize() + "</i></b></u> : "+ it.value.capitalize())
                }

                binding.detailResistances.text = Html.fromHtml(listResistances.joinToString(separator="<br/>"))

                // crafting
                if(!response?.crafting?.materials.isNullOrEmpty()) {
                    var listCrafting = mutableListOf<String>()
                    response?.crafting?.materials?.forEach(){
                        var quantity = "(x" + it.quantity + ") "
                        listCrafting.add(
                            "<u><b><i>⚒️" + it.item.name.capitalize() + "</i></b></u> " + quantity + ":" + "<br/>"
                                    + it.item.description.capitalize()
                        )
                    }
                    binding.detailCrafting.text = Html.fromHtml(listCrafting.joinToString(separator="<br/><br/>"))
                }
                else {
                    binding.detailCrafting.text = "None"
                }

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