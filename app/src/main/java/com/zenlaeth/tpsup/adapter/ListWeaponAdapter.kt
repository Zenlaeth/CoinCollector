package com.zenlaeth.tpsup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.R

class ListWeaponAdapter(private var mListener: onItemClickListener): RecyclerView.Adapter<ListWeaponAdapter.ViewHolder>() {
    val titles = arrayOf(
        "Great sword",
        "Long sword",
        "Sword & shield",
        "Dual blades",
        "Hammer",
        "Hunting horn",
        "Lance",
        "Gunlance",
        "Switch axe",
        "Charge blade",
        "Insect glaive",
        "Light bowgun",
        "Heavy bowgun",
        "Bow"

    )
    val images = arrayOf(
        R.drawable.ic_profile,
        R.drawable.ic_order,
        R.drawable.ic_address,
        R.drawable.ic_payment,
        R.drawable.ic_payment,
        R.drawable.ic_profile,
        R.drawable.ic_order,
        R.drawable.ic_address,
        R.drawable.ic_payment,
        R.drawable.ic_payment,
        R.drawable.ic_profile,
        R.drawable.ic_order,
        R.drawable.ic_address,
        R.drawable.ic_payment
    )

    /*    private lateinit var mListener : onItemClickListener*/

    interface onItemClickListener {
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(v, mListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
    inner class ViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}