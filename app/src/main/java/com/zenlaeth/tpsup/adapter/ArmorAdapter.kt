package com.zenlaeth.tpsup.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.*
import com.zenlaeth.tpsup.activity.ArmorActivity
import com.zenlaeth.tpsup.activity.ArmorDetailsActivity
import com.zenlaeth.tpsup.activity.MonsterDetailsActivity
import com.zenlaeth.tpsup.bean.ArmorBean

class ArmorAdapter(
    val context: ArmorActivity,
    private val armorList: List<ArmorBean>,
    private val layoutId: Int
) : RecyclerView.Adapter<ArmorAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val armorId:TextView? = view.findViewById(R.id.id_item)
        val armorImage = view.findViewById<ImageView>(R.id.image_item)
        val armorName:TextView? = view.findViewById(R.id.name_item)
        val armorDescription: TextView? = view.findViewById(R.id.description_item)

        companion object {
            val armorIdKey = "ID"
        }

        init {
            view.setOnClickListener{
                val intent = Intent(view.context, ArmorDetailsActivity::class.java)
                // passer l'id à l'activité puis get unique armor
                intent.putExtra(armorIdKey, armorId?.text)
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
        // recuperer les informations de l'article
        val currentArmor = armorList[position]

        // utiliser glide pour recuperer l'image à partir de son lien -> composant
        if(currentArmor.assets != null) {
            Glide.with(context).load(Uri.parse(currentArmor.assets.imageMale))
                .into(holder.armorImage)
            Picasso.get().load(currentArmor.assets.imageMale).into(holder.armorImage)
        }

        // mettre à jour champs
        holder.armorId?.text = currentArmor.id.toString()
        holder.armorName?.text = currentArmor.name

/*        // mettre à jour l'image (intent extra)
        holder.articleImage?.drawable */
    }

    override fun getItemCount(): Int = armorList.size
}