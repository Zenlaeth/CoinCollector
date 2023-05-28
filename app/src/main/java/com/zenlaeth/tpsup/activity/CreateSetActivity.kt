package com.zenlaeth.tpsup.activity

import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ApiService.Companion.getListArmorsByType
import com.zenlaeth.tpsup.api.ApiService.Companion.getListWeaponsByType
import com.zenlaeth.tpsup.api.ApiService.Companion.getTypeWeapons
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.ArmorBean
import com.zenlaeth.tpsup.bean.WeaponBean
import com.zenlaeth.tpsup.databinding.CreateSetBinding
import com.zenlaeth.tpsup.databinding.StatsArmorBinding
import com.zenlaeth.tpsup.entity.Set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CreateSetActivity : AppCompatActivity(){
    private lateinit var binding: CreateSetBinding
    private var _headId = 0
    private var _chestId = 0
    private var _glovesId = 0
    private var _waistId = 0
    private var _legsId = 0
    private var _weaponId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // armor choosed
        loadStatsArmorSelected("head", binding.headDropdown, binding.statsHead)
        loadStatsArmorSelected("chest", binding.chestDropdown, binding.statsChest)
        loadStatsArmorSelected("gloves", binding.glovesDropdown, binding.statsGloves)
        loadStatsArmorSelected("waist", binding.waistDropdown, binding.statsWaist)
        loadStatsArmorSelected("legs", binding.legsDropdown, binding.statsLegs)

        // weapon type choosed
        val weaponTypeDropdown = binding.weaponTypeDropdown
        val weaponDropdown = binding.weaponDropdown

        val wTypeAdapter = ArrayAdapter(this, R.layout.dropdown_list, getTypeWeapons())
        weaponTypeDropdown.setAdapter(wTypeAdapter)

        weaponTypeDropdown.onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, i, l ->
//            lifecycleScope.launch {
//                // store id for request
//                val weaponTypeSelected = adapterView.getItemAtPosition(i).toString()
//                val listWeapons = getListWeaponsByType(weaponTypeSelected)
//
//                onResultListWeapons(listWeapons)
//            }

            val weaponTypeSelected = adapterView.getItemAtPosition(i).toString()
            val listWeapons = getListWeaponsByType(weaponTypeSelected)


            val wAdapter = ArrayAdapter(this, R.layout.dropdown_list, listWeapons)
            weaponDropdown.setAdapter(wAdapter)
        }

        // weapon choosed
        weaponDropdown.onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, i, l ->
            // store id for request
            val mString = adapterView.getItemAtPosition(i).toString().split("#")
            val id = mString[1]

            _weaponId = id.toInt()

            // load stats
            getStatsWeapon(
                id,
                binding.statsWeapon.weaponImage,
                binding.statsWeapon.detailAttack,
            )
        }

        val setName = binding.setNameEt

        // create set in database
        binding.createSet.setOnClickListener {
            if(
                _headId !== 0 &&
                _chestId !== 0 &&
                _glovesId !== 0 &&
                _waistId !== 0 &&
                _legsId !== 0 &&
                setName.text.isNotEmpty()
            ) {
                var setData = Set(
                    UUID.randomUUID().toString(),
                    _headId,
                    _chestId,
                    _glovesId,
                    _waistId,
                    _legsId,
                    _weaponId,
                    intent.getStringExtra("MonsterId")!!.toInt(),
                    FirebaseAuth.getInstance().currentUser!!.uid,
                    setName.text.toString()
                )
                FirebaseFirestore.getInstance().collection("sets")
                    .document()
                    .set(setData).addOnSuccessListener {
                        Toast.makeText(this, "The set was created successfully!", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }


    }

//    private fun onResultListWeapons(listWeapons: ArrayList<String>) {
//        val weaponDropdown = binding.weaponDropdown
//
//        for(weapon in listWeapons) {
//            Log.e("hihihihiiii", weapon)
//        }
//        Log.e("hihihihiiii", "heh")
//        val wAdapter = ArrayAdapter(this, R.layout.dropdown_list, listWeapons)
//        weaponDropdown.setAdapter(wAdapter)
//    }

    private fun loadStatsArmorSelected(
        type: String,
        dropdownType: AutoCompleteTextView,
        stats: StatsArmorBinding
    ) {
        val listNamesType = getListArmorsByType(type)

        val adapter = ArrayAdapter(this, R.layout.dropdown_list, listNamesType)
        dropdownType.setAdapter(adapter)

        dropdownType.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->
            // store id for request
            val mString = adapterView.getItemAtPosition(i).toString().split("#")
            val id = mString[1]

            if(type == "head") _headId = id.toInt()
            if(type == "chest") _chestId = id.toInt()
            if(type == "gloves") _glovesId = id.toInt()
            if(type == "waist") _waistId = id.toInt()
            if(type == "legs") _legsId = id.toInt()

            // load stats
            getStatsArmor(
                id,
                stats.headImage,
                stats.detailDefense,
                stats.detailResistances
            )
        }
    }

    private fun getStatsArmor(
        id: String,
        image: ImageView,
        defense: TextView,
        resistance: TextView
    ) {
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getArmor(id)

        call.enqueue(object : Callback<ArmorBean> {
            override fun onResponse(call: Call<ArmorBean>, response: Response<ArmorBean>) {
                val response = response.body()

                // image
                if (response?.assets != null) {
                    Glide.with(this@CreateSetActivity)
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

    private fun getStatsWeapon(
        id: String,
        image: ImageView,
        attack: TextView,
    ) {
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getWeapon(id)

        call.enqueue(object : Callback<WeaponBean> {
            override fun onResponse(call: Call<WeaponBean>, response: Response<WeaponBean>) {
                val response = response.body()

                // image
                if (response?.assets != null) {
                    Glide.with(this@CreateSetActivity)
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