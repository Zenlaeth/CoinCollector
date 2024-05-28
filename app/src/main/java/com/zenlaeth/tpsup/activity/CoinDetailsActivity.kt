package com.zenlaeth.tpsup.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.adapter.CoinAdapter
import com.zenlaeth.tpsup.databinding.CoinDetailsBinding
import kotlinx.coroutines.launch

class CoinDetailsActivity: AppCompatActivity(){
    private lateinit var binding: CoinDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CoinDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coinId = intent.getStringExtra(CoinAdapter.ViewHolder.coinIdKey)

        lifecycleScope.launch {
            val coinDao = MainApplication.coinCollectorDatabase.getCoinDao()
            val coin = coinDao.getCoin(coinId!!)
//            // utiliser glide pour recuperer l'image à partir de son lien -> composant
//            if(coin.image != null) {
//                Glide.with(this@CoinDetailsActivity).load(Uri.parse(coin.image))
//                    .into(binding.image)
//                Picasso.get().load(set.image).into(binding.image)
//            }
            binding.year.text = coin.year.toString()
            binding.rarity.text = coin.rarity.toString()
            binding.quantity.text = coin.quantity.toString()
            binding.value.text = coin.value.toString() + " €"
        }
    }
}