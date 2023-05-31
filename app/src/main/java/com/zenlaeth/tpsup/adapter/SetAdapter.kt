package com.zenlaeth.tpsup.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.*
import com.zenlaeth.tpsup.activity.*
import com.zenlaeth.tpsup.entity.Set

class SetAdapter(
    val context: MonsterDetailsActivity,
    private val setList: MutableList<Set>,
    private val layoutId: Int
) : RecyclerView.Adapter<SetAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val setId:TextView? = view.findViewById(R.id.id_item)
        val setImage = view.findViewById<ImageView>(R.id.image_item)
        val setName:TextView? = view.findViewById(R.id.name_item)

        companion object {
            val setIdKey = "ID"
        }

        init {
            view.setOnClickListener{
                val intent = Intent(view.context, SetDetailsActivity::class.java)

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
        val currentSet = setList[position]

        // mettre à jour champs
        holder.setId?.text = currentSet.uid
        holder.setName?.text = currentSet.setLabel
    }

    override fun getItemCount(): Int = setList.size
}