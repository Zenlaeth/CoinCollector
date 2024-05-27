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
import com.zenlaeth.tpsup.entity.Project

class ProjectAdapter(
    val context: HomeActivity,
    private val projectList: MutableList<Project>,
    private val layoutId: Int
) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val setId:TextView? = view.findViewById(R.id.id_item)
        val setImage = view.findViewById<ImageView>(R.id.image_item)
        val setTitle:TextView? = view.findViewById(R.id.name_item)
        val setDescription:TextView? = view.findViewById(R.id.description_item)
        val setPrice:TextView? = view.findViewById(R.id.price_item)

        companion object {
            val setIdKey = "ID"
        }

        init {
            view.setOnClickListener{
                val intent = Intent(view.context, ProjectDetailsActivity::class.java)

                // passer l'id à l'activité puis get unique set
                intent.putExtra(setIdKey, setId?.text)
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
        val currentSet = projectList[position]

        // mettre à jour champs
        holder.setId?.text = currentSet.uid
        holder.setTitle?.text = currentSet.title
        if(currentSet.image != null) {
            Glide.with(context).load(Uri.parse(currentSet.image))
                .into(holder.setImage)
            Picasso.get().load(currentSet.image).into(holder.setImage)
        }
        holder.setPrice?.text = currentSet.price.toString() + " €"
//        holder.setDescription?.text = currentSet.description
    }

    override fun getItemCount(): Int = projectList.size
}