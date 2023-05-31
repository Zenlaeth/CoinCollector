package com.zenlaeth.tpsup.activity

import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.adapter.MonsterAdapter
import com.zenlaeth.tpsup.adapter.SetAdapter
import com.zenlaeth.tpsup.api.ApiService
import com.zenlaeth.tpsup.api.FirebaseManager
import com.zenlaeth.tpsup.api.FirebaseManager.Companion.getSet
import com.zenlaeth.tpsup.databinding.SetDetailsBinding
import kotlinx.coroutines.launch

class SetDetailsActivity : AppCompatActivity() {
    private lateinit var binding: SetDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setId = intent.getStringExtra(SetAdapter.ViewHolder.setIdKey)

        lifecycleScope.launch {
            val set = getSet(setId!!)

            binding.detailName.text = set.setLabel

            val statsList = mapOf(
                set.idHead to binding.statsHead,
                set.idChest to binding.statsChest,
                set.idGloves to binding.statsGloves,
                set.idWaist to binding.statsWaist,
                set.idLegs to binding.statsLegs
            )

            for(stat in statsList) {
                ApiService.getStatsArmor(
                    stat.key.toString(),
                    stat.value.headImage,
                    stat.value.detailDefense,
                    stat.value.detailResistances,
                    this@SetDetailsActivity,
                    applicationContext as ContextWrapper
                )
            }

            ApiService.getStatsWeapon(
                set.idWeapon.toString(),
                binding.statsWeapon.weaponImage,
                binding.statsWeapon.detailAttack,
                this@SetDetailsActivity,
                applicationContext as ContextWrapper
            )
        }
    }
}