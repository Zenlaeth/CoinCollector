package com.zenlaeth.tpsup.activity

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.ServiceGenerator
import com.zenlaeth.tpsup.bean.ArmorBean
import com.zenlaeth.tpsup.databinding.CreateSetBinding
import com.zenlaeth.tpsup.databinding.SignUpActivityBinding
import com.zenlaeth.tpsup.entity.Set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class SetActivity : AppCompatActivity(){
    private lateinit var binding: CreateSetBinding
    private var _headId = 0
    private var _chestId = 0
    private var _glovesId = 0
    private var _waistId = 0
    private var _legsId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setName = binding.setNameEt

        setIdItemSelected("head", binding.headDropdown)
        setIdItemSelected("chest", binding.chestDropdown)
        setIdItemSelected("gloves", binding.glovesDropdown)
        setIdItemSelected("waist", binding.waistDropdown)
        setIdItemSelected("legs", binding.legsDropdown)

        val createSet = binding.createSet
        createSet.setOnClickListener {
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
                    1,
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

    private fun setIdItemSelected(type: String, dropdown: AutoCompleteTextView) {
        val listNameHeads = getListArmorsType(type, "name")
        // dirty way
        val listIds = getListArmorsType(type, "id")

        val adapter = ArrayAdapter(this, R.layout.dropdown_list, listNameHeads)
        dropdown.setAdapter(adapter)

        dropdown.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            // dirty way
            if(type == "head") {
                _headId = listIds[l.toInt()].toInt()
                getStatsEquipment(
                    _headId.toString(),
                    binding.headImage
                )
            }
            if(type == "chest") _chestId = listIds[l.toInt()].toInt()
            if(type == "gloves") _glovesId = listIds[l.toInt()].toInt()
            if(type == "waist") _waistId = listIds[l.toInt()].toInt()
            if(type == "legs") _legsId = listIds[l.toInt()].toInt()
        }
    }

    fun getListArmorsType(type: String, field: String): ArrayList<String> {
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

                    for(item in sortBody!!) {
                        if(field == "name") {
                            items.add(item.name)
                        } else if(field == "id") {
                            items.add(item.id.toString())
                        }

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

    fun getStatsEquipment(
        id: String,
        image: ImageView
    ) {
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        var call = serviceGenerator.getArmor(id)

        call.enqueue(object : Callback<ArmorBean> {
            override fun onResponse(call: Call<ArmorBean>, response: Response<ArmorBean>) {
                val response = response.body()

                // utiliser glide pour recuperer l'image à partir de son lien -> composant
                if (response?.assets != null) {
                    Glide.with(this@SetActivity)
                        .load(Uri.parse(response?.assets.imageMale))
                        .into(image)
                    Picasso.get().load(response?.assets.imageMale).into(image)
                } else {
                    image.setImageResource(R.drawable.ic_payment)
                }
            }

            override fun onFailure(call: Call<ArmorBean>, t: Throwable) {
                Toast.makeText(applicationContext, "Error !", Toast.LENGTH_LONG).show()
                t.message?.let { it1 -> Log.e("error", it1) }
            }
        })
    }
}