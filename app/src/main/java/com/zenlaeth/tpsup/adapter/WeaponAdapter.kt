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
import com.zenlaeth.tpsup.activity.*
import com.zenlaeth.tpsup.bean.ArmorBean
import com.zenlaeth.tpsup.bean.WeaponBean

class WeaponAdapter(
    val context: WeaponActivity,
    private val weaponList: List<WeaponBean>,
    private val layoutId: Int
) : RecyclerView.Adapter<WeaponAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val weaponId:TextView? = view.findViewById(R.id.id_item)
        val weaponImage = view.findViewById<ImageView>(R.id.image_item)
        val weaponName:TextView? = view.findViewById(R.id.name_item)
        val weaponDescription: TextView? = view.findViewById(R.id.description_item)

        companion object {
            val weaponIdKey = "ID"
        }

        init {
            view.setOnClickListener{
                val intent = Intent(view.context, WeaponDetailsActivity::class.java)

                // passer l'id à l'activité puis get unique weapon
                intent.putExtra(weaponIdKey, weaponId?.text)
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
        val currentWeapon = weaponList[position]

        // utiliser glide pour recuperer l'image à partir de son lien -> composant
        if(currentWeapon.assets != null) {
            Glide.with(context).load(Uri.parse(currentWeapon.assets.image))
                .into(holder.weaponImage)
            Picasso.get().load(currentWeapon.assets.image).into(holder.weaponImage)
        }

        // mettre à jour champs
        holder.weaponId?.text = currentWeapon.id.toString()
        holder.weaponName?.text = currentWeapon.name

/*        // mettre à jour l'image (intent extra)
        holder.articleImage?.drawable */
    }

    override fun getItemCount(): Int = weaponList.size
}