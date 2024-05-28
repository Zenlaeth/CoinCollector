package com.zenlaeth.tpsup.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.*
import com.zenlaeth.tpsup.activity.*
import com.zenlaeth.tpsup.entity.Coin

class CoinAdapter(
    val context: HomeActivity,
    private val coinList: LiveData<List<Coin>>,
    private val layoutId: Int
) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    private var coins: List<Coin> = emptyList()

    init {
        coinList.observe(context as LifecycleOwner) { newList ->
            coins = newList
            notifyDataSetChanged()
        }
    }

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemId:TextView? = view.findViewById(R.id.id_item)
        val image = view.findViewById<ImageView>(R.id.image)
        val year:TextView? = view.findViewById(R.id.year)
        val rarity:TextView? = view.findViewById(R.id.rarity)
        val quantity:TextView? = view.findViewById(R.id.quantity)
        val value:TextView? = view.findViewById(R.id.value)

        companion object {
            val coinIdKey = "ID"
        }

        init {
            view.setOnClickListener{
                val intent = Intent(view.context, CoinDetailsActivity::class.java)

                // passer l'id à l'activité puis get unique set
                intent.putExtra(coinIdKey, itemId?.text)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations
        val currentCoin = coins[position]

        // mettre à jour champs
        holder.itemId?.text = currentCoin.id.toString()
        holder.year?.text = currentCoin.year.toString()
        holder.rarity?.text = currentCoin.rarity
//        if(currentCoin.image != null) {
//            Glide.with(context).load(Uri.parse(currentCoin.image))
//                .into(holder.setImage)
//            Picasso.get().load(currentCoin.image).into(holder.setImage)
//        }
        holder.quantity?.text = currentCoin.quantity.toString()
        holder.value?.text = currentCoin.value.toString() + " €"
    }

    override fun getItemCount(): Int = coins.size
}