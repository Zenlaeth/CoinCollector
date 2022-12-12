package com.zenlaeth.tpsup.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.model.ArmorViewModel
import com.zenlaeth.tpsup.databinding.ActivityArmorBinding

class ArmorActivity: AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityArmorBinding.inflate(layoutInflater)}
    val model by lazy { ViewModelProvider(this).get(ArmorViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.data.observe(this) {
            binding.tvName.text = it?.name ?: "-"
            if(it != null && !it.bonus.ranks.isNullOrEmpty()) {
                binding.tvBonus.text = it.bonus.ranks[0].description
            }
            else {

                binding.tvBonus.text = "-"
            }

            if(it != null && !it.pieces.isNullOrEmpty()) {
                binding.tvHead.text = it.pieces[0].name
                binding.tvChest.text = it.pieces[1].name
                binding.tvGloves.text = it.pieces[2].name
                binding.tvWaist.text = it.pieces[3].name
                binding.tvLegs.text = it.pieces[4].name
                Picasso.get().load(it.pieces[0].assets.imageMale).into(binding.ivHead)
                Picasso.get().load(it.pieces[1].assets.imageMale).into(binding.ivChest)
                Picasso.get().load(it.pieces[2].assets.imageMale).into(binding.ivGloves)
                Picasso.get().load(it.pieces[3].assets.imageMale).into(binding.ivWaist)
                Picasso.get().load(it.pieces[4].assets.imageMale).into(binding.ivLegs)
            }
            else {
                binding.tvHead.text = "-"
                binding.tvChest.text = "-"
                binding.tvWaist.text = "-"
                binding.tvLegs.text = "-"
            }

//            if(it != null && !it.weaknesses.isNullOrEmpty()) {
//                var listWeakness = mutableListOf<String>()
//                it.weaknesses.forEach(){
//                    listWeakness.add(it.element)
//                }
//                binding.tvWeakness.text = listWeakness.joinToString(separator=", ")
//            }
//            else {
//                binding.tvWeakness.text = "-"
//            }
//
//            if(it != null && !it.resistances.isNullOrEmpty()) {
//                var listResistance = mutableListOf<String>()
//                it.resistances.forEach(){
//                    listResistance.add(it.element)
//                }
//                binding.tvResistance.text = listResistance.joinToString(separator=", ")
//            }
//            else {
//                binding.tvResistance.text = "-"
//            }
//            if(it != null && !it.resistances.isNullOrEmpty()) {
//                var listReward = mutableListOf<String>()
//                it.rewards.forEach(){
//                    listReward.add(it.item.name)
//                }
//                binding.tvReward.text = listReward.joinToString(separator="\n")
//            }
//            else {
//                binding.tvReward.text = "-"
//            }
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
        model.loadArmor()
    }
}