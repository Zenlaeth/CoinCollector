package com.zenlaeth.tpsup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.databinding.ActivityMonsterBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread

val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()

class MonsterActivity: AppCompatActivity(), View.OnClickListener {
    val binding by lazy {ActivityMonsterBinding.inflate(layoutInflater)}
    val model by lazy { ViewModelProvider(this).get(MonsterViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.data.observe(this) {
            binding.tvName.text = it?.name ?: "-"
            binding.tvDescription.text = it?.description ?: "-"
            if(it != null && !it.locations.isNullOrEmpty()) {
                binding.tvLocation.text = it.locations[0].name
            }
            else {

                binding.tvLocation.text = "-"
            }

            if(it != null && !it.weaknesses.isNullOrEmpty()) {
                var listWeakness = mutableListOf<String>()
                it.weaknesses.forEach(){
                    listWeakness.add(it.element)
                }
                binding.tvWeakness.text = listWeakness.joinToString(separator=", ")
            }
            else {
                binding.tvWeakness.text = "-"
            }

            if(it != null && !it.resistances.isNullOrEmpty()) {
                var listResistance = mutableListOf<String>()
                it.resistances.forEach(){
                    listResistance.add(it.element)
                }
                binding.tvResistance.text = listResistance.joinToString(separator=", ")
            }
            else {
                binding.tvResistance.text = "-"
            }
        }

        model.errorMessage.observe(this) {
            if(it != null) {
                binding.tvError.isVisible = true
                binding.tvError.text = it
            }
            else {
                binding.tvError.isVisible = false
            }
        }

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }

        binding.btLoad.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        model.loadMonster()
    }
}