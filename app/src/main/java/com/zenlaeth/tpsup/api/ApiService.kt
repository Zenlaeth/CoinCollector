package com.zenlaeth.tpsup.api

import android.util.Log
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

//        suspend fun getListWeaponsByType(type: String): ArrayList<String> {
//            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
//            val response = serviceGenerator.getWeapons().awaitResponse()
//            val items = ArrayList<String>()
//
//            if (response.isSuccessful) {
//                val body = response.body()?.filter { it.type == type }
//                val sortBody = body?.sortedBy { it.name }
//
//                for (item in sortBody!!) {
//                    var name = item.name + " #" + item.id
//
//                    items.add(name)
//                }
//            }
//
//            Log.e("hehehe", "heh")
//
//            return items
//        }

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
//
//    @GET("/weapons")
//    suspend fun getWeaponsAwait() : MutableList<WeaponBean>

    @GET("/weapons/{id}")
    fun getWeapon(@Path("id") searchById:String?) :Call<WeaponBean>

//    @GET("/categories")
//    fun getCategories() :Call<MutableList<CategoryModel>>
//
//    // user

//    @POST("/auth/login")
//    fun loginUser(@Body request: LoginUser) :Call<MutableList<LoginUser>>

//    @FormUrlEncoded
//    @POST("/auth/login")
//    fun loginUser(@Header("email") email: String, @Header("password") password: String) : Call<MutableList<LoginUser>>

//    @FormUrlEncoded
//    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
//    @POST("/auth/login")
//    fun loginUser(@Field("email") email: String, @Field("password") password: String) : Call<ResponseBody>
//
//    @POST("/users/register")
//    fun createUser(@Body request: NewUser) :Call<MutableList<NewUser>>
//
//    // carts
//    @GET("/carts")
//    fun getCarts() :Call<MutableList<CartModel>>
//
//    @POST("/carts")
//    fun createCart(@Body request: NewCart) :Call<MutableList<NewCart>>
}