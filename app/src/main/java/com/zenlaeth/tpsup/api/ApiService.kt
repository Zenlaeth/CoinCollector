package com.zenlaeth.tpsup.api

import android.app.Activity
import android.content.ContextWrapper
import android.net.Uri
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.bean.ArmorBean
import com.zenlaeth.tpsup.bean.MonsterBean
import com.zenlaeth.tpsup.bean.WeaponBean
import com.zenlaeth.tpsup.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import retrofit2.http.*

interface ApiService {
    companion object {
        fun getListArmorsByType(type: String): ArrayList<String> {
            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
            val call = serviceGenerator.getArmors()
            val items = ArrayList<String>()

            call.enqueue(object : Callback<MutableList<ArmorBean>> {
                override fun onResponse(
                    call: Call<MutableList<ArmorBean>>,
                    response: Response<MutableList<ArmorBean>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()?.filter { it.type == type }
                        val sortBody = body?.sortedBy { it.name }

                        for (item in sortBody!!) {
                            var name = item.name + " #" + item.id

                            items.add(name)
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<ArmorBean>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                }
            })

            return items
        }

        fun getListWeaponsByType(type: String): ArrayList<String> {
            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
            val call = serviceGenerator.getWeapons()
            val items = ArrayList<String>()

            call.enqueue(object : Callback<MutableList<WeaponBean>> {
                override fun onResponse(
                    call: Call<MutableList<WeaponBean>>,
                    response: Response<MutableList<WeaponBean>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()?.filter { it.type == type }
                        val sortBody = body?.sortedBy { it.name }

                        for (item in sortBody!!) {
                            var name = item.name + " #" + item.id

                            items.add(name)
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<WeaponBean>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                }
            })

            return items
        }

        fun getTypeWeapons(): Array<String> {
            return arrayOf(
                "great-sword",
                "long-sword",
                "sword-and-shield",
                "dual-blades",
                "hammer",
                "hunting-horn",
                "lance",
                "gunlance",
                "switch-axe",
                "charge-blade",
                "insect-glaive",
                "light-bowgun",
                "heavy-bowgun",
                "bow"
            )
        }

        fun getStatsArmor(
            id: String,
            image: ImageView,
            defense: TextView,
            resistance: TextView,
            context: Activity,
            applicationContext: ContextWrapper
        ) {
            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
            var call = serviceGenerator.getArmor(id)

            call.enqueue(object : Callback<ArmorBean> {
                override fun onResponse(call: Call<ArmorBean>, response: Response<ArmorBean>) {
                    val response = response.body()

                    // image
                    if (response?.assets != null) {
                        Glide.with(context)
                            .load(Uri.parse(response?.assets.imageMale))
                            .into(image)
                        Picasso.get().load(response?.assets.imageMale).into(image)
                    } else {
                        image.setImageResource(R.drawable.ic_payment)
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

                    defense.text = Html.fromHtml(listDefenses.joinToString(separator="<br/>"))

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
                        listResistances.add("️\uD83C\uDF00<u><b><i>" + it.key.capitalize() + "</i></b></u> : "+ it.value.capitalize())
                    }

                    resistance.text = Html.fromHtml(listResistances.joinToString(separator="<br/>"))
                }

                override fun onFailure(call: Call<ArmorBean>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                    t.message?.let { it1 -> Log.e("error", it1) }
                }
            })
        }

        fun getStatsWeapon(
            id: String,
            image: ImageView,
            attack: TextView,
            context: Activity,
            applicationContext: ContextWrapper
        ) {
            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
            var call = serviceGenerator.getWeapon(id)

            call.enqueue(object : Callback<WeaponBean> {
                override fun onResponse(call: Call<WeaponBean>, response: Response<WeaponBean>) {
                    val response = response.body()

                    // image
                    if (response?.assets != null) {
                        Glide.with(context)
                            .load(Uri.parse(response?.assets.image))
                            .into(image)
                        Picasso.get().load(response?.assets.image).into(image)
                    } else {
                        image.setImageResource(R.drawable.ic_payment)
                    }

                    // attack
                    var attacks = mapOf(
                        "display" to response?.attack?.display.toString(),
                        "raw" to response?.attack?.raw.toString(),
                    )
                    var listAttacks = mutableListOf<String>()

                    attacks?.forEach(){
                        listAttacks.add("⚔️<u><b><i>" + it.key.capitalize() + "</i></b></u> : "+ it.value.capitalize())
                    }

                    attack.text = Html.fromHtml(listAttacks.joinToString(separator="<br/>"))
                }

                override fun onFailure(call: Call<WeaponBean>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                    t.message?.let { it1 -> Log.e("error", it1) }
                }
            })
        }
    }

    @GET("/monsters")
    fun getMonsters() :Call<MutableList<MonsterBean>>

    @GET("/monsters/{id}")
    fun getMonster(@Path("id") searchById:String?) :Call<MonsterBean>

    @GET("/armor")
    fun getArmors() :Call<MutableList<ArmorBean>>

    @GET("/armor/{id}")
    fun getArmor(@Path("id") searchById:String?) :Call<ArmorBean>


    @GET("/weapons")
    fun getWeapons() :Call<MutableList<WeaponBean>>

    @GET("/weapons/{id}")
    fun getWeapon(@Path("id") searchById:String?) :Call<WeaponBean>
}